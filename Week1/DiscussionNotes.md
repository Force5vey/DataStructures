(Perform set operations on priority queues)

Write a program that creates two priority queues for integers. Prompt the user to enter two lines of integers.

Read each line of integers as a string and extract the integers from the string and add the integers to the two queues, respectively. Display their union, difference, and intersection. Note that the priority queue can have duplicates. The union of two priority queues may have duplicates. Display all output in increasing order.

Sample Run

Enter integers for priority queue 1: 1 9 2 30 21 4 1
Enter integers for priority queue 2: 12 19 2 10 12 1
The union of the two priority queues is 
1 1 1 2 2 4 9 10 12 12 19 21 30 
The difference of the two priority queues is 
4 9 21 30 
The intersection of the two priority queues is 
1 1 2



####


ok, had to do a short refresher on the set _[things]_ for union difference and intersection. union makes sense and with priority queues duplicates are allowed. For difference it isn't unique to both, it is what is in set a that isn't in set b(this allows for unique numbers in b to not be shown in the difference because it is only from A). Further, for intersection my understanding is min(n, m). so for the sample output, intersection should be 1,2. since they give 1,1,2...I amgoing to make the program preserve teh count of set a so much as it exists in set b.


My logical steps:

Read input from user.
This will utilize nextint from scanner. a robust program would do input checking and reprompt but for this exercise I will assume proper input and simply catch errors and abort if found.

Parse input. as I read integers from the input I will put them into a PriorityQueue<Integer>. This will be two distinct queues, Set a and Set b.

comput union. this is all numbers from both sets, priority queue will automatically sort so should be able to add set be and a to a union queue and then print those results.

Compute Difference.

Create a copy of set a. then iterate through a removing anything that exists in set b. then print those results.

Compute the intersection. Intersection as I know it would not include duplicates, only one of each number that is unique to A that isn't in set b. However, the sample in the problem instructions shows 1,1,2 so I will keep the number of iterations in set a of a given value so long as it exists in set b.

Display results.


####

Dev Notes.

I named my project PriorityQueue...and had some initial issues because it didn't click that I named my class the same as the java library. The compiler was thinking I was trying to paramaterize the class, I renamed my project (and thus main class) to PriorityQueueProject and then that resolved the issues with creating a new PriorityQueue object.


Working through the input. I am running into a bit of trouble because between intermediate programming last semester and this class (my only java use is in classes) I have been doing A LOT of C# and C# is quite similar to java but just different enough to mess me up.

I initially started gathering next ints, then reread the problem instructions, and pulled in the full line as a string and am using String.split(" ") to get an array of String then parsing the values while adding to the priority queue. I would normally validate input but for the sake of this project I am just doing a try and if any number format exception is found I am printing the error and closing the program. This method is very strict, it errors out even if there are two spaces between numbers. 

With this, I have a 'debug' print of the values when I enter them. I can enter the values then with the priority queue printing to screen and they are auto sorted.

Moving to the union, this is straight forward, just offer(value) each value of setB into setA, and it will sort and print all values.

I have union printing, easy peasy, just add set a and set b to a new queue and it sorts and adds values and I can print each. I used a new PriorityQueue so I could maintain the values in each set. 

moving on to the difference. I will need a copy of setA (so I don't lose the values) and then remove everything that isn't also in setB. I'm going to try out:

iterate though setA, do a check if setB contains that value, if it does, remove from set a copy, when finished I can print all values in set a copy.








