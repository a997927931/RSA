package bean;

import java.math.BigInteger;

public class message {
	public BigInteger Sigm;
	public BigInteger n;
	public BigInteger e;
	public BigInteger getSigm() {
		return Sigm;
	}
	public void setSigm(BigInteger sigm) {
		Sigm = sigm;
	}
	public BigInteger getN() {
		return n;
	}
	public void setN(BigInteger n) {
		this.n = n;
	}
	public BigInteger getE() {
		return e;
	}
	public void setE(BigInteger e) {
		this.e = e;
	}
	
}
