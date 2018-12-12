package Dijkstra_Algorithm;


/*
 * Implementacion del monticulo de Williams
 */

public class WilliamsHeap implements IWilliamsHeap<Node>{

	private Node array[];//array del monticulo
	private int k = 0;//puntero al ultimo nodo
	private int n;//numero de nodos maximo
	

	public WilliamsHeap(int n){

		array = new Node[n+1];
		this.n = n;
	}
	
	/*
	 *insert: inserta un elemento en el monticulo y devuelve un array informativo de la posicion de los nodos
	 */
	
	public Integer[] insert(Node elem, Integer[] pos) {
		
		if(k == n+1) {
			
			System.out.println("Monticulo lleno :(");
			return pos;
			
		}else {
			
			k++;
			array[k] = elem;
			return up(k,pos);
			
		}
		
	}
	
	/*
	 *up: Flota un nodo y devuelve la posicion de los nodos
	 */
	
	public Integer[] up(int elem,Integer[] pos) {
		
		int i = elem;
		Node aux;
		int auxpos;
		
		while((i != 1) && array[i/2].getPathCost() > array[i].getPathCost()) {
			
			//intercambio de posiciones en array de posiciones
			auxpos = pos[array[i/2].getKey()];
			pos[array[i/2].getKey()] = pos[array[i].getKey()];
			pos[array[i].getKey()] = auxpos;
			
			//intercambio de posiciones en array de monticulo
			aux = array[i/2];
			array[i/2] = array[i];
			array[i] = aux;
			i = i/2;
		}
		
		pos[array[i].getKey()] = i; 
		
		return pos;
		
	}
	
	/*
	 *elimMin: Elimina el minimo en el monticulo y devuelve el array de posiciones actualizado
	 */
	
	public Integer[] elimMin(Integer[] pos){
		
		if(k == 0) {
			System.out.println("No hay mas elementos :(");
			return null;
		}
		else {
			array[1] = array[k];
			k = k-1;
			return down(1,pos);
		}
		
	}
	
	/*
	 * down: Hunde un nodo en el monticulo y devuelve el array de posiciones actualizado
	 */
	
	public Integer[] down(int elem,Integer[] pos) {
		
		Node aux;
		int j = 1;
		boolean fin = false;
		int m = 0;
		int auxpos;
		
		while((2*j <= k) && !fin) {
			
			if((2*j+1) <= k && (array[2*j + 1].getPathCost() < array[2*j].getPathCost())) {
				m = 2*j +1;
			}else if((2*j+1) >  k || (array[2*j + 1].getPathCost() >= array[2*j].getPathCost())) {
				m = 2*j;
			}
			
			if(array[j].getPathCost() > array[m].getPathCost()) {
				
				//intercambio de posiciones en array de posiciones
				auxpos = pos[array[m].getKey()];
				pos[array[m].getKey()] = pos[array[j].getKey()];
				pos[array[j].getKey()] = auxpos;
				
				//intercambio de posiciones en array de monticulo
				aux = array[j];
				array[j] = array[m];
				array[m] = aux;
				j= m;
				
			}else {
				fin = true;
			}
			
		}
		
		return pos;
		
	}
	
	/*
	 * min: Devuelve el minimo de la estructura
	 */
	
	public Node min() {
		
		if (k == 0) return null;
		return array[1];
	}
	
	/*
	 *decreaseKey: actualiza la clave de un nodo decreciendola, devuelve el array de posiciones actualizado
	 */
	
	public Integer[] decreaseKey(int elem, Double pathCost,Integer[] pos) {
		
		array[elem] = new Node(array[elem].getKey(),pathCost);
		return up(elem,pos);
		
	}


	
}
