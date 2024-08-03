import javax.swing.JLabel;

public class Stopwatch implements Runnable
{
	private int counter;
	private JLabel labelClock;
	
	public Stopwatch(JLabel label)
	{
		this.labelClock = label;
	}
	
	@Override
	public void run()
	{
		while (GlobalFlag.flag)
		{
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			labelClock.setText(counter + " ms");
			counter++;
		}
	}
}
