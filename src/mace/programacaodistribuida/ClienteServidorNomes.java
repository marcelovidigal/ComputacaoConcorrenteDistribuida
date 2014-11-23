package mace.programacaodistribuida;

import java.net.*;
import java.io.*;
import java.util.*;

import mace.classesutilitarias.*;

public class ClienteServidorNomes {
	
	Socket servidor = null;
	
	BufferedReader entrada;
	PrintStream saida;
	
	public void getSocket() throws IOException {
		
		servidor = new Socket(Constantes.NOME_SERVIDOR, Constantes.PORTA_SERVIDOR);
		
		entrada = new BufferedReader(new InputStreamReader(servidor.getInputStream()));
		saida = new PrintStream(servidor.getOutputStream());
	}
	
	public int inserirNome(String nome, String ip, int porta) throws IOException {
		
		getSocket();
		
		saida.println("inserir " + nome + " " + ip + " " + porta);
		saida.flush();
		
		return Integer.parseInt(entrada.readLine());
	}
	
	public Endereco buscarNome(String nome) throws IOException {
		
		getSocket();
		
		saida.println("buscar " + nome);
		saida.flush();
		
		String resultado = entrada.readLine();
		
		StringTokenizer stringTokenizer = new StringTokenizer(resultado);
		
		String ip = "-1";
		int porta = -1;
		
		if (stringTokenizer.countTokens() == 3) {
			nome = stringTokenizer.nextToken();
			ip = stringTokenizer.nextToken();
			porta = Integer.parseInt(stringTokenizer.nextToken());
		} else {
			
			nome = "";
			
			while (stringTokenizer.hasMoreTokens())
				nome += stringTokenizer.nextToken() + " "; 
		}
		
		return new Endereco(nome.trim(), ip, porta);
	}
	
	public static void main(String[] args) {
		
		ClienteServidorNomes clienteServidorNomes = new ClienteServidorNomes();
		
		try {
			clienteServidorNomes.inserirNome("localhost1", "127.0.0.1", 1000);
			clienteServidorNomes.inserirNome("localhost2", "0.0.0.0", 1001);
			clienteServidorNomes.inserirNome("localhost3", "192.168.1.4", 1002);
			
			Endereco endereco = clienteServidorNomes.buscarNome("localhost1");
			System.out.println(endereco.getNome() + ":" + endereco.getIp() + ":" + endereco.getPorta());
			
			endereco = clienteServidorNomes.buscarNome("localhot2");
			System.out.println(endereco.getNome() + ":" + endereco.getIp() + ":" + endereco.getPorta());
			
			endereco = clienteServidorNomes.buscarNome("localhost3");
			System.out.println(endereco.getNome() + ":" + endereco.getIp() + ":" + endereco.getPorta());
			
		} catch (Exception e) {
			System.err.println("Servidor encerrado!" + e);
		}
	}
	
}