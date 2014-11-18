package mace.sincronizacao;

class Filosofo implements Runnable {
	
	int id = 0;
	Recurso recurso = null;
	
	public Filosofo(int id, Recurso recurso) {
		
		this.id = id;
		this.recurso = recurso;
		
		new Thread(this).start();
	}
	
	public void run() {
		while (true) {
			try {
				System.out.println(" Filosofo " + id + " pensando ");
				Thread.sleep(30);
				System.out.println(" Filosofo " + id + " com fome ");
				recurso.obter(id);
				System.out.println(" Filosofo " + id + " comendo ") ;
				Thread.sleep(40);
				recurso.liberar(id);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
}