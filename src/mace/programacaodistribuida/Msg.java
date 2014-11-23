package mace.programacaodistribuida;

import java.util.*;

public class Msg {
	
	int origem, destino;
	String tag;
	String buffer;
	
	public Msg(int origem, int destino, String tag, String buffer) {
		this.origem = origem;
		this.destino = destino;
		this.tag = tag;
		this.buffer = buffer;
	}
	
	public int getOrigem() {
		return origem;
	}
	
	public int getDestino() {
		return destino;
	}
	
	public String getTag() {
		return tag;
	}
	
	public String getBuffer() {
		return buffer;
	}
	
	public int getMsgInt() {
		
		StringTokenizer stringTokenizer = new StringTokenizer(buffer);
		
		return Integer.parseInt(stringTokenizer.nextToken());
	}
	
	public static Msg parseMsg(StringTokenizer stringTokenizer) {
		int origem = Integer.parseInt(stringTokenizer.nextToken());
		int destino = Integer.parseInt(stringTokenizer.nextToken());
		String tag = stringTokenizer.nextToken();
		String buffer = stringTokenizer.nextToken("#") ;
		
		return new Msg(origem, destino, tag, buffer);
	}
	
	public String toString() {
		
		String s =	String.valueOf(origem) + " " + 
					String.valueOf(destino) + " " + 
					tag + " " + 
					buffer + "#";
		
		return s;
	}
	
}