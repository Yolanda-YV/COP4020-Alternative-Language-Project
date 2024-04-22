# COP4020-Alternative-Language-Project

---

## About Java
I chose to use Java version 1.8.0_411 for the Alternative Language Project because of the in-demand languages listed in the assignment, Java was the language I never programmed in before.

### Object-Oriented Programming
Java is an object oriented programming language, so Java programs are centered around objects. So, everything is treated as an object whose attributes and methods are defined by classes.

### File Ingestion
Java uses the File class to create a file object and the Scanner class to read the file line by line.

### Conditional Statements
If-statements, If-Else-statements, Switch statements, and ternary operators are used for conditional statements in Java.

### Assignment Statements
Assignment statements are used to store and manipulate values inside variables in Java.

### Loops
Java makes use of for-loops, while-loops, and do-while loops. Like in C++, there are 2 ways to implemenet a for-loop in Java: ```for (initialization; condition; update) {}``` or ```for (type variable : array) {}```. I mainly used the former method in my own code.

### Subprograms
Because Java is an object-oriented programming language, subprograms are referred to as methods which are declared within classes. As they are a part of these classes, their accessibility can be controlled with modifiers like ```public``` and ```private```.

### Unit Testing
Java has JUnit framework, which is open-source and was specifically made for unit testing. Unit tests are organized into test classes which contain test methods which are test cases.

### Exception Handling
Java has try-catch blocks and exceptions (like FileNotFoundException). It's also possible to throw exceptions explicitly using the ```throw``` keyword.

## Libraries Used

### java.util
Imported parts of the Java.util package, specifically the following classes/packages
- **regex:** is a package that includes the Pattern and Matcher class
  - both classes were used to support the use of regular expressions
- **Scanner:** is a class used to get user input
  - I used this class to read the cells.csv file
- **ArrayList:** is a class that provides a resizable array
  - I used an Array List to hold Cell objects, as well as to hold results in multiple Cell class methods

### java.io
Imported parts of the Java.io package, specifically the following classes
- **File:** a class that lets you work with files
  - I used this to create a file object using the cells.csv file

## Questions Answered
#### 1. What company (oem) has the highest average weight of the phone body?
Lenovo.
#### 2. Was there any phones that were announced in one year and released in another? What are they? Give me the oem and models.
Yes, there were phones that were announced and released in different years. There were four in total: 
   - phone 1 (oem: Motorola, model: One Hyper)
   - phone 2 (oem: Motorola, model: Razr 2019)
   - phone 3 (oem: Xiaomi, model: Redmi K30 5G)
   - phone 4 (oem: Xiaomi, model: Mi Mix Alpha)
#### 3. How many phones have only one feature sensor?
433 phones had only one feature sensor.
#### 4. What year had the most phones launched in any year later than 1999? 
2019 had the most phones launched in the years past 1999.
#### Output from the Main.java answering all questions above
<img width="812" alt="Screenshot 2024-04-21 at 9 22 38 PM" src="https://github.com/Yolanda-YV/COP4020-Alternative-Language-Project/assets/76263266/3ccd70a3-d430-4c88-98dc-24551e24504b">

