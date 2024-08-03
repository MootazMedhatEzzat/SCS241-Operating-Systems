import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager 
{
	String fileName;
	String newFString = null;
	File outFile;
	FileWriter myWriter;
	
	public FileManager(String fileName)
	{
		this.fileName = fileName;
		newFString = fileName + ".txt";
	}
	
	public File createFile()
	{
		outFile = new File(newFString);
				
		try {
			if (outFile.createNewFile())
			{
				System.out.println("File Success");
			}
			else 
			{ 
				System.out.println("File Fail") ;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return outFile;
	}
	
	public FileWriter createFileWriter()
	{
		try {
			myWriter = new FileWriter(outFile);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		return myWriter;
	}
	
	public void closeFile()
	{
		try {
			myWriter.close();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
