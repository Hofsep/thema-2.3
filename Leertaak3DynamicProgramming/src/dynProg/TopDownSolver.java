package dynProg;

public class TopDownSolver implements Solver {

	// matrix is being saved and values are being cached
	static boolean[][] matrix;

	public static void main(String[] args) {
		TopDownSolver tds = new TopDownSolver();
		if (tds.solve(new int[]{3,5,7,9,11}, 17 )) {
			System.out.println("Succesfully finished");
		} else {
			System.out.println("Not possible...");
		}
		System.out.println();
		tds.print(matrix);

	}

	@Override
	public boolean solve(int[] numbers, int sum) {

		// get the sum of everything in array
		// (just for deciding the amount of columns in matrix)
		int arraySum = 0;
		for (int i = 0; i < numbers.length; i++) {
			arraySum += numbers[i];
		}

		// get the matrix and start doing this shjitt
		matrix = new boolean[numbers.length][arraySum];
		for (int row = 0; row < numbers.length; row++) {
			// start a base value for current row and add numbers[i] to it
			int value = 0;
			for (int i = 0; i <= row; i++) {

				// set the base values as true in the row currently working
				matrix[row][numbers[i] - 1] = true;

				// add values from numbers[] and save them as well
				// NOT the base, but the added values of base
				value += numbers[i];
				matrix[row][value - 1] = true;

				// not all combinations are included
				// subtract from the sum and include the rest
				for (int c = 1; c <= i; c++) {
					int subtractFromValue = value - numbers[c - 1];
					matrix[row][subtractFromValue - 1] = true;
				}
			}
		}
		return isSolved(sum);
	}

	/*
	 * isSolved goes through only the row sum-1 getting true back if there is a
	 * true present
	 */
	private boolean isSolved(int sum) {
		if (sum <= matrix[0].length) {
			for (int i=0; i<matrix.length; i++) {
				if (matrix[i][sum - 1])
					return true;
			}
		}
		return false;
	}

	// print out the matrix to see the output
	public void print(boolean[][] m) {

		String firstString = "  |";
		String matrix = "";
		for (int i = 0; i < m.length; i++) {
			matrix += (i + 1) + " |";
			for (int j = 0; j < m[i].length; j++) {
				if (i == 0 && j < 9)
					firstString += (j + 1) + "  |";
				else if (i == 0 && j >= 9)
					firstString += (j + 1) + " |";
				if (m[i][j])
					matrix += "T  |";
				else if (!(m[i][j]))
					matrix += "   |";
			}
			matrix += "\n";
		}
		System.out.println(firstString);
		System.out.println(matrix);
		System.out.println();
	}
}