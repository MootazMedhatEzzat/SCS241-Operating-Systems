package cpu_schedulers_simulator;

import java.util.LinkedList;

public class PreemptivePriority {
	
	private Process[] processesScheduler;
	LinkedList<String> ganttChart = new LinkedList<>();
	private int numOfProcesses;
	private int contextSwitching;
	private int time = 0;
	private int CSTime = 0;
	private int complete = 0;
	private int highestPriority = Integer.MAX_VALUE;
	private int highestPriorityProcess = 0;
	private int numOfContextSwitching = 1;
	boolean check = false;

	public PreemptivePriority(Process processes[], int numOfProcesses, int contextSwitching) {
		this.processesScheduler = processes;
		this.numOfProcesses = numOfProcesses;
		this.contextSwitching = contextSwitching;
	}

	public Process[] schedulingProcess() {

		// Loop until all processes gets completed
		while (complete != numOfProcesses) {

			findHighestPriorityProcess();
			// To store processes execution order
			ganttChart.add(processesScheduler[highestPriorityProcess].getName());
			time++;
			CSTime++;
			processesScheduler[highestPriorityProcess].setProgress();       // Update Progress
			processesScheduler[highestPriorityProcess].setRemainingTime();  // Update Remaining Time 
			
			if (processesScheduler[highestPriorityProcess].getRemainingTime() == 0) {

				complete++; 
				check = false;
                processesScheduler[highestPriorityProcess].setCompletionTime(CSTime);
                processesScheduler[highestPriorityProcess].setCSCompletionTime(CSTime + contextSwitching);
				processesScheduler[highestPriorityProcess].setWatingTime();
				processesScheduler[highestPriorityProcess].setTurnaroundTime();
				highestPriority = Integer.MAX_VALUE;

			}
		}
		return processesScheduler;
	}

	// Method to Find process with shortest remaining time among the processes that arrives till the current time
	public void findHighestPriorityProcess() {
		int preHighestPriorityProcess = highestPriorityProcess;
		
		// the solving of starvation problem
		for (int i = 0; i < numOfProcesses; i++) {
			if ((processesScheduler[i].getRemainingTime() > 0) && (((processesScheduler[i].getArrivalTime() - time) % 15) == 0) && (processesScheduler[i].getPriority() > 0)) {
				processesScheduler[i].updatePriority();
			}
		}
		
		for (int i = 0; i < numOfProcesses; i++) {
			if ((processesScheduler[i].getArrivalTime() <= time) && (processesScheduler[i].getPriority() < highestPriority) && processesScheduler[i].getRemainingTime() > 0) {
				highestPriority = processesScheduler[i].getPriority();
				highestPriorityProcess = i;
				check = true;
			}
		}
		
		contextSwitching(preHighestPriorityProcess);   // Context Switching latency
		
		if (check == false) {
			time++;
			CSTime++;
			findHighestPriorityProcess();
		}
		
	}
	
	// Simulation of Context Switching latency
	public void contextSwitching(int preHighestPriorityProcess) {
		
		if (highestPriorityProcess != preHighestPriorityProcess) {
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
