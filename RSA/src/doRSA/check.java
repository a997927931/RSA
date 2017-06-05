package doRSA;

import java.math.BigInteger;

import bean.message;

public class check {
	public BigInteger checker(message mes){
		BigInteger s=mes.getSigm();
		BigInteger n=mes.getN();
		BigInteger e=mes.getE();
		BigInteger ms;
		ms=s.modPow(e,n);
		return ms;
	}
}
