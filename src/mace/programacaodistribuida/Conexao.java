package mace.programacaodistribuida;

import java.util.*;
import java.net.*;
import java.io.*;

import mace.classesutilitarias.*;

public class Conexao {
	
	ServerSocket serverSocket;
	Socket[] conexoes;
	
	public void conectar(String nome, int id, int totalConexoes, BufferedReader[] entradas, PrintWriter[] saidas) throws Exception {
		
		ClienteServidorNomes clienteServidorNomes = new ClienteServidorNomes();
		
		conexoes = new Socket[totalConexoes];
		int porta = getPortaLocal(id);
		
		serverSocket = new ServerSocket(porta);
		
		// registra host no servidor de nomes
		clienteServidorNomes.inserirNome(nome + id, (InetAddress.getLocalHost()).getHostAddress(), porta);
		
		// aceita conecoes de todos os nos com ids menores
		for (int i = 0; i < id; i++) {
			
			Socket socket = serverSocket.accept();
			
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));		
			String linha = entrada.readLine();
			StringTokenizer stringTokenizer = new StringTokenizer(linha);
			
			int noOrigem = Integer.parseInt(stringTokenizer.nextToken());
			int noDestino = Integer.parseInt(stringTokenizer.nextToken());
			
			System.out.println("Conexao estabelecida [" + noOrigem + "]->[" + noDestino + "]!");
			
			String tag = stringTokenizer.nextToken();
			
			if (tag.equals("ola")) {
				conexoes[noOrigem] = socket;
				entradas[noOrigem] = entrada;
				saidas[noOrigem] = new PrintWriter(socket.getOutputStream());
				
				System.out.println("Mensagem [ola] recebida [" + noOrigem + "]->[" + noDestino + "]!");
			}
		}
		
		// conecta a todos os nos com ids maiores
		for (int i = id + 1; i < totalConexoes; i++) {
			
			Endereco endereco;
			
			do {
				endereco = clienteServidorNomes.buscarNome(nome + i);
				Thread.sleep(100);
			} while(endereco.getPorta() == -1);
			
			conexoes[i] = new Socket(endereco.getIp(), endereco.getPorta());
			saidas[i] = new PrintWriter(conexoes[i].getOutputStream());
			entradas[i] = new BufferedReader(new InputStreamReader(conexoes[i].getInputStream()));
			
			// envia uma mensagem de 'ola' para Pi
			saidas[i].println(id + " " + i + " " + " ola " + " " + " null ");
			saidas[i].flush();
			
			System.out.println("Mensagem [ola] enviada [" + id + "]->[" + i + "]!");
		}
	}
	
	int getPortaLocal(int id) {
		return Constantes.PORTA_SERVIDOR + 20 + id;
	}
	
	public void fecharSockets() {
		try {
			
			serverSocket.close();
			
			for (int i = 0; i < conexoes.length; i++)
				conexoes[i].close();
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
}