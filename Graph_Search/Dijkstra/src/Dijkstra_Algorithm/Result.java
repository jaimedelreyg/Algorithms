package Dijkstra_Algorithm;

/*
 * Clase contenedora del resultado de los algoritmos
 */

public class Result {
	
	private Double[] mincost;
	private Integer[] previousnode;
	
	public Result(Double[] mincost, Integer[] previousnode) {
		this.mincost = mincost;
		this. previousnode = previousnode;
	}
	
	public Double[] getMinCost() {
		return mincost;
	}
	
	public Integer[] getPreviousNode() {
		return previousnode;
	}
}
