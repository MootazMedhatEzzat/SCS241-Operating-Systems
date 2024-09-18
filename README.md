# SCS241-Operating-Systems

<div align="center">
  <table width="100%" border="1" cellpadding="10" cellspacing="0">
    <tr style="background-color:#f2f2f2;">
      <td align="center" colspan="2"><strong>Assignment 2: CPU Scheduling.</strong></td>
    </tr>
    <tr>
      <td align="center"><strong>Names:</strong><br>Ahmed Elsayed Moein<br>Ahmed Sami Darwish<br>Abdulrahman Emad Kamel Ismail<br>Mootaz Medhat Ezzat Abdelwahab</td>
      <td align="center"><strong>IDs:</strong><br>20206120<br>20206136<br>20206148<br>20206074</td>
    </tr>
    <tr style="background-color:#f9f9f9;">
      <td align="center"><strong>Program:</strong><br>Software Engineering</td>
      <td align="center"><strong>Group:</strong><br>B (S5)</td>
    </tr>
    <tr>
      <td align="center" colspan="2"><strong>Delivered To:</strong><br>Prof. Khaled Wassif<br>TA. Ibrahim Ali Mohamed</td>
    </tr>
  </table>
</div>

---

## 📝 Assignment #2

Cairo University  
Faculty of Computers and Artificial Intelligence  
Operating Systems Course (Spring 2023)

---

### 📄 CPU Schedulers Simulator

Write a Java program to simulate the following schedulers:

1. **Preemptive Shortest-Job-First (SJF) Scheduling** with context switching.
2. **Round Robin (RR) Scheduling** with context switching.
3. **Preemptive Priority Scheduling** (with solving the starvation problem).
4. **AG Scheduling**:
   - Each process is provided a static time to execute called quantum.
   - Once a process is executed for a given time period, it’s called FCFS till the finishing of (ceil(52%)) of its Quantum time, then it’s converted to non-preemptive Priority till the finishing of the next (ceil(52%)), after that it’s converted to preemptive Shortest-Job-First (SJF).
   - There are 3 scenarios for the running process:
     1. The running process used all its quantum time and still has job to do (add this process to the end of the queue, then increase its Quantum time by Two).
     2. The running process was executed as non-preemptive Priority and didn’t use all its quantum time based on another process converted from ready to running (add this process to the end of the queue, and then increase its Quantum time by ceil(the remaining Quantum time/2)).
     3. The running process was executed as preemptive Shortest-Job-First (SJF) and didn’t use all its quantum time based on another process converted from ready to running (add this process to the end of the queue, and then increase its Quantum time by the remaining Quantum time).
     4. The running process didn’t use all of its quantum time because it no longer needs that time and the job was completed (set its quantum time to zero).

```
Example:
____________________________________________________________
| Process | Burst Time | Arrival Time | Priority | Quantum |
|---------|------------|--------------|----------|---------|
| P1      | 17         | 0            | 4        | 7       |
| P2      | 6          | 2            | 7        | 9       |
| P3      | 11         | 5            | 3        | 4       |
| P4      | 4          | 15           | 6        | 6       |
____________________________________________________________

Answer:
Quantum (7, 9, 4, 6)     -> ceil(25%) = (2, -, -, -) & ceil(50%) = (4, -, -, -)
Quantum (7+3, 9, 4, 6)   -> ceil(25%) = (-, 3, -, -) & ceil(50%) = (-, 5, -, -)
Quantum (10, 9+3, 4, 6)  -> ceil(25%) = (-, -, 1, -) & ceil(50%) = (-, -, 2, -)
Quantum (10, 12, 4+2, 6) -> ceil(25%) = (-, 3, -, -) & ceil(50%) = (-, 6, -, -)
Quantum (10, 0, 6, 6)    -> ceil(25%) = (3, -, -, -) & ceil(50%) = (5, -, -, -)
Quantum (10+4, 0, 6, 6)  -> ceil(25%) = (-, -, 2, -) & ceil(50%) = (-, -, 3, -)
Quantum (14, 0, 6+3, 6)  -> ceil(25%) = (-, -, -, 2) & ceil(50%) = (-, -, -, 3)
Quantum (14, 0, 9, 6+2)  -> ceil(25%) = (-, -, 3, -) & ceil(50%) = (-, -, 5, -)
Quantum (14, 0, 0, 8)    -> ceil(25%) = (4, -, -, -) & ceil(50%) = (7, -, -, -)
Quantum (14+7, 0, 0, 8)  -> ceil(52%) = (0, 0, 0, 2) & ceil(50%) = (-, -, -, 4)
Quantum (21, 0, 0, 0)    -> ceil(25%) = (6, -, -, -) & ceil(50%) = (11, -, -, -)

Execution Order:
| P1 | P2 | P3 | P2 | P1 | P3 | P4 | P3 | P1 | P4 | P1 |
|----|----|----|----|----|----|----|----|----|----|----|
0    4    7    9    12   15   18   20   26   33   35   38
```

### 🔢 Program Input
- Number of processes
- Round robin Time Quantum
- Context switching

For each process, you need to receive the following parameters from the user:
- Process Name
- Process Arrival Time
- Process Burst Time
- Process Priority

### 🖥️ Program Output
For each scheduler, output the following:
- Processes execution order
- Waiting Time for each process
- Turnaround Time for each process
- Average Waiting Time
- Average Turnaround Time
- Print all history updates of quantum time for each process (AG Scheduling)

### 🏆 Grading Criteria (BOUNS (15 grades))

|                                    | Preemptive Shortest-Job First (SJF) Scheduling | Round Robin (RR) Scheduling | Priority Scheduling | AG Scheduling | Grade |
|------------------------------------|-----------------------------------------------|-----------------------------|---------------------|---------------|-------|
| **Processes execution order**      | 6                                             | 6                           | 6                   | 13            | 31    |
| **Waiting Time for Each Process**  | 6                                             | 6                           | 6                   | 13            | 31    |
| **Turnaround Time for Each Process**| 2                                            | 2                           | 2                   | 4             | 10    |
| **Average Waiting Time**           | 2                                             | 2                           | 2                   | 4             | 10    |
| **Average Turnaround Time**        | 2                                             | 2                           | 2                   | 4             | 10    |
| **Print All History Update of Quantum Time for Each Process** | 0                    | 0                           | 0                   | 8             | 8     |
| **Grade**                          | **18**                                        | **18**                      | **18**              | **46**        | **100**|

### 🛠️ Programming Language and Development Tools Used

<table align="center" border="1" cellpadding="10">
  <thead>
    <tr>
      <th>Programming Language</th>
      <th>Development Tool</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td align="center">
        <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" title="Java" alt="Java" width="40" height="40"/>
      </td>
      <td align="center">
        <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/eclipse/eclipse-original.svg" title="Eclipse" alt="Eclipse" width="40" height="40"/>
      </td>
    </tr>
    <tr>
      <td align="center">
        Java
      </td>
      <td align="center">
        Eclipse IDE for Java Developers
      </td>
    </tr>
  </tbody>
</table>

---

## 💬 Let's Connect
Feel free to reach out to me if you'd like to collaborate on a project or discuss technology! As a Software Engineer, I'm always open to tackling new challenges, sharing knowledge, and growing through collaborative opportunities.

**Mootaz Medhat Ezzat Abdelwahab**  
🎓 Software Engineering Graduate | Faculty of Computers and Artificial Intelligence, Cairo University  

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mootaz-medhat-ezzat-abdelwahab-377a60244)
