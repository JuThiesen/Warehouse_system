import java.io.*;
import java.util.*;
import java.util.LinkedList;

public class Algorithms2 {
	
	static int[] Order_List = new int[] {};
	static int[] Orig_Order_List = new int[] {};
	static int[][] PSU_List;
	static String[] Items;
		

	//as long as our order is not fulfilled, order = false
	boolean order = false;	
			
	public static void local_beam_search(int parallel) {
		
		//k states = inserted by user
		int k = parallel; 			// currently not handed over 
		
		//select k random initial states
		int k_current[][] = new int[][] {};
		for (int i = 0; i == k; i++) {
			for (int j = 0; j == k; j++) {
				k_current[i][j] = PSU_List[(int)(Math.random() * k)][(int)(Math.random() * k)];
			}
		}
		
		//as long as order is not fulfilled, do
		while (order = false) {
		
			//put all neighbors of the k states in one neighbor array
			int[][] all_neighbors = new int[PSU_List.length][];
			for (int i = 0; i < PSU_List.length; i++) {
				for (int j = 0; j < PSU_List.length; j++) {
					int[] index = PSU_List[i];
					all_neighbors[i] = index;
				}
			}
			
			//compute the values of the k states respectively
			int[] current_value = new int[] {};
			for(int i = 0; i == k; i++) {
				current_value[i] = Value(k_current[i]);	
			}
			
			//look for the k neighbors, that improve the objective function the most (that have the highest value)
			for(int i = 0; i == current_value.length; i++) {
					all_neighbors = Best_Neighbor();
					if (Value(all_neighbors) <= current_value[i]) {
						return current_value[i];
					}
					else{
					current_value = Value(all_neighbors);
					}
			}
			
			//if the evaluation function can't be improved further, stop and return current states
			if (value = Order_List.length){
				//as soon as all items of an order collected (objective function can't be improved anymore), order = true
				order = true;
				return k_current;
			}
			
			//else do another iteration with new neighborhood
			else{
				k_current = all_neighbors;
			}
		}
	}
	
	/**
	 * evaluate the value of one particular state
	 * @param n the state which should be evaluated
	 * @return value of the state as number
	 */
	public static int Value (int[] n) {
		int value = 0;
		for (int i = 0; i < n.length; i++) {								// comparing the items stored in one PSU with the items of the order list
			for (int j = 0; j < Order_List.length; j++) {
				if (n[i] == Order_List[j]) {
					value ++;												// for each identical items the value counted +1
				}
			}
		}
		return value;
	}
	
	/**
	 * evaluate the neighbor with the highest value
	 * @return best neighbor
	 */
	
	public static int[] Best_Neighbor () {
		int[] best = PSU_List[0];
		int value_best = Value(best);
		int[] next_state;
		for (int i = 1; i < PSU_List.length; i++) {							// comparing each PSU with the next PSU in the row if the value is higher
			next_state = PSU_List[i];
			if (Value(next_state) > value_best) {
				value_best = Value(next_state);								// if the value of the neighbor is higher than the current the current state will replaced through the neighbor
				best = next_state;						
			}
			
		}
		return best;
	}


}