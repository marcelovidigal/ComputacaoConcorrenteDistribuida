package mace.exclusaomutua;

class Peterson implements Bloqueio {
	
	volatile boolean[] secaoCritica = {false, false};
	volatile int turno = 1;
	
	public void requisitarSecaoCritica(int i) {
		int j = 1 - i;
		secaoCritica[i] = true;
		turno = j;
		while (secaoCritica[j] && (turno == j));
	}
	
	public void liberarSecaoCritica(int i) {
		secaoCritica[i] = false;
	}
	
}