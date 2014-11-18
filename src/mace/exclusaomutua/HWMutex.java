package mace.exclusaomutua;

public class HWMutex implements Bloqueio {
	
	TestAndSet flagBloqueio = new TestAndSet();
	
	public void requisitarSecaoCritica(int i) {	// protocolo de entrada
		while (flagBloqueio.testAndSet(1) == 1);
	}
	
	public void liberarSecaoCritica(int i) {	// protocolo de saida
		flagBloqueio.testAndSet(0);
	}
	
}