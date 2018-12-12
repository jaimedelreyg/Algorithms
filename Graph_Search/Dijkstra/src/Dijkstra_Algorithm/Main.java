package Dijkstra_Algorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		File f = new File(args[0]);//fichero con el test
		Double numrep = Double.valueOf(args[1]);//numero de veces que se tiene que repetir el algoritmo
		Scanner sc = null;
		String[] values; 
		Double ptime;//tiempo antes de la ejecucion del algoritmo
		Double btime;//tiempo despues de la ejecucion del algoritmo
		Double ttime;//tiempo total de ejecucion
		Result result = null;//Resultado de ejecucion encapsulado en una clase
		int i = 0;
	
		/*
		 * Comienzo de la lextura del archivo de prueba
		 * 
		 * ##############################################
		 */
		
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer n = sc.nextInt();
		DirectedGraph g = new DirectedGraph(n);

		
		sc.nextLine();
		
		System.out.println("Cargando Archivo de pruebas " + f.toString() + " espere...");
		while(sc.hasNext()) {
			values = sc.nextLine().split(" +");
			g.addEdge(Integer.valueOf(values[0]),Integer.valueOf(values[1]), Integer.valueOf(values[2]));

		}
		
		System.out.println("Archivo Cargado!");
		
		/*
		 * #############################################
		 */
		
		/*
		 * Comienzo de la ejecucion del algoritmo con array
		 * 
		 * ################################################
		 */
		
		ptime = Double.valueOf(System.currentTimeMillis()); //medicion antes del algoritmo
		
		System.out.println("APLICANDO ALGORITMO CON ARRAY");
		for(i = 0; i<numrep ; i++) {
		result = dijkstraCostArray(g, n);
		if(i%100 == 0) {System.out.println("Aplicando algoritmo "+ numrep + " veces: " + i/10 + "%");}
		}
		
		btime = Double.valueOf(System.currentTimeMillis());	//medicion despues del algoritmo
		ttime = (btime - ptime)/numrep; //tiempo total dividido por el numero de repeticiones
		
		/*
		 * ##############################################
		 */
		
		
		/*
		 * Comienzo de la escritura de resultados en el fichero de salida ArrayResults.txt y Log.txt
		 */
		
		try {
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("ArrayResults.txt",true), "UTF-8");
			BufferedWriter bufWriter = new BufferedWriter(writer);
			bufWriter.write(n.toString()+" "+ ttime);
			bufWriter.newLine();
			writer.flush();
			bufWriter.flush();
			writer.close();
			bufWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		try {
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("Log.txt",true), "UTF-8");
			BufferedWriter bufWriter = new BufferedWriter(writer);
			bufWriter.write("----------------------------- \n");
			bufWriter.write("Ejecucion de Array Dijkstra del archivo: " + f.toString() + " Repeticiones para calculo de tiempo: " + numrep + "\n");

			for(i = 0; i < n; i++) {
				bufWriter.write("Nodo: "+ i + " Coste del camino: " + result.getMinCost()[i] + " Previous Node: " + result.getPreviousNode()[i] + "\n");				
			}
			writer.flush();
			bufWriter.flush();
			writer.close();
			bufWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		/*
		 * ##############################################
		 */
		
		/*
		 * Comienzo de la ejecucion del algoritmo con monticulo
		 * 
		 * ################################################
		 */
		
		System.out.println("APLICANDO ALGORITMO CON HEAP");
		ptime = Double.valueOf(System.currentTimeMillis());
		
		for(i = 0; i<numrep ; i++) {
		result = dijkstraPriorityHeap(g, n);
		if(i%100 == 0) {System.out.println("Aplicando algoritmo "+ numrep + " veces: " + i/10 + "%");}
		}
		
		btime = Double.valueOf(System.currentTimeMillis());	
		ttime = (btime - ptime)/numrep;
		
		/*
		 * ##############################################
		 */
		
		/*
		 * Comienzo de la escritura de resultados en el fichero de salida HeapResults.txt y Log.txt
		 */
		
		try {
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("HeapResults.txt",true), "UTF-8");
			BufferedWriter bufWriter = new BufferedWriter(writer);
			bufWriter.write(n.toString()+" "+ ttime);
			bufWriter.newLine();
			writer.flush();
			bufWriter.flush();
			writer.close();
			bufWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		try {
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("Log.txt",true), "UTF-8");
			BufferedWriter bufWriter = new BufferedWriter(writer);
			bufWriter.write("----------------------------- \n");
			bufWriter.write("Ejecucion de Heap Dijkstra del archivo: " + f.toString() + " Repeticiones para calculo de tiempo: " + numrep + "\n");
			for(i = 0; i < n; i++) {
				bufWriter.write("Nodo: "+ i + " Coste del camino: " + result.getMinCost()[i] + " Previous Node: " + result.getPreviousNode()[i] + "\n");				
			}
			writer.flush();
			bufWriter.flush();
			writer.close();
			bufWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		/*
		 * ##############################################
		 */


	}
	
	/*
	 * dijkstraCostArray: implementacion del algoritmo con array de costes
	 */
	

	public static Result dijkstraCostArray(DirectedGraph g,int v){

		Double[] mincost = new Double[v]; // array de costes
		Integer[] previousnode = new Integer[v]; //array con nodos anteriores al de la posicion en el array
		ArrayList<Integer> candidates = new ArrayList<Integer>(v); //lista con candidatos en el grafo por visitar
		int n = g.getV();

		//inicializamos los valores del nodo origen
		mincost[0] = 0.0;
		previousnode[0] = -1;
		candidates.add(0);
		
		//iniciamos el resto de nodos con coste infinito en el array y los añadimos como candidatos
		for(int i = 1; i < n; i++) {


			candidates.add(i);
			mincost[i] = Double.POSITIVE_INFINITY;
			previousnode[i]= -1;

		}
		
		/*
		 * 1. Se ejecuta el bloque mientras la lista candidatos tiene nodos
		 * 2. Busca dentro de la lista de candidatos el nodo con el menor coste del camino O(n)
		 * 3. Elimina el nodo seleccionado de la lista de candidatos
		 * 4. Mira los adyacentes de dicho nodo O(n)
		 * 5. Actualiza el camino a los nodos adyacentes si su valor es menor que el disponible actualmente y modifica el nodo anterior
		 * 
		 */

		while(!candidates.isEmpty()){
			
			Double d = Double.POSITIVE_INFINITY;
			Integer i = null;
			
			for(Integer j : candidates) {
				
				if(mincost[j] < d) {
					d = mincost[j];
					i = j;
				}
				
			}
				
			candidates.remove(Integer.valueOf(i));
			
			for (Node x:g.getAdj(i)) {

				if(mincost[i] + x.getPathCost() < mincost[x.getKey()]) {
	
						mincost[x.getKey()] = mincost[i] + x.getPathCost();
						previousnode[x.getKey()] = i;
	
					}
				}
			}
		


		return new Result(mincost,previousnode); //devuelve el resultado
	}
	
	/*
	 * dijkstraPriorityHeap: implementacion del algoritmo con monticulo
	 */
	
	public static Result dijkstraPriorityHeap(DirectedGraph g,int v){

		Double[] mincost = new Double[v];//array de costes
		Integer[] previousnode = new Integer[v];//array con nodos previos
		Integer[] heaposition = new Integer[v];//array con la posicion en el monticulo de cada elemento del grafo, la posicion 0 corresponde con el nodo 0, etc.
		IWilliamsHeap<Node> candidates = new WilliamsHeap(v);//lista de candidatos
		Node aux;
		
		//inicializacion del coste y nodo anterior del primer nodo
		mincost[0] = 0.0;
		previousnode[0] = -1;
		heaposition = candidates.insert(new Node(0,0),heaposition);//mete el nodo en candidatos
		
		//inicia el resto de nodos
		for(int i = 1; i < v; i++) {

			heaposition = candidates.insert(new Node(i,Double.POSITIVE_INFINITY),heaposition);
			mincost[i] = Double.POSITIVE_INFINITY;
			previousnode[i]=-1;

		}
		
		/*
		 * 1.Mientras el monticulo no este vacio el bucle se ejecuta
		 * 2.Saca el minimo del monticulo y itera sus adyacentes
		 * 3. Actualiza el camino a los nodos adyacentes si su valor es menor que el disponible actualmente y modifica el nodo anterior
		 * 4. Ademas se actualizan posiciones que habran cambiado debido al metodo decrecer clave
		 */
		while((aux = candidates.min()) != null){

			Integer i = aux.getKey();
			
			for (Node x:g.getAdj(i)) {

				if(mincost[i] + x.getPathCost() < mincost[x.getKey()]) {
					
					mincost[x.getKey()] = mincost[i] + x.getPathCost();
					heaposition = candidates.decreaseKey(heaposition[x.getKey()], mincost[i] + x.getPathCost(),heaposition);
					previousnode[x.getKey()] = i;			
				}
			}
			
			heaposition = candidates.elimMin(heaposition);//Se elimina el nodo de los candidatos y se actualizan posiciones
		}
		
		return new Result(mincost,previousnode);
	}
	

}
