package doRSA;

import java.math.BigInteger;
import java.util.Random;

import bean.message;

public class Sig {
	private BigInteger m;
	private BigInteger p;
	private BigInteger q;
	private BigInteger n;
	private BigInteger e;
	private BigInteger fn;
	private BigInteger d;
	private BigInteger s;
	private message mes;
	
	public message Siger(BigInteger m){
		mes=new message();
		//随机生成大素数p，q
		p=BigInteger.probablePrime(1024, new Random());
		q=BigInteger.probablePrime(1024, new Random());
		//公开n
		n=p.multiply(q);
		mes.setN(n);
		//保密fn
		fn=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		//生成随机公钥e
		e=Gete(fn);
		mes.setE(e);
		//求得逆元d
		d=extendEuclid(e,fn);
		//进行签名运算
		s=m.modPow(d,n);
		mes.setSigm(s);
		return mes;
	}
	//生成随机互素数e
	private static BigInteger Gete(BigInteger fn){
			//生成小于fn的随机数
			Random r=new Random();
			int a=r.nextInt(999);
			BigInteger Biga=new BigInteger(String.valueOf(a));
			BigInteger th=new BigInteger("1000");
			BigInteger e=fn.multiply(Biga).divide(th);
			//取随机数后第一个与fn互素的数
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
		//求逆元
	private static BigInteger extendEuclid(BigInteger e, BigInteger modValue){  
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
}
