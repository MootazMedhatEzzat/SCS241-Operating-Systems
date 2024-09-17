package cpu_schedulers_simulator;

import java.util.LinkedList;

public class SRTF {
	
	private Process[] processesScheduler;
	LinkedList<String> ganttChart = new LinkedList<>();
	private int numOfProcesses;
	private int contextSwitching;
	private int time = 0;
	private int CSTime = 0;
	private int complete = 0;
	private int shortestRemainingTime = Integer.MAX_VALUE;
	private int shortestpProcess = 0;
	private int numOfContextSwitching = 1;
	boolean check = false;

	public SRTF(Process processes[], int numOfProcesses, int contextSwitching) {
		this.processesScheduler = processes;
		this.numOfProcesses = numOfProcesses;
		this.contextSwitching = contextSwitching;
	}

	public Process[] schedulingProcess() {

		// Loop until all processes gets completed
		while (complete != numOfProcesses) {

			findShortestProcess();
			// To store processes execution order
			ganttChart.add(processesScheduler[shortestpProcess].getName());
			time++;
			CSTime++;
			processesScheduler[shortestpProcess].setProgress();       // Update Progress
			processesScheduler[shortestpProcess].setRemainingTime();  // Update Remaining Time 
			
			if (processesScheduler[shortestpProcess].getRemainingTime() == 0) {

				complete++; 
				check = false;
                processesScheduler[shortestpProcess].setCompletionTime(CSTime);
                processesScheduler[shortestpProcess].setCSCompletionTime(CSTime + contextSwitching);
				processesScheduler[shortestpProcess].setWatingTime();
				processesScheduler[shortestpProcess].setTurnaroundTime();
				shortestRemainingTime = Integer.MAX_VALUE;

			}
		}
		//ganttChart.add(processesScheduler[shortestpProcess].getName());
		return processesScheduler;
	}

	// Method to Find process with shortest remaining time among the processes that arrives till the current time
	public void findShortestProcess() {
		int preShortestpProcess = shortestpProcess;
		for (int i = 0; i < numOfProcesses; i++) {
			if ((processesScheduler[i].getArrivalTime() <= time) && (processesScheduler[i].getRemainingTime() < shortestRemainingTime) && processesScheduler[i].getRemainingTime() > 0) {
				shortestRemainingTime = processesScheduler[i].getRemainingTime();
				shortestpProcess = i;
				check = true;
			}
		}
		
		contextSwitching(preShortestpProcess);   // Context Switching latency
		
		if (check == false) {
			time++;
			CSTime++;
			findShortestProcess();
		}
	}
	
	// Simulation of Context Switching latency
	public void contextSwitching(int preShortestpProcess) {
		
		if (shortestpProcess != preShortestpProcess) {
			CSTime += contextSwitching;
			numOfContextSwitching++;
		}
		
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

}
