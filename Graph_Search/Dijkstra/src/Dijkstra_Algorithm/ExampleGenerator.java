package Dijkstra_Algorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

/*Generador de grafos conexos con un numero nodos/2 de aristas aproximadamente*/

public class ExampleGenerator {

public static void main(String[] args) {
		
		Random r = new Random();
		int nodes = Integer.valueOf(args[1]);//numero de nodos
		int maxcostpath = Integer.valueOf(args[2]);//maximo coste del camino
//		int maxedges = Integer.valueOf(args[3]);
		ArrayList<TreeSet<Integer>> excludenumbers = new ArrayList<TreeSet<Integer>>(nodes); //nodos a los que un nodo determinado ya no puede apuntar para evitar que dos nodos se apunten entre si
		File f = new File(args[0]);//archivo de salida
		Integer randomnode;
		TreeSet<Integer> numberswoedge = new TreeSet<Integer>();//lista con nodos que aun no han sido apuntados por nadie
		
//		if(maxedges > nodes - 3) {
//			maxedges = nodes - 3;
//		}
		
		//Se añaden todos a la lista de no apuntados por nadie
		for(int i = 0; i < nodes; i++) {
			numberswoedge.add(i);
		}
		
		//se inicia las listas de nodos excluidos
		for(int i = 0; i < nodes; i++) {
			excludenumbers.add(new TreeSet<Integer>());
		}

		try {

			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			BufferedWriter bufWriter = new BufferedWriter(writer);
			bufWriter.write(String.valueOf(nodes));
			bufWriter.newLine();

			/*1. Para cada nodo se elige de forma aleatoria un numero de aristas
			 *2. Se obtiene de forma aleatoria un nodo posible que no este en la lista de excluidos
			 *3. Se elimina el nodo de nodos no apuntados
			 *4. Se mete en la lista de excluidos al nuevo nodo y viceversa
			 */
			for(int i = 0; i < nodes; i++) {
			
			excludenumbers.get(i).add(i);
				
				for(int j = 0 ; j < r.nextInt(nodes/2) + 1;j++) {
					

						randomnode = randomExcluding(excludenumbers.get(i),nodes);
						bufWriter.write(i + " " + randomnode + " " + r.nextInt(maxcostpath));
						numberswoedge.remove(randomnode);
						bufWriter.newLine();
						excludenumbers.get(randomnode).add(i);
						excludenumbers.get(i).add(randomnode);
					
				}

			}

			/*
			 * Una vez generado de forma aleatoria el grafo se procede a enlazar aquellos nodos que por aleatoriedad
			 * han quedado sin ser apuntados por nadie.
			 */
			
			for(Integer j : numberswoedge) {
				
				randomnode = randomExcluding(excludenumbers.get(j),nodes);
				bufWriter.write(randomnode + " " + j + " " + r.nextInt(maxcostpath));
				bufWriter.newLine();
				excludenumbers.get(randomnode).add(j);
				excludenumbers.get(j).add(randomnode);
				
			}
			
			bufWriter.flush();
			writer.flush();
			bufWriter.close();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

/*
 * randomExcluding: Busca un aleatorio que no este en una cierta lista de excluidos
 */

public static Integer randomExcluding(TreeSet<Integer> excludenumbers,int nodes) {
	
	Random r = new Random();
	int randomnum = r.nextInt(nodes);
		while(excludenumbers.contains(Integer.valueOf(randomnum))) {
			randomnum = r.nextInt(nodes);
		}
	
	return randomnum;	
}
	

}

