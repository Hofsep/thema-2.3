package dynProg;


public class RecursiveSolver implements Solver {
	
	private boolean check = false;
	
	public static void main(String[] args){
		
		RecursiveSolver rs = new RecursiveSolver();
		rs.solve(new int[]{3,5,7,9,11}, 17);

	}

	@Override
	public boolean solve(int[] numbers, int sum) {

		int som = 0;
		for(int i=0; i<numbers.length; i++){
			som+= numbers[i];
		}
		
		if(som == sum){
	//		System.out.println("true");
			check = true;
		} else {
			
				for(int i=0; i<numbers.length; i++){
						int[] tempArray = new int[numbers.length-1];
						for(int j=0; j<numbers.length-1; j++){
							if(numbers[i]!=numbers[j]){
								tempArray[j] = numbers[j];
							}
						}
						
						solve(tempArray, sum);
				}
				
		}
		return check;
	}
	
}