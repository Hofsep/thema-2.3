import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class GetalRij {
	private int[] getallen;
	
	public GetalRij( int aantal, int max ){
		// Belangrijke aanname: aantal < max, anders kunnen de getallen niet uniek zijn.
		getallen = new int[aantal];
		vulArrayMetUniekeWaarden( aantal, max );
	}

	private void vulArrayMetUniekeWaarden(int aantal, int max) {
		// Vul een hulplijst met getallen 0, ..., max
		ArrayList<Integer> hulpLijst = new ArrayList<Integer>( max );
		for ( int i=0; i<max; i++){
			hulpLijst.add( i );
		}
		
		// Stop 'aantal' random waarden in getallen
		Random r = new Random();
		for ( int i=0; i<aantal; i++){
			// Het omzetten van Integer naar int gaat sinds Java 1.5 automatisch (unboxing).
			int getal = (Integer) (hulpLijst.remove( r.nextInt( hulpLijst.size())));
			getallen[i] = getal;
		}
	}
	
	public boolean zitErinA( int zoekWaarde ){
		
		boolean found = false;
		int index = 0;
		
		while( index < getallen.length ){
			if( getallen[index] == zoekWaarde ){
				found = true;
				System.out.println("FOUND");
			}
			index++;
		}
		
		return found;
	}

	public boolean zitErinB( int zoekWaarde ){
		
		boolean found = false;
		int index = 0;
		
		while(index < getallen.length ){
			if( getallen[index] == zoekWaarde ){
				found = true;
				System.out.println("FOUND");
				break;
			}
			index++;
		}
		
		return found;
	}

	public boolean zitErinC( int zoekWaarde ){
			
		boolean found = false;
		int index;
		
		if(zoekWaarde >= getallen.length){
			index = getallen.length-1;
		} else {
			index = zoekWaarde;
		}
		
		while(!found && index >= 0){
			if( getallen[index] == zoekWaarde ){
				found = true;
				System.out.println("FOUND");
			}
			index--;
		}
		
		return found;
	}

	public boolean zitErinD( int zoekWaarde ){
		
		boolean found = false;
		int min =0;
		int max =getallen.length-1;
		
		while(max>=min){
			
			int mid = (min+max)/2;
			if(getallen[mid] == zoekWaarde){
				found = true;
				System.out.println("FOUND");
				break;
			}
			else if (zoekWaarde<getallen[mid]) { 
				max = mid-1;
			}
			else {
				min = mid+1;
			}
			
		}
		return found;
	}
	
	public void sorteer(){
		Arrays.sort( getallen);
	}
	
	public void print(){
		for( int i=0; i<getallen.length; i++)
			System.out.println(getallen[i]);
	}

}
