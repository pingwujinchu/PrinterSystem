package com.xjtu.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Manager implements Runnable{
    public static ExecutorService executor;
    boolean isTermnit = false;
    int index = 1;
    
    static{
    	 executor = Executors.newCachedThreadPool();
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
		if(executor.isTerminated()){
			try {
				Thread.sleep(index*1000);
				if(index<10){
					index++;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	}

}
