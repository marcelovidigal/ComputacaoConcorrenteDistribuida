package mace.intro;

class Foo {
	
	String nome;
	
	public Foo(String nome) {
		this.nome = nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
}

class FooBar extends Foo implements Runnable {
	
	public FooBar(String nome) {
		super(nome);
	}
	
	public void run() {
		for (int i = 0; i < 1000; i++)
			System.out.println(getNome() + " : Ola Mundo");
	}
	
	public static void main(String[] args) {
		
		FooBar f1 = new FooBar("Romeu");
		Thread tl = new Thread(f1);
		tl.start();
		
		FooBar f2 = new FooBar("Julieta");
		Thread t2 = new Thread(f2);
		t2.start();
		
	}
	
}