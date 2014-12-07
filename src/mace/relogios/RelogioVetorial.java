package mace.relogios;

import mace.classesutilitarias.Util;

public class RelogioVetorial {
	
	public int[] r;
	
	int id;
	int n;
	
	public RelogioVetorial(int quantidadeProcessos, int id) {
		this.id = id;
		n = quantidadeProcessos;
		r = new int[quantidadeProcessos];
		
		for (int i = 0 ; i < n; i++) 
			r[i] = 0;
		
		r[this.id] = 1;
	}
	
	public void tick() {
		r[id]++;
	}
	
	public void enviar() {
		// inclui o vetor na mensagem
		r[id]++;
	}
	
	public void receber(int[] valor) {
		for (int i = 0 ; i < n; i++)
			r[i] = Util.max(r[i], valor[i]) ;
		
		r[id]++;
	}
	
	public int getValor(int i) {
		return r[i];
	}
	
	public String tostring() {
		return Util.writeArray(r);
	}
	
}