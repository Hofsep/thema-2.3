package Vraag1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NumberPrinter {
	
	public static void main(String[] args)
	{
		ExecutorService executor = Executors.newFixedThreadPool(4);
		executor.execute(new PrintNumber(1));
		executor.execute(new PrintNumber(2));
		executor.execute(new PrintNumber(3));
		executor.execute(new PrintNumber(4));
		
		while(!executor.isTerminated())
		{

		}
		executor.shutdown();

	}
	
	public static class PrintNumber implements Runnable
	{
		private int number;
		private static Num num = new Num();
		
		public PrintNumber(int i)
		{
			number = i;
			System.out.println(number);
		}

		@Override
		public void run() 
		{
			try {
	            while (true) {
	                putMessage();
	            }
	        } catch (InterruptedException e) {
	        }
		}
		
		private synchronized void putMessage() throws InterruptedException {
			try
			{
				if (num.getNumber() != number) {
		            wait();
		        }
				num.substract();
				for(int i = 0; i < 2; i++)
				{
					System.out.print(print());
				}
				System.out.print(" ");
				System.out.print(num.getNumber()+" ");
				notify();
			}
			finally
			{
				notify();
			}
		}

		private int print()
		{
				return number;
		}
	}
	
	public static class Num
	{
		private int numb = 4;
		
		public Num()
		{
			
		}
		
		public void setNumber(int i)
		{
			numb = i;
		}
		
		public int getNumber()
		{
			return numb;
		}
		
		public void substract()
		{
			numb -= 1;
		}
		
	}
	
}
