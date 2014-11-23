package mace.programacaodistribuida;

public class TabelaNomes {
	
	final int tamMax = 100;
	
	private String[] nomes = new String[tamMax];
	private String[] ips = new String[tamMax];
	private int[] portas = new int[tamMax];
	private int tamAtual = 0;
	
	int buscar(String nome) {
		for (int i = 0; i < tamAtual; i++)
			if (nomes[i].equals(nome))
				return i;
		
		return -1;			
	}
	
	int inserir(String nome, String ip, int porta) {
		
		int indice = buscar(nome); // ja existe? 
		
		if ((indice == -1) && (tamAtual < tamMax)) {
			nomes[tamAtual] = nome;
			ips[tamAtual] = ip;
			portas[tamAtual] = porta;
			
			tamAtual++;
			
			return 1;
		} else // ja existe, ou tabela cheia
			return 0;
	}
	
	String getNome(int indice) {
		return nomes[indice];
	}
	
	String getIp(int indice) {
		return ips[indice];
	}
	
	int getPorta(int indice) {
		return portas[indice];
	}
	
}