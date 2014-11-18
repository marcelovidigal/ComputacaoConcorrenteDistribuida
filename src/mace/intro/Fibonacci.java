package mace.intro;

public class Fibonacci extends Thread {
	
	int n;
	int resultado;
	static int totalThreads = 0;
	
	public Fibonacci(int n) {
		
		this.n = n;
		
		totalThreads++;
		System.out.println("Executando com #" + totalThreads + " threads...");
	}
	
	public void run() {
		if ((n == 0) || (n == 1))
			resultado = 1;
		else {
			
			Fibonacci fl = new Fibonacci(n - 1);
			Fibonacci f2 = new Fibonacci(n - 2);
			
			fl.start();
			f2.start();
			
			try {
				fl.join();
				f2.join();				
			} catch (InterruptedException e) {};
			
			resultado = fl.getResultado() + f2.getResultado();
		}
	}
	
	public int getResultado() {
		
		totalThreads--;
		System.out.println("Executando com #" + totalThreads + " threads...");
		
		return resultado;
	}
	
	public static void main(String[] args) {
		
		Fibonacci fl = new Fibonacci(Integer.parseInt(args[0]));
		fl.start();
		
		try {
			fl.join();
		} catch (InterruptedException e) {};
		
		System.out.println("Resposta: " + fl.getResultado());
	}
	
}