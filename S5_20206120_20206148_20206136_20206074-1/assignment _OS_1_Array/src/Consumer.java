import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Consumer implements Runnable
{
	String outputFile;
	Buffer buffer = null;
	File myFile;
	FileWriter myWriter;
	
	public Consumer (Buffer buffer, String outputFile, File file, FileWriter fileWriter)
	{
		this.buffer = buffer;
		this.outputFile = outputFile;
		this.myFile = file;
		this.myWriter = fileWriter;
	}

	@Override
	public void run() 
	{
		while(GlobalFlag.flag || !(buffer.isEmpty())) 
		{
				
			buffer.consume(myFile, myWriter);
		}
		
		try {
			myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
