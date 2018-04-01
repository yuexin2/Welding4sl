package com.xatu.yuexin.welding4sl.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.megvii.cloud.http.CommonOperate;
import com.megvii.cloud.http.FaceSetOperate;
import com.megvii.cloud.http.Response;
import com.xatu.yuexin.welding4sl.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class CheckFaceActivity extends AppCompatActivity  {
    private static final int REQUEST_CODE_TAKE_PICTURE = 0;
    private static final int REQUEST_CODE_CROP_PICTURE = 1;
    private static final int REQUEST_CODE_PERMISSION = 2;

    private ImageView mImageView;
    private Uri mUri;
    private File mFile;


    String key = "Z0-hIaQ61A7DHIqrKPLgnGlAaL1c1MVp";//api_key
    String secret = "XFxQTRqzv-gWxL5YsOgo8mrvfbSy_APz";//api_secret

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());


        setContentView(R.layout.activity_check_face);

        mImageView = (ImageView) findViewById(R.id.iv_show_camera2_activity);
        mFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/jd_id_origin_pictrue.jpg");
        mUri = FileProvider.getUriForFile(this, "com.xatu.yuexin.welding4sl.fileprovider", mFile);

        // TODO: 首先确保父目录应该存在。目标文件可以不存在，但目录必须存在。如果不存在需要mkdirs
        if (!mFile.getParentFile().exists()) {
            mFile.getParentFile().mkdirs();
        }
        showTakePhotoActivity();
    }

    private void showTakePhotoActivity() {
        // TODO: 检查权限是否足够
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
            //如果没有授权，则请求授权
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            // TODO: 需要考虑如果系统没有匹配的intent情况
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
            startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);
        }
    }

    public void showPhotoCropActivity() {
        // TODO: 需要考虑权限问题，intent没有匹配的情况，在个别系统中会有这样情况发生。
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(mUri, "image/*");
        intent.putExtra("crop", "true");//进行修剪
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        intent.putExtra("outputX", 150);
//        intent.putExtra("outputY", 150);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("return-data", false);

        //重要的一步，使用grantUriPermission来给对应的包提升读写指定uri的临时权限。否则即使调用成功，也会保存裁剪照片失败。
        List<ResolveInfo> resInfoList = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            grantUriPermission(packageName, mUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        startActivityForResult(intent, REQUEST_CODE_CROP_PICTURE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // TODO: 检查权限是否获取成功，可以在这里检查获取结果，也可以复用之前的逻辑。
        showTakePhotoActivity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_TAKE_PICTURE:
                if (resultCode == RESULT_OK) {
                    showPhotoCropActivity();
                } else {
                    // TODO: 拍照失败或者取消的情况
                }
                break;
            case REQUEST_CODE_CROP_PICTURE:
                if (resultCode == RESULT_OK) {
                    // TODO: 成功裁剪并保存到mUri情况
                    //mImageView.setVisibility(View.VISIBLE);
                    mImageView.setImageURI(mUri);


                    if(TextUtils.isEmpty(key) || TextUtils.isEmpty(secret)){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("please enter key and secret");
                        builder.setTitle("");
                        builder.show();
                    }else{


                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message msg = new Message();
                                facePPhandler.sendMessage(msg);
                            }
                        }).start();
                    }
                } else {
                    // TODO: 裁剪失败或者保存失败的情况
                }
                break;
        }
    }
    private Handler facePPhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(CheckFaceActivity.this.getContentResolver(),mUri);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] byteArray = baos.toByteArray();

                CommonOperate commonOperate = new CommonOperate(key, secret, false);
                FaceSetOperate FaceSet = new FaceSetOperate(key, secret, false);
                ArrayList<String> faces = new ArrayList<>();


                //查询人脸是否在库中
                Response faceset =  commonOperate.searchByOuterId(null, null, byteArray, "yxTestSet", 1);
                String faceSetResult = new String(faceset.getContent());
                AlertDialog.Builder builder = new AlertDialog.Builder(CheckFaceActivity.this);
                builder.setMessage(faceSetResult);
                builder.setTitle("");
                builder.show();


                JSONObject jsonObject = new JSONObject(faceSetResult);
                JSONObject thresholds = jsonObject.getJSONObject("thresholds");

                double low = thresholds.getDouble("1e-3");
                double hight = thresholds.getDouble("1e-5");

                JSONArray results = jsonObject.getJSONArray("results");
                JSONObject result = (JSONObject) results.get(0);
                double confidence = result.getDouble("confidence");
                String face_token = result.getString("face_token");

                if(confidence > hight){
                    Toast.makeText(CheckFaceActivity.this,"检测成功",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent( CheckFaceActivity.this , LoginActivity.class);
				    startActivity(intent);
                    CheckFaceActivity.this.finish();
                }else{
                    Toast.makeText(CheckFaceActivity.this,"未检测到匹配人脸，请重新检测",Toast.LENGTH_LONG).show();
                    showTakePhotoActivity();
                }



            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(CheckFaceActivity.this,"没有数据请重新检测。",Toast.LENGTH_LONG).show();
                showTakePhotoActivity();
            }
        }
    };


}
