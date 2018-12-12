package Dijkstra_Algorithm;

import java.util.LinkedList;

/*Implementación de un grafo dirigido con costes*/

public class DirectedGraph {
	
	private int v; //numero de nodos
	private LinkedList<LinkedList<Node>> adj; //lista de adyacencia 
	
	public DirectedGraph(int v) {
		
		this.v = v;
		adj = new LinkedList<LinkedList<Node>>();
		//Se rellena la lista de adyacencia con listas para cada nodo
		for(int i = 0; i < v; i++) {
			
			adj.add(new LinkedList<Node>());
			
		}
		
	}
	
	/*AddEdge: Añade un vertice a la estructura*/
	
	public void addEdge(int v, int next,double cost) {
		
		adj.get(v).add(new Node(next,cost));
		
	}
	
	/*getV: devuelve el numero de nodos*/
	
	public int getV() {
		return v;
	}
	
	/*LinkedList: Devuelve la lista de adyacentes del nodo v*/
	
	public LinkedList<Node> getAdj(int v){
		
		return adj.get(v);
	}

}
