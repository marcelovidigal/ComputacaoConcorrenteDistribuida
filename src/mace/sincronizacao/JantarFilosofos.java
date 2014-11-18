package mace.sincronizacao;

class JantarFilosofos implements Recurso {
	
	int n = 0;
	SemaforoBinario[] garfo = null;
	
	public JantarFilosofos(int n) {
		
		this.n = n;
		garfo = new SemaforoBinario[n] ;
		
		for (int i = 0; i < n; i++) {
			garfo[i] = new SemaforoBinario(true);
		}
	}
	
	public void obter(int i) {
		garfo[i].P();
		garfo[(i + 1) % n].P();
	}
	
	public void liberar(int i) {
		garfo[i].V();
		garfo[(i + 1) % n].V();		
	}
	
	public static void main(String[] args) {
		
		JantarFilosofos jantarFilosofos = new JantarFilosofos(5);
		
		for (int i = 0; i < 5; i++)
			new Filosofo(i, jantarFilosofos);
	}
	
}