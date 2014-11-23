package mace.programacaodistribuida;

import java.util.*;
import java.io.*;

import mace.classesutilitarias.*;

public class Linker {
	
	BufferedReader[] entradas;
	PrintWriter[] saidas;
	
	BufferedReader entrada;
	
	int id, n;
	
	Conexao conexao;
	
	public IntLinkedList vizinhos = new IntLinkedList();
	
	public Linker(String nome, int id , int totalConexoes) throws Exception {
		
		this.id = id;
		this.n = totalConexoes;
		
		entradas = new BufferedReader[totalConexoes];
		saidas = new PrintWriter[totalConexoes];
		
		Topologia.lerVizinhos(id, n, vizinhos);
		
		conexao = new Conexao();
		conexao.conectar(nome, id, totalConexoes, entradas, saidas);
	}
	
	public void enviarMsg(int destino, String tag, String msg) {
		saidas[destino].println(id + " " + destino + " " + tag + " " + msg + "#"); 
		saidas[destino].flush();
	}
	
	public void enviarMsg(int destino, String tag) {
		enviarMsg(destino, tag, " 0 ");
	}
	
	public void multicast(IntLinkedList destinos, String tag, String msg) {
		for (int i = 0; i < destinos.size(); i++) {
			enviarMsg(destinos.getItem(i), tag, msg);
		}
	}
	
	public Msg receberMsg(int origem) throws IOException {
		
		String linha = entradas[origem].readLine();
		
		//Util.println(" mensagem recebida " + linha);
		System.out.println(" mensagem recebida " + linha);
		
		StringTokenizer stringTokenizer = new StringTokenizer(linha);
		origem = Integer.parseInt(stringTokenizer.nextToken());
		int destino = Integer.parseInt(stringTokenizer.nextToken());
		String tag = stringTokenizer.nextToken();
		String msg = stringTokenizer.nextToken("#");
		
		return new Msg(origem, destino, tag, msg);
	}
	
	public int getId() { 
		return id;
	}
	
	public int getTotalConexoes() { 
		return n;
	}
	
	public void fechar() { 
		conexao.fecharSockets();
	}
	
	public static void main(String[] args) {
		try {
			//int id = Integer.parseInt(args[0]);
			//int totalConexoes = Integer.parseInt(args[1]);
			
			int totalConexoes = Integer.parseInt(args[0]);
			
			//Linker linker = new Linker("localhost", id, totalConexoes);
			
			for (int i = 0; i < totalConexoes; i++) {
				
				final int id = i;
				
				new Thread(new Runnable() {
					public void run() {
						
						try {
							
							Linker linker = new Linker("localhost", id, totalConexoes);
							
							while (true) {
								
								for (int i = 0; i < totalConexoes; i++)
									if (i != id) {
										linker.enviarMsg(i, "msg", "msg de " + id + " para " + i);
										System.out.println(linker.receberMsg(i));
									}
								
								Thread.sleep(1000);
							}
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
			}
			
			/*
			for (int i = 0; i < totalConexoes; i++)
				if (i != id)
					linker.enviarMsg(i, "msg", "msg de " + id + " para " + i);
			
			for (int i = 0; i < totalConexoes; i++)
				if (i != id)
					System.out.println(linker.receberMsg(i));
			*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}