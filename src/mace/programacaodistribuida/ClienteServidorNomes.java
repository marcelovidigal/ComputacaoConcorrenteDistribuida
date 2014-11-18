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
	
	public int inserirNome(String nome, String nomeHost, int numeroPorta) throws IOException {
		
		getSocket();
		
		saida.println("inserir " + nome + " " + nomeHost + " " + numeroPorta);
		saida.flush();
		
		return Integer.parseInt(entrada.readLine());
	}
	
	public Endereco buscarNome(String nome) throws IOException {
		
		getSocket();
		
		saida.println("buscar " + nome);
		saida.flush();
		
		String resultado = entrada.readLine();
		
		StringTokenizer stringTokenizer = new StringTokenizer(resultado);
		int numeroPorta = Integer.parseInt(stringTokenizer.nextToken());
		String nomeHost = stringTokenizer.nextToken();
		
		return new Endereco(nomeHost, numeroPorta);
	}
	
	public static void main(String[] args) {
		
		ClienteServidorNomes clienteServidorNomes = new ClienteServidorNomes();
		
		try {
			clienteServidorNomes.inserirNome(" ola1 ", "localhost", 1000);
			clienteServidorNomes.inserirNome(" ola2 ", "localhost", 1001);
			clienteServidorNomes.inserirNome(" ola3 ", "localhost", 1002);
			
			Endereco endereco = clienteServidorNomes.buscarNome(" ola1 ");
			System.out.println(endereco.getNomeHost() + ":" + endereco.getPorta());
			
			endereco = clienteServidorNomes.buscarNome(" ola3 ");
			System.out.println(endereco.getNomeHost() + ":" + endereco.getPorta());
			
			endereco = clienteServidorNomes.buscarNome(" ola4 ");
			System.out.println(endereco.getNomeHost() + ":" + endereco.getPorta());
			
		} catch (Exception e) {
			System.err.println("Servidor finalizado: " + e);
		}
	}
	
}