package Vraag1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SumThreads {
	public static Number syncNum = new Number();
	public static Number asyncNum = new Number();
	
	public static void main(String[] args)
	{
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 1000; i++)
		{
			executor.execute(new AddNumber());
		}
		
		executor.shutdown();
		
		while(!executor.isTerminated())
		{
			
		}
		System.out.println("Synced " +syncNum.getNumber());
		System.out.println("Unsynced " +asyncNum.getNumber());
	}
	
	public static class AddNumber implements Runnable
	{

		@Override
		public void run() {
			syncNum.add(1);
			asyncNum.aAdd(1);
		}
		
	}
	
	private static class Number
	{
		private int number = 0;
		private static Lock lock = new ReentrantLock();
		
		public void add(int a)
		{
			lock.lock();
			try{
				number += a;
			}
			finally{
				lock.unlock();
			}
			
		}
		
		public void aAdd(int a)
		{
			number += a;
		}
		
		public int getNumber()
		{
			return number;
		}
	}
}
