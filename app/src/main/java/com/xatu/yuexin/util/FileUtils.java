package com.xatu.yuexin.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Environment;


public class FileUtils {

	private String SDPATH;

	public String getSDPATH() {
		return SDPATH;
	}
	
	public FileUtils(){
		//得到SD卡的路径
		SDPATH = Environment.getExternalStorageDirectory()+"/";
	}
	/**
	 * 在SD卡上创建文件
	 * @param fileName 文件名
	 * @return
	 * @throws IOException
	 */
	public File createSDFile(String fileName) throws IOException{
		System.out.println(SDPATH+fileName);
		File file = new File(SDPATH+fileName);
		file.createNewFile();
		return file;
	}
	
	/**
	 * 在SD卡上创建目录
	 * @param dirName
	 * @return
	 */
	public File createSDDir(String dirName){
		File dir;
		//匹配得到对否为多级目录
	    String regEx = "\\w*/\\w*/\\w*";  
	    Pattern pat = Pattern.compile(regEx);  
	    Matcher mat = pat.matcher(dirName);
	    boolean flag = mat.find();
	    if(flag){
	    	dirName = dirName.substring(0, dirName.lastIndexOf("/"));
	    	dir = new File(SDPATH+dirName);
	    	dir.mkdirs();
	    }else{
	    	dir = new File(SDPATH+dirName);
	    	dir.mkdir();
	    }
		return dir;
	}
	/**
	 * 判断文件是否存在
	 * @param fileName
	 * @return
	 */
	public boolean isFileExist(String fileName){
		File file = new File(SDPATH+fileName);
		return file.exists();
	}
	/**
	 * 删除文件
	 * @param fileName
	 * @return
	 */
	public boolean deleteFile(String fileName){
		File file = new File(SDPATH+fileName);
		return file.delete();
	}
	/**
	 * 删除文件
	 * @param fileName
	 * @return
	 */
	public boolean deleteFile2(String fileName){
		File file = new File(fileName);
		return file.delete();
	}
	
	/**
	 * 向sd卡中写入数据
	 * @param path 路径
	 * @param fileName 文件名
	 * @param input InputStream数据流
	 * @return
	 */
	public File write2SDFromInput(String path,String fileName,InputStream input){
		File file = null;
		FileOutputStream out = null;
		try {
			createSDDir(path);
			file = createSDFile(path+fileName);
			out = new FileOutputStream(file);
			
			byte buffer[] = new  byte[4*1024];
			int numread;
			while((numread=input.read(buffer))!=-1){
				out.write(buffer,0,numread);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public void writeTxtFile(String content,String path,String  fileName,boolean isCover){
		//fileName = SDPATH+fileName;
		File file = null;
		FileOutputStream fos =null;
		OutputStreamWriter osw=null;
		BufferedWriter bw=null;
		try {
			file = createSDFile(path+fileName);
			if(isCover){
				fos = new FileOutputStream(file);
			}else{
				fos = new FileOutputStream(file,true);
			}
			osw = new OutputStreamWriter(fos);
		    bw = new BufferedWriter(osw);
		    bw.write(content);
			bw.newLine();
			bw.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				osw.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	} 
	
	public String readTXTFile(String fileName) {
        File file = new File(SDPATH+fileName);
        StringBuffer sbresult=new StringBuffer();
        BufferedReader reader = null;
        try {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = "";
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                //System.out.println(line+"行-->"+tempString);
                sbresult.append(tempString);
                tempString = "";
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sbresult.toString();
    }
	
	/**
	 * 读取目录中的MP3文件的名字和大小
	 */
//	public List<Mp3> getMp3Files(String path){
//		List<Mp3> mp3Infos = new ArrayList<Mp3>();
//		File file = new File(SDPATH+path);
//		File[] files = file.listFiles();
//		if(null!=files){
//			for (int i = 0; i < files.length; i++) {
//				Mp3 mp3 = new Mp3();
//				String docName = files[i].getName();
//				if(docName.endsWith("mp3")){
//					String mp3Name = files[i].getName();
//					String mp3NameTemp  = mp3Name.substring(0, mp3Name.lastIndexOf(".mp3")==-1?mp3Name.length():mp3Name.lastIndexOf(".mp3"));
//					mp3.setSname(mp3NameTemp);
//					MediaPlayer mediaPlayer = new MediaPlayer();
//					
//					try {
//						mediaPlayer.setDataSource(SDPATH+path+mp3Name);
//						mediaPlayer.prepare();
//						
//						mp3.setSongTime(mediaPlayer.getDuration()/1000);
//						
//						//mp3.setUrlLrc(SDPATH+path.substring(0, path.lastIndexOf("/"))+"/lrc/"+mp3Name.substring(0, mp3Name.lastIndexOf("/"))+".lrc");
//						mp3.setUrlMp3(SDPATH+path+mp3Name);
//						
//						String lrcPath = path.replace("/mp3/", "/lrc/")+mp3Name.substring(0, mp3Name.lastIndexOf("."))+".lrc";
//						//System.out.println(SDPATH+lrcPath);
//						if(isFileExist(lrcPath)){
//							mp3.setUrlLrc(SDPATH+lrcPath);
//						}else{
//							mp3.setUrlLrc("NODOC");
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					
//					
//				}
//				mp3Infos.add(mp3);
//			}
//		}
//		
//		return mp3Infos;
//	}
	
	/**
	 * 获取文件夹中文件的路径
	 * @param filePath
	 * @return
	 */
	public List<String> fileList(String filePath){
		File srcFile = new File(SDPATH+filePath);
		boolean bFile = srcFile.exists();
		List<String> list = new ArrayList<String>();
		if (!bFile || !srcFile.isDirectory() || !srcFile.canRead()) {
			
		}else{
			File[] file = srcFile.listFiles();
			for(int i=0;i<file.length;i++){
				list.add(file[i].getAbsolutePath());
			}
		}
		return list;
	}
}
