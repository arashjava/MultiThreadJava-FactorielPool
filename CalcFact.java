package App;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class CalcFact implements Runnable{
	
	private int value;
	private int number;
	
	public CalcFact(int value, int number) {
		this.value =value;
		this.number= number;
	}

	public static void main(String[] args) throws InterruptedException {
		defineProcessors(8);
	}
	
	public static void defineProcessors(int numProcessors) throws InterruptedException {
		ExecutorService executor= Executors.newFixedThreadPool(numProcessors);
		
		Random random= new Random();
		int[] number= new int[20];
		
		try {
			for (int i = 0; i < 20; i++) {
				number[i]= random.nextInt(random.nextInt(1000));
				executor.submit(new CalcFact(number[i], i));
			}
		} finally {
			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.DAYS);
		}
	}

	
	@Override
	public void run() {
		long start = System.nanoTime();    
		BigInteger result= BigInteger.valueOf(1);
		for (int i = 1; i < value+1 ; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		long elapsedTime = System.nanoTime() - start;

		System.out.println("Time spent =" + elapsedTime/1000 + "(micro sec) Task number " + number + ": Factoriel of "+ value +  " is: "+ result);
		
	}

}

