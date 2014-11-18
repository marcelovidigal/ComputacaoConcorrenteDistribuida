package mace.programacaodistribuida;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorDatagrama {
	
	public static void main(String[] args) {
		
		DatagramSocket datagramSocket = null;
		DatagramPacket datagramaEnvio, datagramaRecepcao;
		
		int porta = 2018;
		int tamanho = 1024;
		
		try {
			
			datagramSocket = new DatagramSocket(porta);
			byte[] buffer = new byte[tamanho];
			
			System.out.println("Servidor iniciado...");
			
			while (true) {
				try {
					datagramaRecepcao = new DatagramPacket(buffer, buffer.length);
					datagramSocket.receive(datagramaRecepcao);
					
					datagramaEnvio = new DatagramPacket(
						datagramaRecepcao.getData(),
						datagramaRecepcao.getLength(),
						datagramaRecepcao.getAddress(),
						datagramaRecepcao.getPort()
					);
					
					datagramSocket.send(datagramaEnvio);
					
				} catch (IOException e) {
					System.err.println(e);
				}
			}
		} catch (SocketException e) {
			System.err.println(e);
		} finally {
			datagramSocket.close();
		}
	}
	
}