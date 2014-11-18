package mace.sincronizacao;

import mace.classesutilitarias.Util;

public class JantarFilosofosMonitor implements Recurso {
	
	int n = 0;
	int[] situacao = null;
	
	static final int PENSANDO = 0, COM_FOME = 1, COMENDO = 2;
	
	public JantarFilosofosMonitor (int n) {
		
		this.n = n;
		situacao = new int[n];
		
		for (int i = 0; i < n; i++)
			situacao[i] = PENSANDO;
	}
	
	int obterEsquerda(int i) {
		return (n + i - 1) % n;
	}
	
	int obterDireita(int i) {
		return (i + 1) % n;
	}
	
	public synchronized void obter(int i) {
		
		situacao[i] = COM_FOME;
		verificar(i);
		
		while (situacao[i] != COMENDO)
			Util.myWait(this);
	}
	
	public synchronized void liberar(int i) {	
		situacao[i] = PENSANDO;
		verificar(obterEsquerda(i));
		verificar(obterDireita(i));
	}
	
	void verificar(int i) {
		if ((situacao[obterEsquerda(i)] != COMENDO) && (situacao[i] == COM_FOME) && (situacao[obterDireita(i)] != COMENDO)) {
			situacao[i] = COMENDO;
			notifyAll();
		}
	}
	
	public static void main(String[] args) {
		JantarFilosofosMonitor jantarFilosofosMonitor = new JantarFilosofosMonitor(5);
		
		for (int i = 0; i < 5; i++)
			new Filosofo(i, jantarFilosofosMonitor);
	}
	
}