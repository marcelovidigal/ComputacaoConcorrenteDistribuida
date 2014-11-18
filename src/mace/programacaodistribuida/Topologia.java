package mace.programacaodistribuida;

import java.io.*;
import java.util.*;

import mace.classesutilitarias.*;

public class Topologia {
	
	public static void lerVizinhos(int myId, int N, IntLinkedList neighbors) {
		
		Util.println("Lendo topologia");
		
		try {
			
			BufferedReader dIn = new BufferedReader(new FileReader("topologia" + myId));
			StringTokenizer st = new StringTokenizer(dIn.readLine());
			
			while (st.hasMoreTokens()) {
				int neighbor = Integer.parseInt(st.nextToken());
				neighbors.add(neighbor);
			}
		} catch (FileNotFoundException e) {
			for (int j = 0; j < N; j++)
				if (j != myId)
					neighbors.add(j);
		} catch (IOException e) {
			System.err.println(e);
		}
	
		Util.println(neighbors.toString());
	}
	
}