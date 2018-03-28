package com.xatu.yuexin.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Digest {
	private MessageDigest __md5 = null;
    private StringBuffer __digestBuffer = null;

    public MD5Digest() //throws NoSuchAlgorithmException
    {
        try {
			__md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        __digestBuffer = new StringBuffer();
    }

    public String md5crypt(String s)
    {
        if(s==null)
        	return null;
    	__digestBuffer.setLength(0);
        byte abyte0[] = __md5.digest(s.getBytes());
        for(int i = 0; i < abyte0.length; i++)
            __digestBuffer.append(this.toHex(abyte0[i]));

        return __digestBuffer.toString();
    }
    public String toHex(byte one){
      //String HEX="0123456789ABCDEF";
    	String HEX="1234567890098765";
	  //String HEX="wwwhqxksccomwwww";
	  char[] result=new char[2];
	  result[0]=HEX.charAt((one & 0xf0) >> 4);
	  result[1]=HEX.charAt(one & 0x0f);
	  String mm=new String(result);
	  return mm;
   }
}
