package mace.sincronizacao; 

class LeitorEscritor {
	
	int numeroLeitores = 0;
		
	SemaforoBinario mutex = new SemaforoBinario(true);
	SemaforoBinario bloqueioEscritor = new SemaforoBinario(true);
	
	public void iniciarLeitura() {
		mutex.P();
		numeroLeitores++;
		
		if (numeroLeitores == 1)
			bloqueioEscritor.P();
		
		mutex.V();
	}
	
	public void finalizarLeitura() {
		mutex.P();
		numeroLeitores--;
		
		if (numeroLeitores == 0)
			bloqueioEscritor.V();
		
		mutex.V();
	}
	
	public void iniciarEscrita() {
		bloqueioEscritor.P();
	}
	
	public void finalizarEscrita() {
		bloqueioEscritor.V();
	}
	
}