package mace.sincronizacao;

import mace.classesutilitarias.Util;

public class SemaforoBinario {
	
	boolean valor;
	
	SemaforoBinario(boolean valorInicial) {
		valor = valorInicial;
	}
	
	public synchronized void P() {
		while (valor == false)
			Util.myWait(this); // na fila de processos bloqueados
		
		valor = false;
	}
	
	public synchronized void V() {
		valor = true;
		notify();
	}
	
}