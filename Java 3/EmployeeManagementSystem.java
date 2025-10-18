import java.util.Scanner;

class Employee {
    protected int empID;
    protected double basicSalary;
    protected String department;

    public Employee(int empID, double basicSalary, String department) {
        this.empID = empID;
        this.basicSalary = basicSalary;
        this.department = department;
    }

    public void displayDetails() {
        System.out.println("\nEmployee ID: " + empID);
        System.out.println("Department: " + department);
        System.out.println("Basic Salary: " + basicSalary);
    }
}

class Salary extends Employee {
    protected double hra;
    protected double da;

    public Salary(int empID, double basicSalary, String department) {
        super(empID, basicSalary, department);
    }

    public void calculateAllowances() {
        hra = basicSalary * 0.20;
        da = basicSalary * 0.10;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("HRA: " + hra);
        System.out.println("DA: " + da);
    }
}

class Award extends Salary {
    protected double performanceAward;

    public Award(int empID, double basicSalary, String department) {
        super(empID, basicSalary, department);
    }

    public void calculateAward(boolean outstanding) {
        performanceAward = outstanding ? 5000 : 0;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Performance Award: " + performanceAward);
    }
}

class HRPanel extends Salary {
    private double hrf;
    private double pf;
    private double pt;

    public HRPanel(int empID, double basicSalary, String department) {
        super(empID, basicSalary, department);
    }

    public void calculateDeductions() {
        hrf = basicSalary * 0.02;
        pf = basicSalary * 0.12;
        pt = 200;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("HRF: " + hrf);
        System.out.println("PF: " + pf);
        System.out.println("PT: " + pt);
    }

    public void completeSalaryBreakdown() {
        double gross = basicSalary + hra + da;
        double deductions = hrf + pf + pt;
        double netSalary = gross - deductions;

        System.out.println("------ Salary Breakdown ------");
        System.out.println("Gross Salary: " + gross);
        System.out.println("Total Deductions: " + deductions);
        System.out.println("Net Salary: " + netSalary);
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Multilevel Inheritance Example ===");
        System.out.print("Enter Employee ID: ");
        int empID = sc.nextInt();
        System.out.print("Enter Basic Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();

        Award emp1 = new Award(empID, salary, dept);
        emp1.calculateAllowances();
        System.out.print("Is performance outstanding? (true/false): ");
        boolean outstanding = sc.nextBoolean();
        emp1.calculateAward(outstanding);
        emp1.displayDetails();

        System.out.println("\n=== Hierarchical Inheritance Example ===");
        System.out.print("Enter Employee ID: ");
        int empID2 = sc.nextInt();
        System.out.print("Enter Basic Salary: ");
        double salary2 = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Department: ");
        String dept2 = sc.nextLine();

        HRPanel emp2 = new HRPanel(empID2, salary2, dept2);
        emp2.calculateAllowances();
        emp2.calculateDeductions();
        emp2.displayDetails();
        emp2.completeSalaryBreakdown();

        sc.close();
    }
}