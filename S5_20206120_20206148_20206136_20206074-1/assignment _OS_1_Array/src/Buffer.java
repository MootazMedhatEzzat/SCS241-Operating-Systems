import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JLabel;

public class Buffer 
{
	LinkedList<Integer> store = null;
	private semaphore spaces = null;
	private semaphore elements = new semaphore(0);
	private semaphore mutex = new semaphore(1);
	
	public Buffer(LinkedList<Integer> store,int size) 
	{
		this.store = store;
		spaces = new semaphore(size);
	}
	
	private int primeCtr = 0;
	private int ctr = 0;
	
	public void produce(int current, JLabel labelMax, JLabel labelCount)
    {
			spaces.Wait();
			mutex.Wait();
			
			store.add(current);
			primeCtr++;
			
			labelMax.setText( Integer.toString(current) );
			labelCount.setText( Integer.toString(primeCtr) );
			
			mutex.Notify();
	        elements.Notify();
	 }
	
	public void consume(File file, FileWriter fileWriter)
	{
	        int val;
	         
	        elements.Wait();
	        mutex.Wait();
	          
	        val = store.removeFirst();
	         
	        try {
	        	 
	        	 if(ctr < 15)
	        	 {
	        		 fileWriter.write("\"" + val + "\", ");
	        		 fileWriter.flush();
	 				 ctr++;
	        	 }
	        	 else {
	        		 fileWriter.write("\n");
	        		 ctr = 0;
	        	 }
				
	         } catch (IOException e) {
					e.printStackTrace();
	         }
	        mutex.Notify();
	        spaces.Notify();
	}
	
	public boolean isEmpty()
	{
		if (store.isEmpty())
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
}

