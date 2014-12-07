package mace.relogios;

import mace.classesutilitarias.Util;

public class RelogioLamport {
	
	int r;
	
	public RelogioLamport() {
		r = 1;
	}
	
	public int getValor() {
		return r;
	}
	
	public void tick() { // em eventos internos
		r = r + 1;
	}
	
	public void enviar() {
		// inclui r na mensagem
		r = r + 1;
	}
	
	public void receber(int origem, int valor) {
		r = Util.max(r, valor) + 1;
	}
	
}