package Vraag1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * NumberPrinter
 * creates and executes the threads
 * 
 * @author Ferdi
 *
 */
public class NumberPrinter
{	
	/**
	 * main
	 * autoloader
	 * @param args
	 */
	public static void main(String[] args)
	{
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(new PrintNumber(1));
		executor.execute(new PrintNumber(2));
		executor.execute(new PrintNumber(3));
		executor.execute(new PrintNumber(4));
		
		//When done, shut down
		executor.shutdown();
	}
	

	/**
	 * PrintNumber
	 * Internal class that implements Runnable
	 * this is the actual thread that prints the number.
	 * 
	 * @author Ferdi
	 *
	 */
	public static class PrintNumber implements Runnable
	{
		/**
		 * private class variables
		 */
		private int number;
		private static Lock lock = new ReentrantLock();
		private static Condition isReady = lock.newCondition();
		private static Number num = new Number();
		
		/**
		 * constructor
		 * @param i - number
		 */
		PrintNumber(int i)
		{
			number = i;
		}
		
		@Override
	    public void run()
	    {
			/* Print it */ 
		 // print(); 
			
			/* Print it reverse */ 
		 // /*
			try
	        {
		        reversePrint();
	        }
	        catch (InterruptedException e)
	        {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
	        } //*/
		    
	    }
		/**
		 * print number twice
		 */
		private void print()
	    {
			lock.lock();
			try
			{
			    for(int i = 0;i < 2; i++)
			    {
			    	System.out.print(number);
			    }
			    System.out.print(" ");
			}
			finally
			{
				lock.unlock();
			}
		    
	    }
		
		/**
		 * print number twice in reverse order
		 * @throws InterruptedException
		 */
		private void reversePrint() throws InterruptedException
		{
			lock.lock();
			try
			{
				while(number < num.num)
				{
					isReady.await();
				}
				num.subNum();
				for(int i = 0;i < 2; i++)
			    {
			    	System.out.print(number);
			    }
			    System.out.print(" ");
			    isReady.signalAll();
				
			}
			finally
			{
				lock.unlock();
			}
		}
		
		
	}

	/**
	 * Class Number
	 * Internal class to keep track of what number 
	 * is in the thread to determine whether the 
	 * thread needs to wait or be executed
	 * 
	 * @author Ferdi
	 *
	 */
	static class Number
	{
		int num = 4;
		
		/**
		 * empty constructor
		 */
		Number()
		{
			
		}
		
		/**
		 * substract 1 of num
		 */
		void subNum()
		{
			if(num > 0)
			num -=1;
		}
	}
}