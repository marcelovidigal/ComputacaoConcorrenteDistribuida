package mace.classesutilitarias;

public class Endereco {
	
	String nome;
	String ip;
	int porta;
	
	public Endereco(String nome, String ip, int porta) {
		this.nome = new String(nome);
		this.ip = new String(ip);
		this.porta = porta;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getIp() {
		return ip;
	}
	
	public int getPorta() {
		return porta;
	}
	
}