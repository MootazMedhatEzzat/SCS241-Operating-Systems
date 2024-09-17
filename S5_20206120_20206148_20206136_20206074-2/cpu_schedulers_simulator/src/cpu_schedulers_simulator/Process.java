package cpu_schedulers_simulator;

public class Process {
	
	private String name;
	private int arrivalTime;
	private int burstTime;
	private int priority;
	private int completionTime;
	private int CSCompletionTime;
	private int progress;
	private int remainingTime;
	private int watingTime;
	private int turnaroundTime;
	private boolean isComplete;
	private boolean inQueue;
	
	// AG Scheduling
	private int quantum;

	public Process(String name, int arrivalTime, int burstTime, int priority) {
		
		setName(name);
		setArrivalTime(arrivalTime);
		setBurstTime(burstTime);	
		setPriority(priority);
		setRemainingTime();
		completionTime = 0;
		progress = 0;
		watingTime = 0;
		turnaroundTime = 0;
		
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	} 
	
	public void updatePriority() {
		this.priority --;
	} 
	
	public void setWatingTime() {
		watingTime = ((CSCompletionTime - arrivalTime) - burstTime);
	}
	
	public void setWatingTime(int watingTime) {
		this.watingTime = watingTime;
	}
	
	public void setTurnaroundTime() {
		turnaroundTime = watingTime + burstTime;
	}
	
	public void setCompletionTime(int Completion) {
		this.completionTime += Completion;
	}
	
	public void setCSCompletionTime(int CSCompletionTime) {
		this.CSCompletionTime = CSCompletionTime;
	}
	
	public void setProgress() {
		progress++;
	}
	
	public void setRemainingTime() {
		remainingTime = burstTime - progress;
	}
	
	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}
	
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	public void setInQueue(boolean inQueue) {
		this.inQueue = inQueue;
	}
	
	public String getName() {
		return name;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public int getBurstTime() {
		return burstTime;
	}

	public int getPriority() {
		return priority;
	}

	public int getCompletionTime() {
		return completionTime;
	}
	
	public int getProgress() {
		return progress;
	}
	
	public int getRemainingTime() {
		return remainingTime;
	}

	public int getWatingTime() {
		return watingTime;
	}

	public int getTurnaroundTime() {
		return turnaroundTime;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public boolean isInQueue() {
		return inQueue;
	}

	public int getCSCompletionTime() {
		return CSCompletionTime;
	}

	// AG Scheduling
	public int getQuantum() {
		return quantum;
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
	
	
	
}
