package my.java.basics.employee;

public class Employee {
    public int empid;
    public String name;
    public double salary;
    
    public Employee(int empid, String name, double salary) {
        this.empid = empid;
        this.name = name;
        this.salary = salary;
    }
    
    public int getEmpid() {
        return empid;
    }
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
    
    @Override
    public String toString() {
        return "Employee [empid=" + empid + ", name=" + name + ", salary=" + salary + "]";
    }
}
