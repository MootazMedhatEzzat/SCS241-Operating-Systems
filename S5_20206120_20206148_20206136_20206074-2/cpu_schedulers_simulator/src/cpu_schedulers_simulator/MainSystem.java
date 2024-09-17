package cpu_schedulers_simulator;
import java.util.Scanner;

public class MainSystem {
	
	public static void main(String[] args) {
	
		Scanner input = new Scanner(System.in);
		Process[] proc;
		int numberOfProcesses;
		int roundRobinTimeQuantum;
		int contextSwitching;
		////////////////////////////////////////
		String processName;
		int processArrivalTime;
		int ProcessBurstTime;
		int ProcessPriority ;
		
		System.out.print("Enter Number Of Processes: ");
		numberOfProcesses = input.nextInt();
		System.out.print("Enter Round robin Time Quantum: ");
		roundRobinTimeQuantum = input.nextInt();
		System.out.print("Enter Context Switching: ");
		contextSwitching = input.nextInt();
		
		proc = new Process[numberOfProcesses];
		
		for (int i = 0; i < numberOfProcesses; i++) {
			System.out.print("Enter Process Name: ");
			processName = input.next();
			System.out.print("Enter Process Arrival Time For " + processName + ": ");
			processArrivalTime = input.nextInt();
			System.out.print("Enter Process Burst Time For   " + processName + ": ");
			ProcessBurstTime = input.nextInt();
			System.out.print("Enter Process Priority For     " + processName + ": ");
			ProcessPriority = input.nextInt();
			proc[i] = new Process(processName, processArrivalTime, ProcessBurstTime, ProcessPriority);
		}	
		
		// Test Case For Shortest-Remaining-Time-First
		Process proc1[] = { new Process("P1", 0, 7, 0), 
                            new Process("P2", 2, 4, 0),
                            new Process("P3", 4, 1, 0), 
                            new Process("P4", 5, 4, 0)};
		// Test Case For RoundRobin with time quantum = 3
		Process proc2[] = { new Process("P1", 0, 8, 0), 
	                        new Process("P2", 5, 2, 0),
	                        new Process("P3", 1, 7, 0), 
	                        new Process("P4", 6, 3, 0),
	                        new Process("P5", 8, 5, 0)};
		// Test Case For RoundRobin with time quantum = 6
		Process proc3[] = { new Process("P1", 0, 8, 0), 
	                        new Process("P2", 1, 5, 0),
	                        new Process("P3", 2, 10, 0), 
	                        new Process("P4", 3, 11, 0)};
		// Test Case For  Preemptive Priority
		Process proc4[] = { new Process("P1", 0, 10, 3), 
                            new Process("P2", 1, 1, 1),
                            new Process("P3", 2, 2, 4), 
                            new Process("P4", 3, 1, 5),
                            new Process("P5", 4, 5, 2)};
		
		
		
		
		SRTF srtf = new SRTF(proc1, 4, 1);
		print(srtf.schedulingProcess());
		srtf.printNumOfContextSwitching();
		srtf.printGanttChart();
		//////////////////////////////////////
		RR rr= new RR (proc2, 5, 3, 1);
		print(rr.schedulingProcess());
		rr.printNumOfContextSwitching();
		rr.printGanttChart();
        //////////////////////////////////////
	    PreemptivePriority pp= new PreemptivePriority (proc4, 5, 1);
		print(pp.schedulingProcess());
		pp.printNumOfContextSwitching();
		pp.printGanttChart();
	    
	}
	
	public static void print(Process[] processes) {
        
		sort(processes);
		System.out.println("Process Name " + " Arrival Time " + " Completion Time " + " Burst Time " + "    Priority " + "     Waiting Time " + "   Turnaround Time");
		for (int i = 0; i < processes.length; i++) {
			System.out.println(" " + processes[i].getName() + "\t\t" + processes[i].getArrivalTime()
					+ "\t\t" + processes[i].getCompletionTime() + "\t\t" + processes[i].getBurstTime()
					+ "\t\t" + processes[i].getPriority()
					+ "\t\t" + processes[i].getWatingTime() + "\t\t" + processes[i].getTurnaroundTime());
		}
		System.out.println("----------------------------------------------------------------------------------------------------------");
		getAverageWaitingTime(processes);
		getAverageTurnaroundTime(processes);
	}
	
	public static void sort(Process[] processes) {
		
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
	
	static public void getAverageWaitingTime(Process[] processes) {

		float sumOfWaitingTime = 0;
		float averageWaitingTime = 0;
		
		for (int i = 0; i < processes.length; i++) {
			sumOfWaitingTime += processes[i].getWatingTime();
		}
		
		averageWaitingTime = sumOfWaitingTime / processes.length;
		
		System.out.println("Average Waiting Time        = " + averageWaitingTime);
	}
	
	static public void getAverageTurnaroundTime(Process[] processes) {

		float sumOfTurnaroundTime = 0;
		float averageTurnaroundTime = 0;
		
		for (int i = 0; i < processes.length; i++) {
			sumOfTurnaroundTime += processes[i].getTurnaroundTime();
		}
		
		averageTurnaroundTime = sumOfTurnaroundTime / processes.length;
		
		System.out.println("Average Turnaround Time     = " + averageTurnaroundTime);
	}

}
