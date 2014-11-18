package mace.sincronizacao;

import mace.classesutilitarias.Util;

public class BufferLimitadoMonitor {
	
	final int tamanhoBuffer = 10;
	double[] buffer = new double[tamanhoBuffer];
	int indiceArmazenamento = 0, indiceRecuperacao = 0, contador = 0;
	
	public synchronized void armazenar(double valor) {
		while (contador == tamanhoBuffer) // buffer cheio
			Util.myWait(this);
		
		buffer[indiceArmazenamento] = valor;
		indiceArmazenamento = (indiceArmazenamento + 1) % tamanhoBuffer;
		contador++;
		
		if (contador == 1) // itens disponiveis para consumo
			notify();
	}
	
	public synchronized double recuperar() {
		
		double valor;
		
		while (contador == 0) // buffer vazio
			Util.myWait(this);
		
		valor = buffer[indiceRecuperacao];
		indiceRecuperacao = (indiceRecuperacao + 1 ) % tamanhoBuffer;
		contador--;
		
		if (contador == tamanhoBuffer - 1) // slots vazios disponiveis
			notify();
		
		return valor;
	}
	
}