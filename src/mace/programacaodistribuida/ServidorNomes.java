package mace.programacaodistribuida;

import java.net.*;
import java.io.*;
import java.util.*;

import mace.classesutilitarias.*;

public class ServidorNomes {
	
	TabelaNomes tabela;
	
	public ServidorNomes() {
		tabela = new TabelaNomes();
	}
	
	void processarCliente(Socket cliente) {
		try {
			
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintWriter saida = new PrintWriter(cliente.getOutputStream());
			
			String linha = entrada.readLine();
			StringTokenizer stringTokenizer = new StringTokenizer(linha);
			String tag = stringTokenizer.nextToken();
			
			if (tag.equals("buscar")) {
				
				int indice = tabela.buscar(stringTokenizer.nextToken());
				
				if (indice == -1) // nao localizado
					saida.println(- 1 + " " + " host nao encontrado ");
				else
					saida.println(tabela.getPorta(indice) + " " + tabela.getNomeHost(indice));
				
			} else if (tag.equals("inserir")) {
				
				String nome = stringTokenizer.nextToken();
				String nomeHost = stringTokenizer.nextToken();
				int porta = Integer.parseInt(stringTokenizer.nextToken());
				
				int retorno = tabela.inserir(nome, nomeHost, porta);
				
				saida.println(retorno);
			}
			
			saida.flush();
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		ServidorNomes servidorNomes = new ServidorNomes();
		
		System.out.println("Servidor de nomes iniciado: ");
		
		try {
			
			serverSocket = new ServerSocket(Constantes.PORTA_SERVIDOR);
			
			while (true) {
				Socket cliente = serverSocket.accept();
				servidorNomes.processarCliente(cliente);
				cliente.close();
			}
		} catch (IOException e) {
			System.err.println("Servidor finalizado: " + e);
		}
	}
	
}