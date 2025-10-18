import java.util.Scanner;

public class StudentManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static Student[] students = new Student[100];
    static int count = 0;

    enum Department {
        COMPUTER, IT, MECHANICAL, ELECTRONICS, CIVIL,
        MBA, BBA
    }

    enum School { ENGINEERING, MANAGEMENT }
    enum Year { FY, SY, TY }

    static class Student {
        static int nextPRN = 10001;

        String name;
        int prn;
        String admissionType;
        Department department;
        School school;
        Year year;
        float sgpa;
        float cgpa;

        // Constructor 1: Every Student
        Student(String name, Department department, School school, boolean isLateral, Year year, float sgpa, float cgpa) {
            this.name = name;
            this.prn = nextPRN++;
            this.admissionType = isLateral ? "Lateral Entry" : "Direct FY";
            this.department = department;
            this.school = school;
            this.year = year;
            this.sgpa = sgpa;
            this.cgpa = cgpa;
        }

        // Constructor 2: FY Students
        Student(String name, Department department, School school, boolean isLateral, Year year) {
            this(name, department, school, isLateral, year, 0, 0);
        }

        // Constructor 3: Name & School
        Student(String name, School school) {
            this.name = name;
            this.prn = nextPRN++;
            this.admissionType = "Direct FY";
            this.school = school;
            this.department = Department.COMPUTER; // default dept
            this.year = Year.FY;
            this.sgpa = 0;
            this.cgpa = 0;
        }

        // Constructor 4: Default
        Student() {
            this.name = "Unknown";
            this.prn = nextPRN++;
            this.admissionType = "Direct FY";
            this.department = Department.COMPUTER;
            this.school = School.ENGINEERING;
            this.year = Year.FY;
            this.sgpa = 0;
            this.cgpa = 0;
        }

        void display() {
            System.out.println("Name: " + name);
            System.out.println("PRN: " + prn);
            System.out.println("Admission Type: " + admissionType);
            System.out.println("School: " + school);
            System.out.println("Department: " + department);
            System.out.println("Year: " + year);
            if (year != Year.FY) {
                System.out.println("SGPA: " + sgpa);
                System.out.println("CGPA: " + cgpa);
            }
            System.out.println("---------------------------------");
        }
    }

    static Department[] getDepartmentsBySchool(School school) {
        if (school == School.ENGINEERING) {
            return new Department[]{Department.COMPUTER, Department.IT, Department.MECHANICAL, Department.ELECTRONICS, Department.CIVIL};
        } else {
            return new Department[]{Department.MBA, Department.BBA};
        }
    }

    void adminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search by PRN");
            System.out.println("4. Search by Department");
            System.out.println("5. Update Department");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> addStudent();
                case 2 -> displayAll();
                case 3 -> searchByPRN();
                case 4 -> searchByDepartment();
                case 5 -> updateDepartment();
                case 6 -> {
                    System.out.println("Exiting Admin Menu...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    void studentMenu() {
        System.out.print("Enter your PRN: ");
        int prn = sc.nextInt(); sc.nextLine();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (students[i].prn == prn) {
                students[i].display();
                found = true;
                break;
            }
        }

        if (!found) System.out.println("Student not found.");
    }

    void addStudent() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.println("Choose School:");
        for (School s : School.values()) {
            System.out.println("- " + s);
        }
        System.out.print("Enter school: ");
        School school = School.valueOf(sc.nextLine().toUpperCase());

        Department[] validDepts = getDepartmentsBySchool(school);
        System.out.println("Available Departments:");
        for (Department d : validDepts) {
            System.out.println("- " + d);
        }
        System.out.print("Enter department: ");
        Department dept = Department.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Is this a Lateral admission? (yes/no): ");
        boolean isLateral = sc.nextLine().equalsIgnoreCase("yes");

        System.out.println("Choose Year: FY, SY, TY");
        Year year = Year.valueOf(sc.nextLine().toUpperCase());

        float sgpa = 0, cgpa = 0;
        if (year != Year.FY) {
            System.out.print("Enter SGPA: ");
            sgpa = sc.nextFloat(); sc.nextLine();
            System.out.print("Enter CGPA: ");
            cgpa = sc.nextFloat(); sc.nextLine();

            students[count++] = new Student(name, dept, school, isLateral, year, sgpa, cgpa);
        } else {
            students[count++] = new Student(name, dept, school, isLateral, year);
        }

        System.out.println("Student added successfully.\n");
    }

    void displayAll() {
        if (count == 0) {
            System.out.println("No students to display.");
            return;
        }
        for (int i = 0; i < count; i++) {
            students[i].display();
        }
    }

    void searchByPRN() {
        System.out.print("Enter PRN to search: ");
        int prn = sc.nextInt(); sc.nextLine();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (students[i].prn == prn) {
                students[i].display();
                found = true;
                break;
            }
        }

        if (!found) System.out.println("Student not found.");
    }

    void searchByDepartment() {
        System.out.print("Enter department to search: ");
        Department dept = Department.valueOf(sc.nextLine().toUpperCase());
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (students[i].department == dept) {
                students[i].display();
                found = true;
            }
        }

        if (!found) System.out.println("No students found in that department.");
    }

    void updateDepartment() {
        System.out.print("Enter PRN to update department: ");
        int prn = sc.nextInt(); sc.nextLine();
        boolean updated = false;

        for (int i = 0; i < count; i++) {
            if (students[i].prn == prn) {
                School school = students[i].school;
                Department[] validDepts = getDepartmentsBySchool(school);

                System.out.println("Available Departments for " + school + ":");
                for (Department d : validDepts) {
                    System.out.println("- " + d);
                }
                System.out.print("Enter new department: ");
                students[i].department = Department.valueOf(sc.nextLine().toUpperCase());
                System.out.println("Department updated.");
                updated = true;
                break;
            }
        }

        if (!updated) System.out.println("Student not found.");
    }

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();

        while (true) {
            System.out.println("\n--- Login ---");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt(); 
            sc.nextLine();

            switch (choice) {
                case 1 -> system.adminMenu();
                case 2 -> system.studentMenu();
                case 3 -> {
                    System.out.println("Exiting System...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}