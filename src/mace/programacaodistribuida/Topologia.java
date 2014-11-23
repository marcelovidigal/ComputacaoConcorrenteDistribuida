package mace.programacaodistribuida;

import java.io.*;
import java.util.*;

import mace.classesutilitarias.*;

public class Topologia {
	
	public static void lerVizinhos(int id, int n, IntLinkedList vizinhos) {
		
		//Util.println("Lendo topologia...");
		System.out.println("Lendo topologia...");
		
		try {
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader("topologia" + id));
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			while (stringTokenizer.hasMoreTokens()) {
				int vizinho = Integer.parseInt(stringTokenizer.nextToken());
				vizinhos.adicionar(vizinho);
			}
			
			bufferedReader.close();
			
		} catch (FileNotFoundException e) {
			for (int i = 0; i < n; i++)
				if (i != id)
					vizinhos.adicionar(i);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		//Util.println(neighbors.toString());
		System.out.println("[" + id + "]" + vizinhos.toString());
	}
	
}