import java.util.concurrent.ArrayBlockingQueue;
import javax.swing.JLabel;

public class Producer implements Runnable 
{
	private boolean prime(int Num)
	{
		if (Num <= 1)
            return false;
 
        // Check if number is 2
        else if (Num == 2)
            return true;
 
        // Check if n is a multiple of 2
        else if (Num % 2 == 0)
            return false;
 
        // If not, then just check the odds
        for (int i = 3; i <= Math.sqrt(Num); i += 2) {
            if (Num % i == 0)
                return false;
        }
        return true;
	}
	
	private int N;
	private String maxString = "Max Num",CtrString="counter";
	ArrayBlockingQueue<String> queue = null;
	private JLabel labelMax, labelCount;
	Buffer buffer = null;
	
	public Producer (Buffer buffer, int N,JLabel label1,JLabel label2)
	{
		this.buffer = buffer;
		this.N = N;
		this.labelMax = label1;
		this.labelCount = label2;
	}
	
	private int numberProducer = 0;

	@Override
	// code of thread 1 (producer)
	public void run() 
	{
		while (GlobalFlag.flag)
		{
			if (numberProducer < N)
			{
				numberProducer++;
				
				if (prime(numberProducer))
				{
					buffer.produce(numberProducer,labelMax,labelCount);
				}
			}
			
			if(numberProducer == N)
			{
				GlobalFlag.flag = false;
			}
		}
	}

	public String getMaxString() {
		return maxString;
	}

	public void setMaxString(String maxString) {
		this.maxString = maxString;
	}

	public String getCtrString() {
		return CtrString;
	}

	public void setCtrString(String ctrString) {
		CtrString = ctrString;
	}
}
