package cpu_schedulers_simulator;

import java.util.LinkedList;
import java.util.Queue;

public class RR {
	
	private Process[] processesScheduler;
	Queue<Integer> readyQueue = new LinkedList<>();
	LinkedList<String> ganttChart = new LinkedList<>();
	private int numOfProcesses;
	private int timeQuantum;
	private int contextSwitching;
	private int time = 0;             // holds the current time after each process has been executed
	private int CSTime = 0;
	private int processesExecuted = 0; // holds the number of processes executed so far
	private int runningProcess = 0;
	private int numOfContextSwitching = 0;
	
	public RR(Process processes[], int numOfProcesses, int timeQuantum, int contextSwitching) {
		this.processesScheduler = processes;
		this.numOfProcesses = numOfProcesses;
		this.timeQuantum = timeQuantum;
		this.contextSwitching = contextSwitching;
		sort(processesScheduler);
	}
	

	public Process[] schedulingProcess() {
		
		// Initially, pushing the first process which arrived first
		readyQueue.add(0); 
		processesScheduler[0].setInQueue(true);
		// Loop until the readyQueue gets empty
	    while (readyQueue.isEmpty() == false)
	    {
	        updateQueue();
	    }
		
		return processesScheduler;
	}
	
	// At every time quantum or when a process has been executed before the time quantum,
	// check for any new arrivals and push them into the queue
	void checkForNewArrivals()
	{
	    for (int i = 0; i < numOfProcesses; i++)
	    {
	        // checking if any processes has arrived if so, push them in the ready Queue.
	        if ((processesScheduler[i].getArrivalTime() <= time) && (processesScheduler[i].isInQueue() == false) && (processesScheduler[i].isComplete() == false))
	        {
	        	processesScheduler[i].setInQueue(true);
	            readyQueue.add(i);
	        }
	    }
	}
	
	
	// Context switching takes place at every time quantum
	void updateQueue()
	{
		runningProcess = readyQueue.remove();
	    // if the the remaining time for this process is less than or equal quantum,
		// this mean that this is the last cycle for this process
	    if (processesScheduler[runningProcess].getRemainingTime() <= timeQuantum)
	    {
	    	processesScheduler[runningProcess].setComplete(true);
	        time += processesScheduler[runningProcess].getRemainingTime();
	        // To store processes execution order
	        for(int i = 0; i < processesScheduler[runningProcess].getRemainingTime(); i++) {
	    		ganttChart.add(processesScheduler[runningProcess].getName());
	        }
	        CSTime += processesScheduler[runningProcess].getRemainingTime();
	        processesScheduler[runningProcess].setCompletionTime(CSTime);
	        processesScheduler[runningProcess].setCSCompletionTime(CSTime + contextSwitching);
	        processesScheduler[runningProcess].setWatingTime();
	        processesScheduler[runningProcess].setTurnaroundTime();
	 
	        if (processesScheduler[runningProcess].getWatingTime() < 0) {
	        	
	        	processesScheduler[runningProcess].setWatingTime(0);
	        	
	        }
	 
	        processesScheduler[runningProcess].setRemainingTime(0);
	 
	        // if all the processes are not yet inserted in the queue,
	        // then check for new arrivals
	        if (processesExecuted != numOfProcesses)
	        {
	            checkForNewArrivals();
	        }
	    }
	    else
	    {
	        // the process is not done yet. But it's going to be pre-empted
	        // since one quantum is used
	    	processesScheduler[runningProcess].setRemainingTime(processesScheduler[runningProcess].getRemainingTime() - timeQuantum);
	        time += timeQuantum;
	        // To store processes execution order
	        for(int i = 0; i < timeQuantum; i++) {
	    		ganttChart.add(processesScheduler[runningProcess].getName());
	        }
	        CSTime += timeQuantum;
	        // if all the processes are not yet inserted in the queue,
	        // then check for new arrivals
	        if (processesExecuted != numOfProcesses)
	        {
	            checkForNewArrivals();
	        }
	        // insert the incomplete process back into the queue
	        readyQueue.add(runningProcess);
	    }
	    CSTime += contextSwitching;
	    numOfContextSwitching++;
	}
	
	
	public void printNumOfContextSwitching() {
		
		System.out.println("Number of Context Switching = " + numOfContextSwitching);
	}	
	
	public void printGanttChart() {
		System.out.print("Processes execution order: ");
		for (int i = 0; i < ganttChart.size(); i++)
	    {
			System.out.print(ganttChart.get(i));
			if ((i < (ganttChart.size() - 1)) && (ganttChart.get(i) != ganttChart.get(i+1))) {
				System.out.print(" -> ");
			}
			else {
				System.out.print(" ");
			}
	    }
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------");
	}
		
	public void sort(Process[] processes) {
		
		Process temp;
		
		for(int i = 0; i < processes.length; i++) {
			for(int j = i + 1; j < processes.length; j++) {
				if(processes[i].getArrivalTime() > processes[j].getArrivalTime()) {
					temp  = processes[i];
					processes[i] = processes[j];
					processes[j] = temp;
				}
			}
		}
	}

}



