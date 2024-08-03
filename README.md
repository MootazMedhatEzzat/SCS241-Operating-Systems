# SCS241-Operating-Systems

<div align="center">
  <table width="100%" border="1" cellpadding="10" cellspacing="0">
    <tr style="background-color:#f2f2f2;">
      <td align="center" colspan="2"><strong>Assignment 1: Threads</strong></td>
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

## Assignment #1 (Deadline 18/11/2022)

Cairo University  
Faculty of Computers and Artificial Intelligence  
Operating Systems Course (Spring 2023)

### Description

Given `N` numbers and one file, our system simulates a real-life scenario of how buffering is run where a user will decide N to get the prime numbers from `0` to `N`. Somehow, the producer schedules the primes in a queue, and the consumer will use this queue to write them in the file. The application should use multiple threads to perform multiple actions simultaneously, reducing the time elapsed. 

Note: The Consumer thread will hold a lock when it starts and release it when the ready queue is empty and must notify all other threads.

<div align="center">
  <img src="https://github.com/user-attachments/assets/85490796-7f70-4e9a-aec0-2ea06f380d99" alt="image">
</div>

### Submission Instructions

1. Submission deadline: 18/11.
2. The assignment is to be submitted in groups of a minimum of 3 and a maximum of 4 persons.
3. Do not use built-in semaphores.

### Grading Criteria

<div align="center">
  <table width="50%">
    <tr>
      <td align="left"><strong>Criteria</strong></td>
      <td align="right"><strong>Points</strong></td>
    </tr>
    <tr>
      <td align="left">Class producer</td>
      <td align="right">1</td>
    </tr>
    <tr>
      <td align="left">Class consumer</td>
      <td align="right">1</td>
    </tr>
    <tr>
      <td align="left">Test cases</td>
      <td align="right">1</td>
    </tr>
    <tr>
      <td align="left">Handling queue is empty</td>
      <td align="right">0.5</td>
    </tr>
    <tr>
      <td align="left">Save to file</td>
      <td align="right">0.5</td>
    </tr>
    <tr>
      <td align="left">Calculate prime number</td>
      <td align="right">0.5</td>
    </tr>
    <tr>
      <td align="left">Using synchronization</td>
      <td align="right">0.5</td>
    </tr>
    <tr>
      <td align="left">GUI (real-time update of this GUI)</td>
      <td align="right">1</td>
    </tr>
  </table>
</div>
