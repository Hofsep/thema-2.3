package dynProg;

public class BottomUpSolver implements Solver {
	
	public static void main(String[] args){
		BottomUpSolver bsm = new BottomUpSolver();
		if(bsm.solve( new int[]{3,5,7,9,11}, 17 )){
			System.out.println("Succesfully Finished!");
		} else {
			System.out.println("Not Possible...");
		}
	}
	
	@Override
	public boolean solve(int[] numbers, int sum) {

		// return true if the array is empty and the sum is empty
		if (numbers.length == 0 && sum == 0) {
			return true;
		}
		// make a matrix of booleans to indicate if the sum of values could be
		// made into the column
		boolean check = false;
		boolean[][] matrix = new boolean[numbers.length][sum];
		for (int row = 0; row < matrix.length; row++) {
			// set the base values as true in the matrix
			matrix[row][numbers[row] - 1] = true;

			for (int col = 0; col < matrix[row].length && row > 0; col++) {
				// set matrix coordinate to true if coordinate above is set as true
				matrix[row][col] = matrix[row][col] || matrix[row - 1][col];

				// M(i-1,j) + C --> M(i,j+c) == true
				// as defined at question 11
				if (matrix[row - 1][col]) {
					if (numbers[row] + col < sum) {
						matrix[row][col + numbers[row]] = true;
					}
				}
				// the check is solved if the last column is set to true
				if (matrix[row][sum - 1]) {
					check = true;
				}

			}
		}
		print(matrix, sum, numbers);
		return check;
	}

	// print out the matrix to see the output
	public void print(boolean[][] m, int sum, int[] numbers) {

		String firstString = "  |";
		String matrix = "";
		for (int i = 0; i < m.length; i++) {
			matrix += (i + 1) + " |";
			for (int j = 0; j < sum; j++) {
				if (i == 0 && j < 9) firstString += (j + 1) + "  |"; else if (i == 0 && j >= 9) firstString += (j + 1) + " |";
				if (m[i][j]) matrix += "T  |"; else if (!(m[i][j])) matrix += "   |";
			}
			matrix += numbers[i] + " added";
			matrix += "\n";
		}
		System.out.println(firstString);
		System.out.println(matrix);
		System.out.println();
	}
}