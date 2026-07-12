package my.java.basics.employee;

public class Transaction {
    int transactionId;
    String department;
    Double  amount;

    public Transaction(int transactionId, String department, Double amount) {
        this.transactionId = transactionId;
        this.department = department;
        this.amount = amount;
    }
    
    public int getTransactionId() {
        return transactionId;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    @Override
    public String toString() {
        return "Transaction [transactionId=" + transactionId + ", department=" + department + ", amount=" + amount + "]";
    }
}

/*
my.java.basics.employee.Transaction@4e25154f

Yes, you are exactly right. That string is a representation of the memory location (or identity) of that specific object instance.

To be technically precise, it is a Hexadecimal Hash Code of the object.

Breaking down the format:
my.java.basics.employee.Transaction@4e25154f

my.java.basics.employee.Transaction: This is the fully qualified name of your class (Package Name + Class Name).

@: This acts as a separator.

4e25154f: This is the Hash Code in hexadecimal format.

Is it the actual physical RAM address?
Not exactly. While it behaves like a unique identifier for that specific object instance in memory, it is not the raw physical memory address (like 0x7ffee...) that the CPU uses.

Java uses a "Virtual Machine" (JVM), so it abstracts away direct memory addresses for security and stability. Instead, the JVM assigns a hash code to every object to track its identity. You can think of it as a "digital fingerprint" for that specific object instance.

Why this matters
Because it is a "fingerprint," this is how Java knows that two different objects are not the same, even if they have the same data inside them.

If you create two transactions with the exact same ID and Amount:

Transaction t1 = new Transaction(1, "HR", 1000.0);

Transaction t2 = new Transaction(1, "HR", 1000.0);

Even though the data inside is identical, t1.toString() and t2.toString() would return different hex codes (e.g., @4e25154f vs @7a81197d). This is Java’s way of saying, "These are two distinct items living in two different spots in the memory heap."

Which one should you use?
For your code logic: Always use Getters (Approach 1).

For finding bugs: Always use your IDE Debugger (Approach 2).

For reading logs/console: Use toString().

Since you are learning, the best habit to build is to keep your toString() methods in your POJOs (Plain Old Java Objects) because it makes your life as a developer infinitely easier when you are printing output to the console to verify your program's flow.

/*
Java
Transaction t1 = new Transaction(101, "Sales", 500.50);
System.out.println(t1); 
Output (With toString):

Plaintext
Transaction [transactionId=101, department=Sales, amount=500.5]
(Now you can instantly see the exact state of the object).

Why do we NEED to have this?
In modern software development, you rarely write toString() to show data to an end-user on a screen. Instead, you need it for the following reasons:

Painless Debugging: When your code breaks and you are inspecting variables in your IDE, having toString() allows you to instantly see the data inside your objects without having to click and expand every single variable.

Logging: When an error occurs on a server, developers write objects to log files to figure out what went wrong (e.g., logger.error("Failed to process: " + transaction);). Without toString(), your logs will just be full of useless hashcodes.

Printing Collections: If you put multiple transactions into a List and print the List, toString() ensures the whole list is readable.

Java
List<Transaction> list = List.of(t1, t2);
System.out.println(list); 
// Outputs: [Transaction [transactionId=1...], Transaction [transactionId=2...]]
Summary: You don't strictly need it for the code to compile or run, but it is a fundamental best practice for making your code debuggable and readable */
