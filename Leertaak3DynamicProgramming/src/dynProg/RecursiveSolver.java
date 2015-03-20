package dynProg;

public class RecursiveSolver implements Solver {

	boolean check = false;
	
	public static void main(String[] args){
		RecursiveSolver rs = new RecursiveSolver();
		if(rs.solve( new int[]{3,5,7,9,11}, 17 ) ){
			System.out.println("Succesfully finished!");
		} else {
			System.out.println("Not Possible..");
		}
	}
	
	@Override
	public boolean solve(int[] numbers, int sum) {

		// get the sum of everything in array
		int arraySum = 0;
		for (int i = 0; i < numbers.length; i++) {
			arraySum += numbers[i];
		}
		// if the sum calculated in array equals to sum, return true
		if (arraySum == sum) {
			check = true;
		} else {
			// remove a value and make a copy array without that value
			for (int i = 0; i < numbers.length; i++) {
				int[] tempArray = new int[numbers.length - 1];
				for (int j = 0; j < numbers.length - 1; j++) {
					if (numbers[i] != numbers[j]) {
						tempArray[j] = numbers[j];
					}
				}
				// try recursion with the array copy and sum
				solve(tempArray, sum);
			}
		}
		return check;
	}
}