package mace.exclusaomutua;

import java.util.Random;
//import java.util.logging.FileHandler;
//import java.util.logging.Logger;
//import java.util.logging.SimpleFormatter;

import mace.classesutilitarias.*;

public class SegmentoProcessamento extends Thread {
	
	int id;
	Bloqueio bloqueio;
	Random random = new Random();
	
	public SegmentoProcessamento(int id, Bloqueio bloqueio) {
		this.id = id;
		this.bloqueio = bloqueio;
	}
	
	void entrouSecaoCritica() {
		System.out.println(id + " esta na SC ***** ");
		// secao critica
		Util.mySleep(random.nextInt(1000));
	}
	
	void saiuSecaoCritica() {
		System.out.println(id + " nao esta na SC ") ;
		Util.mySleep(random.nextInt(1000));
	}
	
	public void run() {
		while (true) {
			bloqueio.requisitarSecaoCritica(id);
			entrouSecaoCritica();
			bloqueio.liberarSecaoCritica(id);
			saiuSecaoCritica();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		//Logger log = Logger.getLogger("exclusaomutua");
		//FileHandler fileHandler = new FileHandler("exclusaomutua.log");
		//SimpleFormatter simpleFormatter = new SimpleFormatter();  
		//fileHandler.setFormatter(simpleFormatter);
		
		//log.addHandler(fileHandler);
		//log.info("iniciando a aplicacao");
		
		SegmentoProcessamento[] thread;
		int n = Integer.parseInt(args[0]);
		thread = new SegmentoProcessamento[n];
		
		//Bloqueio bloqueio = new Bakery(n);	// ou qualquer outro algoritmo mutex
		//Bloqueio bloqueio = new Peterson();
		Bloqueio bloqueio = new HWMutex();
		
		for (int i = 0; i < n; i++) {
			thread[i] = new SegmentoProcessamento(i, bloqueio);
			thread[i].start();
		}
	}
	
}