package mace.sincronizacao;

import mace.classesutilitarias.Util;

public class SemaforoContador {
	
	int valor;
	
	public SemaforoContador(int valorInicial) {
		valor = valorInicial;
	}
	
	public synchronized void P() {
		
		valor--;
		
		if (valor < 0)
			Util.myWait(this);
	}
	
	public synchronized void V() {
		
		valor++;
		
		if (valor <= 0)
			notify();
	}
	
}