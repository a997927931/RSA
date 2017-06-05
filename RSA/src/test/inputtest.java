package test;

import java.io.UnsupportedEncodingException;


public class inputtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a="Äã";
		try {
			String m = java.net.URLEncoder.encode(a,"utf-8");
			System.out.println(m);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buffer=a.getBytes();
		 String h = "";  
         
	        for(int i = 0; i < buffer.length; i++){  
	            String temp = Integer.toHexString(buffer[i] & 0xFF);  
	            if(temp.length() == 1){  
	                temp = "0" + temp;  
	            }  
	            h = h + " "+ temp;  
	        }  
	}

}
