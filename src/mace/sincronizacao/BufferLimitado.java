package mace.sincronizacao;

class BufferLimitado {
	
	final int tamanho = 10;
	double[] buffer = new double[tamanho];
	int indiceArmazenamento = 0, indiceRecuperacao = 0;
	
	SemaforoBinario mutex = new SemaforoBinario(true);
	SemaforoContador estaVazio = new SemaforoContador(0);
	SemaforoContador estaCheio = new SemaforoContador(tamanho);
	
	public void armazenar(double valor) {
		estaCheio.P();			// wait if buffer is full
		mutex.P();				// ensures mutual ezclusion
		buffer[indiceArmazenamento] = valor;	// update the buffer
		indiceArmazenamento = (indiceArmazenamento + 1) % tamanho;
		mutex.V();
		estaVazio.V();			// notify any waiting consumer
	}
	
	public double recuperar() {
		
		double valor;
		
		estaVazio.P();			// wait if bufferis empty
		mutex.P();				// ensures mutual ezclusion
		valor = buffer[indiceRecuperacao]; // read from buffer
		indiceRecuperacao = (indiceRecuperacao + 1) % tamanho;
		mutex.V();
		estaCheio.V();			// notify any waiting producer
		
		return valor;
	}
	
}