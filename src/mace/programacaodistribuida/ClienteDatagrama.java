package mace.programacaodistribuida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteDatagrama {
	
	public static void main(String[] args) {
		
		String hostname;
		
		int porta = 2018;
		int tamanho = 1024;
		
		DatagramSocket datagramSocket = null;
		DatagramPacket datagramaEnvio, datagramaRecepcao;
		
		if (args.length > 0)
			hostname = args[0];
		else
			hostname = "localhost";
		
		try {
			
			InetAddress inetAddress = InetAddress.getByName(hostname);
			datagramSocket = new DatagramSocket();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			
			while (true) {
				try {
					
					String entrada = bufferedReader.readLine();
					
					if (entrada.equals("fim")) 
						break;
					
					byte[] buffer = new byte[entrada.length()];
					buffer = entrada.getBytes();
					datagramaEnvio = new DatagramPacket(buffer, buffer.length, inetAddress, porta);
					datagramSocket.send(datagramaEnvio);
					
					buffer = new byte[tamanho];
					datagramaRecepcao = new DatagramPacket(buffer, buffer.length);
					datagramSocket.receive(datagramaRecepcao);
					String echo = new String(datagramaRecepcao.getData(), 0 , datagramaRecepcao.getLength());
					
					System.out.println(echo);
					
				} catch (IOException e) {
					System.err.println(e);
				}
			}
		} catch (UnknownHostException e) {
			System.err.println(e);
		} catch (SocketException e) {
			System.err.println(e);
		} finally {
			datagramSocket.close();
		}
	}
	
}