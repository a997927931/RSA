package test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Random;


public class main {
	
	
	//�������������e
	private static BigInteger Gete(BigInteger fn){
		//����С��fn�������
		Random r=new Random();
		int a=r.nextInt(999);
		BigInteger Biga=new BigInteger(String.valueOf(a));
		BigInteger th=new BigInteger("1000");
		BigInteger e=fn.multiply(Biga).divide(th);
		//ȡ��������һ����fn���ص���
		BigInteger i;
		int flag=0;
		for(i=e;i.compareTo(fn)==-1;i=i.add(BigInteger.ONE)){
			if(i.gcd(fn).compareTo(BigInteger.ONE)==0){
				e=i;
				flag=1;
				break;
			}
		}
		if(flag==1)
			return e;
		else
			return Gete(fn);
	}
	
	public static BigInteger extendEuclid(BigInteger e, BigInteger modValue){  
	      //ad-my=1
		BigInteger D = BigInteger.ZERO;  
		BigInteger x1,x2,y1,y2,t1,t2;  
	      
	    y1 = BigInteger.ONE;  
	    x1= BigInteger.ZERO;  
	    x2 = modValue;  
	    y2 = e;  
	      
	    BigInteger q = BigInteger.ZERO;  
	    while(true){  
	        if(y2.compareTo(BigInteger.ONE)==0){  
	            D = y1;  
	            break;  
	        }  
	        q =x2.divide(y2);  
	          
	        t1=x1.subtract(q.multiply(y1));  
	        t2=x2.subtract(q.multiply(y2));  
	          
	        x1 = y1;  
	        x2 = y2;  
	          
	        y1 = t1;  
	        y2 = t2;  
	    }  
	    return D.compareTo(BigInteger.ZERO)==-1?modValue.add(D):D;  
	} 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger m;
		BigInteger q;
		BigInteger p;
		BigInteger fn;
		BigInteger n;
		BigInteger e;
		BigInteger d;
		BigInteger sig;
		BigInteger ms;
		//�ַ���ת��Ϊ������
		String s="һ�����������߰˾�ʮһ�����������߰˾�ʮһ�����������߰�";
		String s2="f";
		try {
			s2  =   java.net.URLEncoder.encode(s,   "utf-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		m=new BigInteger(s2.getBytes());
		//������ɴ�����p��q
		p=BigInteger.probablePrime(1024, new Random());
		q=BigInteger.probablePrime(1024, new Random());
		//����n
		n=p.multiply(q);
		//����fn
		fn=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		//���������Կe
		e=Gete(fn);
		d=extendEuclid(e,fn);
		sig=m.modPow(d,n);
		ms=sig.modPow(e,n);
		 
		try {
			String   mytext2   =   java.net.URLDecoder.decode(new String(ms.toByteArray()),   "utf-8");
			System.out.println(mytext2);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}

}
