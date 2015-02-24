
public class SnelheidOefening {

	/**
	 * @param args
	 */
	public static void main( String[] args){
		
		System.out.println("START");
		long making =tijd();
		
		GetalRij gr = new GetalRij( 300000, 400000);
		System.out.println("Making the array took " + ((tijd() - making)/1000) + " seconds");
		
		long a = tijd();
		gr.zitErinA(86425);
		System.out.print("Method A searching time = " + (tijd()-a) + " milliseconds");
		System.out.println();

		long b = tijd();
		gr.zitErinB(86425);
		System.out.print("Method B searching time = " + (tijd()-b) + " milliseconds");
		System.out.println();
		
		gr.sorteer();
		long c = tijd();
		gr.zitErinC(86425);
		System.out.print("Method C searching time = " + (tijd()-c) + " milliseconds");
		System.out.println();
		
		long d = tijd();
		gr.zitErinD(86425);
		System.out.print("Method D searching time = " + (tijd()-d) + " milliseconds");
		System.out.println();
		
		System.out.println("END OF PROGRAM");
	}

	// Hulpmethode voor tijdsbepaling
	private static long tijd(){
		return System.currentTimeMillis();
	}

}
