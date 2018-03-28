package com.xatu.yuexin.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class ConsoleUtil {
     
	public  String input="";
	public  String err="";
	

     
    private Process p = null;
    /**
     * 运行控制台命令
     * @param cmd
     */
    public void runConsole(String[] cmd) {
         
        try {
            p = Runtime.getRuntime().exec(cmd[0]);
            
            DataOutputStream os = new DataOutputStream(p.getOutputStream());
            
            for(int i=1;i<cmd.length;i++){
            	os.writeBytes(cmd[i]+" \n");
            }
            os.writeBytes("exit \n");
            os.flush();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }finally{
        }
        Thread threadInput =  new ConsoleInputReturn(p.getInputStream());
        threadInput.start();
        Thread threadErr =  new ConsoleErrReturn(p.getErrorStream());
        threadErr.start();
        int times=0;
        while(true){//等待所有子线程执行完  
        	if((threadInput.isAlive()==false)&&(threadErr.isAlive()==false)||times>30){  
        	break;  
        	}  
        	try {
				Thread.sleep(1000);
				times++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}  
        }
        p.destroy();
    }
    /**
     * 控制台错误信息
     * @author Administrator
     *
     */
    private class ConsoleErrReturn extends Thread {
        private InputStream is;
        public ConsoleErrReturn(InputStream is) {
            this.is = is;
        }
        @Override
        public void run() {
            InputStreamReader reader = null;
            try {
                reader = new InputStreamReader(is,"GBK");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            char[] buf = new char[1024];
            int size;
            while(true) {
                try {
                    size = reader.read(buf);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
                if(size != -1) {
                    //System.out.print(new String(buf,0,size));
                    err += new String(buf,0,size);
                } else {
                    break;
                }
            }
            try {
                reader.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
    }
    /**
     * 控制台输入信息
     * @author Administrator
     *
     */
    private class ConsoleInputReturn extends Thread {
        private InputStream is;
        public ConsoleInputReturn(InputStream is) {
            this.is = is;
        }
        @Override
        public void run() {
            InputStreamReader reader = null;
            try {
                reader = new InputStreamReader(is,"GBK");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            char[] buf = new char[1024];
            int size;
            while(true) {
                try {
                    size = reader.read(buf);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
                if(size != -1) {
                    input += new String(buf,0,size);
                } else {
                    break;
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	public synchronized boolean getRootAhth()
	{
		Process process = null;
		DataOutputStream os = null;
		try
		{
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes("exit\n");
			os.flush();
			int exitValue = process.waitFor();
			if (exitValue == 0)
			{
				return true;
			} else
			{
				return false;
			}
		} catch (Exception e)
		{
			return false;
		} finally
		{
			try
			{
				if (os != null)
				{
					os.close();
				}
				process.destroy();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}