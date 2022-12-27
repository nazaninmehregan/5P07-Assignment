# 5P07-Assignment

## Benchmarking
[task 1 repository](https://github.com/nazaninmehregan/5P07-Assignment/tree/master/Task1)

### Experimental Environment
```

Processor	Intel(R) Core(TM) i7-8565U CPU @ 1.80GHz   1.99 GHz
Installed RAM	12.0 GB (11.9 GB usable)
System type	64-bit operating system, x64-based processor

Data Size: 1 million integers
Data Structures: HashSet, LinkedHashSet, TreeSet
Data Operations: Insert, Sort, Search

Benchmark parameters:
JMH version: 1.36
Warmup: 2 iterations, 10 s each
Measurement: 5 iterations, 10 s each
Time Unit: Seconds

```
In this task, I implemented different methods for benchmarking each of the insert, sort and search operations using the HashSet, LinkedHashSet, and TreeSet data structures.
I first implemented the insertion methods in order to initialize the different sets and be able to implement the search and sort methods. All of the methods were benchmarked in one run that took about 1 and a half hour to complete.

### Compile & Build
mvn clean install

### RUN
java -jar target/benchmarks.jar > output.txt

## Result Evaluation

### **//Insert**


| **Data Structure**        | **Throughput** (ops/s)          | **Average Time(Speed)** (s/op) |
| :------------- |:-------------:| :-----:|
| HashSet     | 11.350 | 0.077 |
| LinkedHashSet      | 9.629      |   0.071 |
| TreeSet | 2.608      |    0.350 |

HashSet gives better performance than the LinkedHashSet and TreeSet. LinkedHashSet has moderate and slower performance since it uses LinkedList internally to ensure element insertion order. TreeSet gives the least performance because it has to sort the elements after each insertion and removal operation.


### **//Search (Contain)**

| **Data Structure**        | **Throughput** (ops/s)          | **Average Speed** (s/op) |
| :------------- |:-------------:| :-----:|
| HashSet     | 35376978.027 | 10⁻<sup>7</sup> |
| LinkedHashSet      | 34963825.247      |  10⁻<sup>7</sup> |
| TreeSet | 35496029.579   |    10⁻<sup>7</sup> |

HashSet is the fastest for the search operation since the items are randomly placed in the set and the throughput would be higher.
Linked hashset is a close second in throughput and speed since its elements maintain an insertion order. In contrasst, treeset has the lowest throughput since the items in it are ordered and when looking for one item it takes a long time to get to it as it might bethe last item in the tree.


### **//Sort**

| **Data Structure**        | **Throughput** (ops/s)          | **Average Speed** (s/op) |
| :------------- |:-------------:| :-----:|
| HashSet     | 13782696.867 | 10⁻⁸ |
| LinkedHashSet      | 20309539.756      |   10⁻⁸ |
| TreeSet | 25150085.460     |   10⁻⁹ |

When data is inserted into treeSet, the data is already sorted. Even if the sorted order has to be in descending order, we can quickly reverse the order of the tree. That's why treeset has the higest throughput.

Data in hashset are randomly positioned therefore it has the lowest performance among others.

The items in a LinkedHashset are all connected to each other, making it simple to arrange them in the appropriate order. This contributes to the set's higher throughput.

## Discussions

When no insertion order or sorting of items is necessary yet we want to keep unique objects, we should utilize HashSet.
It has an O(1) order of complexity for item insertion, removal, and access.

When the insertion order of entries is necessary, we should utilize LinkedHashSet.
It has an O(1) order of complexity for item insertion, removal, and access.

When we need to sort the order of items based on a Comparator, we should use TreeSet.
The complexity of inserting, deleting, and accessing objects is O(log(n)).


## OpenTelemetry Tracing
[task 2 repository](https://github.com/nazaninmehregan/5P07-Assignment/tree/master/Task2)


In this task, I used socket programming to write the code for client and seerve. The client connects to the specified port and address. Then fore each file, a thread is created that will run the "send" method. This method gets the file number as input, reads the content of the file, and sends it to the server.
The server is also implemented in a way that can handle requests in parallel. Every time a connection is bound to the server, it creates a new thread that runs the "receiver" method. This method gets the message (contents of a file) from the client and writes it down to a file called server_file.txt.

It is worth mentioning that no race condition occured while running this app because the final server_file is composed of the first 10 files and no data is corrupted. Therefore, there was no need of locking or releasing the shared resources between the active threads.


## Tracing

I installed OpenTelemetry API and SDK for getting traces from code. I also set up Jaeger on Docker for analysis of the traced data.
I tried to do the tracing automatically as I thought it would be more accurate but the automatic implementation made of use of the Flask library so I wrote the tracer code manually and put them in critical parts of the program.
The tracing result in Jaeger is shown in the picture below:

![image](https://user-images.githubusercontent.com/34520824/209613300-c4f92c4c-ace5-4004-bd09-ed28c997bd1a.png)

This data shows that there has been 11 spans during the runtime and the depth was 2. It took 31ms to read and send all the files from client to server.
Also, it took different amounts of time for each file to be processed and sent over to the server. This might be related to factors such as file size or resources such as cpu and cache.

![image](https://user-images.githubusercontent.com/34520824/209615142-935546e5-e3c4-4fd6-b011-fe0e97384dcc.png)
This image shows the exact timestamps that the client sends requests to the server.

![image](https://user-images.githubusercontent.com/34520824/209615581-8e0d44bd-a362-4570-b8a7-0dc8240af91b.png)
This image shows the trace data regarding number of active threads in the program and it shows that there have been a maximum of two active threads while the application was running.




