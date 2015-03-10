package dynProg;


public class TopDownSolver implements Solver{
        
    private int[][] matrix;
    private int[] solved;
    
    public boolean solve(int[] numbers, int sum){           
        matrix = new int[numbers.length][sum];  

        int totaal=0;
        int teller = 0;
        for(int p=0;p<numbers.length;p++){
            totaal += numbers[p];
        }               
        solved = new int[totaal];
        for(int i = 0; i<numbers.length; i++){                  
            int somB = 0;
            for(int j = 0; j<=i; j++){   
            	
                if(checkSolved(numbers[j])){
                    matrix[i][numbers[j]-1] = numbers[j];
                    solved[teller] = numbers[j];
                    teller++;
                }

                somB += numbers[j];     
                if(checkSolved(somB)){                                  
                    if(somB<=sum){                                  
                        matrix[i][somB - 1] = somB;
                        solved[teller] = somB;
                        teller++;
                    }
                }
                if (j > 0){
                    totaal = somB - numbers[j - 1];
                    if(checkSolved(totaal)){        
                        if(totaal-1<=sum){
                            matrix[i][totaal - 1] = totaal;
                            solved[teller] = totaal;
                            teller++;
                        }
                    }
                }                               
            }                       
        }               
        return solved(sum);
    }
    
    private boolean solved(int sum){                
        if(sum<= matrix[0].length){
            //Kijken of er een antwoord staat in de laatste kolom
            //Elke rij bijlangs gaan en kijken in de laatste kolom of er een antwoord staat
            for(int i = 0; i<matrix.length; i++){
                if(matrix[i][sum-1] != 0){      
                    System.out.println("true");
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean checkSolved(int n){
        if(solved != null){             
            for(int i = 0;i<solved.length;i++){
                if(solved[i]==n){
                    return false;
                }
            }       
        }
        return true;            
    }
}