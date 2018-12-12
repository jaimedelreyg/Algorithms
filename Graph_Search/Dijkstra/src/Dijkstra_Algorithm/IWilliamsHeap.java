package Dijkstra_Algorithm;

/*
 * Interfaz para generalizar monticulo de Williams
 */

public interface IWilliamsHeap<T> {
	
	public Integer[] insert(T elem, Integer[] pos);
	public Integer[] up(int elem,Integer[] pos);
	public Integer[] elimMin(Integer[] pos);
	public T min();
	public Integer[] decreaseKey(int elem, Double pathCost,Integer[] pos);
	public Integer[] down(int elem,Integer[] pos);
}
