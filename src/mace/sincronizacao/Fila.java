package mace.sincronizacao;

import mace.classesutilitarias.Util;

public class Fila {
	
	class No {
		public String dados;
		public No proximo;
	}
	
	No inicio = null, fim = null;
	
	public synchronized void enfileirar(String dados) {
		
		No temp = new No();
		temp.dados = dados;
		temp.proximo = null;
		
		if (fim == null) {
			fim = temp;
			inicio = fim;
		} else {
			fim.proximo = temp;
			fim = temp;
		}
		
		notify();
	}
	
	public synchronized String desenfileirar() {
		while (inicio == null)
			Util.myWait(this);
		
		String retorno = inicio.dados;
		inicio = inicio.proximo;
		
		return retorno;
	}
	
}