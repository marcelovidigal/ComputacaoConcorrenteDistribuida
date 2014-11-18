package mace.sincronizacao;

public class DLCelula {
	int valor;
	
	public synchronized int getValor() {
		return valor;
	}
	
	public synchronized void setValor(int i) {
		valor = i;
	}
	
	public synchronized void trocar(DLCelula celula) {
		int temp = getValor();
		setValor(celula.getValor());
		celula.setValor(temp);
	}
	
}