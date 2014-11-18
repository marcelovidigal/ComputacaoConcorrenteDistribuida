package mace.sincronizacao;

public class Celula {
	
	int valor;
	
	public synchronized int getValor() {
		return valor;
	}
	
	public synchronized void setValor(int i) {
		valor = i;
	}
	
	protected synchronized void executarTroca(Celula celula) {
		int temp = getValor();
		setValor(celula.getValor());
		celula.setValor(temp);
	}
	
	public void trocar(Celula celula) {
		if (this == celula)
			return;
		else if (System.identityHashCode(this) < System.identityHashCode(celula))
			executarTroca(celula);
		else
			celula.executarTroca(this);
	}
	
}