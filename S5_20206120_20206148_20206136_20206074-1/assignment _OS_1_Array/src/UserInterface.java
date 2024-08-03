import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class UserInterface implements ActionListener
{
	public JPanel creatorJPanel(JPanel panel, String string, JTextField textField)
	{
		
		textField.setPreferredSize(new Dimension(280, 30));
		JLabel label = new JLabel(string);
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setSize(20,20);
		panel.setLayout(new FlowLayout(FlowLayout.LEADING,3,3));
		panel.add(textField, BorderLayout.CENTER);
		panel.add(label);
		return panel;
	};
	
	public JPanel creatorJPanel2(JPanel panel, String string)
	{
		JLabel label = new JLabel(string);
		label.setPreferredSize(new Dimension(280, 40));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setSize(20,20);
		panel.setLayout(new FlowLayout(FlowLayout.LEADING,3,3));
		panel.add(label);
		return panel;
	};
	
	private JFrame frame;
	private JButton button;
	private JPanel panel1,panel2;
	private JPanel subpanel1,subpanel2,subpanel3;
	private JPanel subpanelA,subpanelB,subpanelC;
	private LineBorder border;
	private JTextField textField1,textField2,textField3;
	private JLabel label1,label2,label3;
	
	private int N;
	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public void setLabel2(JLabel label2) {
		this.label2 = label2;
	}

	public JLabel getLabel3() {
		return label3;
	}

	public void setLabel3(JLabel label3) {
		this.label3 = label3;
	}

	private int bufferSize = 5;
	private String outputfile;
	private String MaxPrime = "MAX_PRIME", PrimeCtr = "PrimeCTR";
	ArrayBlockingQueue<String> queue = null;
	Buffer buffer = null;
	
	public UserInterface() 
	{
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(400, 350));
		frame.setBackground(Color.BLACK);
		
		button = new JButton("Start Producer");
		button.addActionListener(this);
		
		border = new LineBorder(Color.BLACK, 10);
		
		textField1 = new JTextField();
		textField2 = new JTextField();
		textField3 = new JTextField();
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		panel1.setSize(20,20);
		panel1.setBorder(border);
		panel1.setLayout(new GridLayout(0,1));
		panel1.add(creatorJPanel(subpanel1,"N",textField1));
		panel1.add(creatorJPanel(subpanel2,"Buffer Size",textField2));
		panel1.add(creatorJPanel(subpanel3, "Output File",textField3));
		panel1.add(button, BorderLayout.CENTER);
		
		subpanelA = new JPanel();
		subpanelB = new JPanel();
		subpanelC = new JPanel();
		
		label1 = new JLabel(MaxPrime);
		label2 = new JLabel(PrimeCtr);
		label3 = new JLabel("Timer-0");
		
		label1.setPreferredSize(new Dimension(80, 30));
		label2.setPreferredSize(new Dimension(80, 30));
		label3.setPreferredSize(new Dimension(80, 30));
		
		panel2.setSize(20,20);
		panel2.setBorder(border);
		panel2.setLayout(new GridLayout(0,1));
		panel2.add(creatorJPanel2(subpanelA,"the largest prime number"+"\t"));
		subpanelA.add(label1);
		panel2.add(creatorJPanel2(subpanelB,"# of elements (prime number) generated"+"\t"));
		subpanelB.add(label2);
		panel2.add(creatorJPanel2(subpanelC, "time elapsed since the start of processing"+"\t"));
		subpanelC.add(label3);
		
		panel1.setBackground(Color.LIGHT_GRAY);
        panel2.setBackground(Color.LIGHT_GRAY);
        
		frame.add(panel1, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Prime Numbers Generator");
		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		GlobalFlag.flag = true;
		
		N = Integer.parseInt(textField1.getText());
		bufferSize = Integer.parseInt(textField2.getText());
		outputfile = textField3.getText();
		
		FileManager fileManager = new FileManager(outputfile);
		File myFile = fileManager.createFile();
		FileWriter myFileWriter = fileManager.createFileWriter();
		
		LinkedList<Integer> bufferArray = new LinkedList<>();
		
		buffer = new Buffer(bufferArray, bufferSize);
		
		Producer producer = new Producer(buffer, N,label1,label2);
		Consumer consumer = new Consumer(buffer, outputfile, myFile, myFileWriter);
		Stopwatch stopwatch = new Stopwatch(label3);
		
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		Thread stopwatchThread = new Thread(stopwatch);
		
		producerThread.start();
		consumerThread.start();
		stopwatchThread.start();
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public String getMaxPrime() {
		return MaxPrime;
	}

	public void setMaxPrime(String maxPrime) {
		MaxPrime = maxPrime;
	}

	public String getPrimeCtr() {
		return PrimeCtr;
	}

	public void setPrimeCtr(String primeCtr) {
		PrimeCtr = primeCtr;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public String getOutputfile() {
		return outputfile;
	}

	public void setOutputfile(String outputfile) {
		this.outputfile = outputfile;
	}
}
