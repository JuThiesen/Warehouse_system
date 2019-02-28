import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Algorithms1 {
		
	static int[] Order_List = new int[] {};
	static int[] Orig_Order_List = new int[] {};
	static int[][] PSU_List;
	static String[] Items;
		
	
	/**
	 * Classical Hill Climbing
	 * Start with a random state (PSU)
	 * Checking value of the direct neighbor and replace current state if neighbor improve (see method Single_Hill_Climbing_Step)
	 * Repeat this step until no direct neighbor exist with higher value (difference to First Choice Hill Climbing)
	 * After that comparing current state (PSU) with the order list and delete the items which already found (current state)
	 * Repeat this steps until the order is fulfilled (order list = empty)
	 * @return PSUs which are needed to fulfill the order, identifier of the PSUs and the items which are stored in the PSUs
	 */
	public static void Hill_Climbing () {
		copy_Order_List();													// copy order list, if the user rerun the algorithm the original order list will be used
		do {																// loop to repeat the hill climbing until order list is empty
			int current = Single_Hill_Climbing_Step();						// see method Single_Hill_Climbing_Step
			
			if (Value(current) <= 0) {
				continue;													// if a PSU is chosen with value 0 and the neighbors also have value 0 continue (termination of endless loop)
			}
			
			String[] items = new String[PSU_List[current].length];			// replace the number place holders for the items with the actual name
			for (int i = 0; i < items.length; i++) {
				items[i] = Items[PSU_List[current][i]];
			}
			System.out.println("PSU " + (current + 1) + " " + Arrays.toString(items)); 			// print out the current PSU + identifier and items of the PSU 
			int[] new_List = new_Order_List(current);
			Order_List = new_List; 											// replace current order list through new order list in which already collected items are deleted 
		} while (Order_List.length > 0);											
	}

		
	/**
	 * First choice Hill Climbing
	 * Start with a random state (PSU)
	 * Checking value of the direct neighbor and replace current state if neighbor improve (see method Single_Hill_Climbing_Step)
	 * After that comparing current state (PSU) with the order list and delete the items which already found (current state)
	 * Repeat this steps until the order is fulfilled (order list = empty)
	 * @return PSUs which are needed to fulfill the order, identifier of the PSUs and the items which are stored in the PSUs
	 */
	public static void First_Choice_Hill_Climbing () {
		copy_Order_List();															// copy order list, if the user rerun the algorithm the original order list will be used
		do {																		// loop to repeat the hill climbing until order list is empty
			int randomNum = ThreadLocalRandom.current().nextInt(0, PSU_List.length); // random start 
			int current = randomNum;									

			int neighbor = Next_Improve_Neighbor(current);							// take the first neighbor that improve the current value (difference to classical hill climbing)
			current = neighbor;														// replace current with improving neighbor
				
			
			if (Value(current) <= 0) {												// if a PSU is chosen with value 0 and the neighbors also have value 0 continue (termination of endless loop)
				continue;
			}
			
			String[] items = new String[PSU_List[current].length];					// replace the number place holders for the items with the actual name
			for (int i = 0; i < items.length; i++) {
				items[i] = Items[PSU_List[current][i]];
			}
			System.out.println("PSU " + (current + 1) + " " + Arrays.toString(items));  // print out the current PSU + identifier and items of the PSU 
			int[] new_List = new_Order_List(current);
			Order_List = new_List; 													// replace current order list through new order list in which already collected items are deleted 
		} while (Order_List.length > 0);										
	}
	
	
	/**
	 * Parallel Hill Climbing
	 * @param parallel how often the Hill Climbing should be started parallel (users choice)
	 * For each Hill Climbing selecting random start state(PSUs)
	 * Checking value of the direct neighbor and replace current state if neighbor improve (see method Single_Hill_Climbing_Step)
	 * Repeat this step until no direct neighbor exist with higher value (difference to First Choice Hill Climbing)
	 * Compare the best neighbors of all parallel Hill Climbings and take the one with highest value as current
	 * Comparing current state (PSU) with the order list and delete the items which already found (current state)
	 * Repeat this steps until the order is fulfilled (order list = empty)
	 * @return PSUs which are needed to fulfill the order, identifier of the PSUs and the items which are stored in the PSUs
	 */
	public static void Parallel_Hill_Climbing (int parallel) {
		copy_Order_List();																// copy order list, if the user rerun the algorithm the original order list will be used
		do {																			// loop to repeat the hill climbing until order list is empty
			
			int[] runs = new int[parallel];												// for each parallel hill climbing use Method Single_Hill_Climbing_Step
			for (int i = 0; i < parallel; i++) {
				runs[i] = Single_Hill_Climbing_Step();
			}
			int current = runs[0];											 
			int best_value = Value(current);	
			for (int i = 1; i < runs.length; i++) {										// find best value of parallel runs (minimizing the risk of find only a local maximum and maximizing the chance to find a global maximum)
				if (Value(runs[i]) > best_value) {										// if this best  value is higher than the current value replace current
					current = runs[i];	
					best_value = Value(current);
				}
			}
			
			if (Value(current) <= 0) {													// if a PSU is chosen with value 0 and the neighbors also have value 0 continue (termination of endless loop)
				continue;
			}
			
			String[] items = new String[PSU_List[current].length];						// replace the number place holders for the items with the actual name
			for (int i = 0; i < items.length; i++) {
				items[i] = Items[PSU_List[current][i]];
			}
			System.out.println("PSU " + (current + 1) + " " + Arrays.toString(items)); // print out the current PSU + identifier and items of the PSU 
			int[] new_List = new_Order_List(current);
			Order_List = new_List; 														// replace current order list through new order list in which already collected items are deleted 
		} while (Order_List.length > 0);									
	}
	

	/* Start code of used methods */
	
	/**
	 * Method which contain a single classical Hill Climbing step
	 * The method searched for a direct neighbor (right,left PSU) which will improve the current value and and replace the current state with the neighbor state
	 * @return if exist improving neighbor state else current state 
	 */
	
	public static int Single_Hill_Climbing_Step() {
		int random_start = ThreadLocalRandom.current().nextInt(0, PSU_List.length);// random start
		int current = random_start;											// replace current through the state which is chosen through rnd start
		int current_value = Value(current);
		int neighbor_value = 0;
		do {																// see method Next_Improved_Neighbor
			int neighbor = Next_Improve_Neighbor(current);
			neighbor_value = Value(neighbor);
			
			if (neighbor_value > current_value) {							// if the neighbor have a higher value as the current state replace him
				current = neighbor;
				current_value = neighbor_value;
			}
			
		} while (neighbor_value > current_value);							// terminate when no neighbor has a higher value
		
		return current;														// return the current state (PSU)
	}	
	
			
	/**
	 * evaluate the value of one particular state
	 * @param n the state which should be evaluated
	 * @return value of the state as number
	 */
	
	public static int Value (int n) {
		int value = 0;
		for (int i = 0; i < PSU_List[n].length; i++) {						// comparing the items stored in one PSU with the items of the order list
			for (int j = 0; j < Order_List.length; j++) {
				if (PSU_List[n][i] == Order_List[j]) {
					value ++;												// for each identical items the value counted +1
				}
			}
		}
		return value;
	}
	
		
	/**
	 * evaluate the neighbor with next best value
	 * @return best neighbor
	 */
	
	public static int Next_Improve_Neighbor (int current) {					
		int current_value = Value(current);							
		int left = Math.max(0, current - 1);								// left neighbor or if array ends himself
		int right = Math.min(PSU_List.length - 1, current + 1);				// right neighbor or if array ends himself
		int left_value = Value(left);										
		int right_value = Value(right);										
		
		if (left_value > current_value || right_value > current_value) {	// as long as the value of the left or right neighbor is higher as the current value
			if (left_value > right_value) {									// if the value of the left neighbor is higher return his value
				return left;
			} else {														// else if the value of the right neighbor is higher return his value
				return right;			
			}
		} else {
			return current;													// if no neighbor exist which can improve your value return the current
		}
		
	}
		
	/**
	 * create a new order list based on the current order list
	 * the items which are already collected are deleted from the order list
	 * @param c current best state (PSU)
	 * @return new order list
	 */
	public static int[] new_Order_List (int c) {			
		for (int i = 0; i < Order_List.length; i++) {						// comparing order list with current state (PSU) 
			 for (int j = 0; j < PSU_List[c].length; j++) {
				 if (Order_List[i] == PSU_List[c][j]) {						// if both have the equal item this items will be replaced through zero in the order list
					 Order_List[i] = 0;
					 break;
				 }
			 }
		}
		int count = 0;
		for (int k = 0; k < Order_List.length; k++) {						// counting how much items left in the order list ( items which are not equal to zero)
			if (Order_List[k] != 0) {
				count ++;
			}
		}
		int[] new_Order_List = new int[count];								// creating a new order list of the size of the count
		
		int current_new = 0;
		for (int i = 0; i < Order_List.length; i++) {
			if (Order_List[i] != 0) {										// put each item in the order list which is not zero into the new order list
				new_Order_List[current_new++] = Order_List[i];
				Order_List[i] = 0;
			}
		}
		return new_Order_List;	 
	}
	
		
	
	/**
	 * copying the original (orig) order list and transform her into the "working" order list
	 * this is needed to rerun the order
	 */
	public static void copy_Order_List() {
		Order_List = new int[Orig_Order_List.length];						// creating array with the came length as  the original order list
		for (int i = 0; i < Orig_Order_List.length; i++) {				
			Order_List[i] = Orig_Order_List[i];								// copying each item
		}
	}
}
		
		