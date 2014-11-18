package mace.intro;

public class OlaMundoThread extends Thread {
	
	public void run() {
		System.out.println("Ola Mundo!");
	}
	
	public static void main(String[] args) {
		
		OlaMundoThread olaMundoThread1 = new OlaMundoThread();
		OlaMundoThread olaMundoThread2 = new OlaMundoThread();
		OlaMundoThread olaMundoThread3 = new OlaMundoThread();
		
		olaMundoThread1.start();
		olaMundoThread2.start();
		olaMundoThread3.start();
	}
	
}