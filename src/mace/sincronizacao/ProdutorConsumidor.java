package mace.sincronizacao;

import java.util.Random;

import mace.classesutilitarias.Util;

class Produtor implements Runnable {
	
	BufferLimitado bufferLimitado = null;
	//BufferLimitadoMonitor bufferLimitado = null;
	
	public Produtor(BufferLimitado bufferLimitado) {
	//public Produtor(BufferLimitadoMonitor bufferLimitado) {
		this.bufferLimitado = bufferLimitado;
		new Thread(this).start();
	}
	
	public void run() {
		
		double item;
		Random random = new Random();
		
		while (true) {
			item = random.nextDouble();
			System.out.println(" item produzido " + item);
			bufferLimitado.armazenar(item);
			Util.mySleep(250);
		}
	}
	
}

class Consumidor implements Runnable {
	
	BufferLimitado bufferLimitado = null;
	//BufferLimitadoMonitor bufferLimitado = null;
	
	public Consumidor(BufferLimitado bufferLimitado) {
	//public Consumidor(BufferLimitadoMonitor bufferLimitado) {
		this.bufferLimitado = bufferLimitado;
		new Thread(this).start();
	}
	
	public void run() {
		
		double item;
		
		while (true) {
			item = bufferLimitado.recuperar();
			System.out.println(" item consumido " + item);
			Util.mySleep(250);
		}
	}
	
}

class ProdutorConsumidor {
	
	public static void main(String [] args) {
		
		BufferLimitado bufferLimitado = new BufferLimitado();
		//BufferLimitadoMonitor bufferLimitado = new BufferLimitadoMonitor();
		Produtor produtor1 = new Produtor(bufferLimitado);
		Produtor produtor2 = new Produtor(bufferLimitado);
		Consumidor consumidor = new Consumidor(bufferLimitado);
	}
	
}