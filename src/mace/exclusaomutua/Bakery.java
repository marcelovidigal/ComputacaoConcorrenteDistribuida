package mace.exclusaomutua;

class Bakery implements Bloqueio {
	
	int n;
	
	volatile boolean[] escolhendo;	// porta de entrada
	volatile int[] ticket;
	
	public Bakery(int numeroProcessos) {
		n = numeroProcessos;
		escolhendo = new boolean[n];
		ticket = new int[n];
		
		for (int j = 0; j < n; j++) {
			escolhendo[j] = false;
			ticket[j] = 0;
		}
	}
	
	public void requisitarSecaoCritica(int i) {
		
		// passo 1, porta, escolha um numero
		escolhendo[i] = true;
		
		for (int j = 0; j < n; j++)
			if (ticket[j] > ticket[i])
				ticket[i] = ticket[j];
		
		ticket[i]++;
		escolhendo[i] = false;
		
		// passo 2, verifica se o pid eh o menor
		for (int j = 0; j < n; j++) {
			while (escolhendo[j]);
			while ((ticket[j] != 0) && ((ticket[j] < ticket[i]) || ((ticket[j] == ticket[i]) && j < i)));	// busy wait
		}
	}
	
	public void liberarSecaoCritica(int i) {	// protocolo de saida
		ticket[i] = 0;
	}
	
}