package mace.exclusaomutua;

public class TestAndSet {
	
	int valor = -1;
	
	public synchronized int testAndSet(int valorNovo) {
		int valorAntigo = valor;
		valor = valorNovo;
		
		return valorAntigo;
	}
	
}