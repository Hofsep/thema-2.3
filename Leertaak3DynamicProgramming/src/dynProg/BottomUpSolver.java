package dynProg;


public class BottomUpSolver implements Solver{
        
    private int[][] matrix;
  
    public boolean solve(int[] numbers, int sum){           
	    matrix = new int[numbers.length][sum];   
	
	    int totaal=0;
	    for(int p=0;p<numbers.length;p++){
	            totaal += numbers[p];
	    }
	    
	    for(int i = 0; i<numbers.length; i++){                  
            int somB = 0;
            for(int j = 0; j<=i; j++){      

                matrix[i][numbers[j]-1] = numbers[j];

                somB += numbers[j];     
                if(somB<=sum){                                  
                    matrix[i][somB - 1] = somB;
                }

                if (j > 0)
                {
                    totaal = somB - numbers[j - 1];
                    if(totaal-1<=sum){
                            matrix[i][totaal - 1] = totaal;
                    }                                       
                }                                                                       
            }                       
	    }               
	    return solved(sum);
    }
    
    private boolean solved(int sum){
        if(sum<= matrix[0].length){
            for(int i = 0; i<matrix.length; i++){
                if(matrix[i][sum-1] != 0){      
                        System.out.println("true");
                        return true;
                }
            }
        }
        return false;
    }
}