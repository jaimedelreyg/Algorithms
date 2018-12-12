package Dijkstra_Algorithm;

/*
 * Clase Nodo que encapsula toda la informacion
 */

public class Node {
	
private Integer key; //Clave del nodo
private double pathcost; //Coste del camino hasta el nodo

public Node (Integer key, double pathcost) {
	this.key = key;
	this.pathcost = pathcost;
}

public Integer getKey() {
	
	return key;
}

public double getPathCost() {
	
	return pathcost;
}



}
