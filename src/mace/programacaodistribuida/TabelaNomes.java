package mace.programacaodistribuida;

public class TabelaNomes {
	
	final int tamMax = 100;
	
	private String[] nomes = new String[tamMax];
	private String[] hosts = new String[tamMax];
	private int[] portas = new int[tamMax];
	private int tamAtual = 0;
	
	int buscar(String s) {
		for (int i = 0; i < tamAtual; i++)
			if (nomes[i].equals(s))
				return i;
		
		return -1;			
	}
	
	int inserir(String s, String nomeHost, int numeroPorta) {
		
		int indice = buscar(s); // ja existe? 
		
		if ((indice == -1) && (tamAtual < tamMax)) {
			nomes[tamAtual] = s;
			hosts[tamAtual] = nomeHost;
			portas[tamAtual] = numeroPorta;
			
			tamAtual++;
			
			return 1;
		} else // ja existe, ou tabela cheia
			return 0;
	}
	
	int getPorta(int indice) {
		return portas[indice];
	}
	
	String getNomeHost(int indice) {
		return hosts[indice];
	}
	
}