package mace.classesutilitarias;

public class Endereco {
	
	String nomeHost;
	int numeroPorta;
	
	public Endereco(String s, int i) {
		nomeHost = new String(s);
		numeroPorta = i;
	}
	
	public String getNomeHost() {
		return nomeHost;
	}
	
	public int getPorta() {
		return numeroPorta;
	}
	
}