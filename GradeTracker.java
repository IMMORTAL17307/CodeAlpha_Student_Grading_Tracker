import java.util.ArrayList;
import java.util.Scanner;

public class GradeTracker {
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("       CODEALPHA STUDENT GRADE TRACKER           ");
        System.out.println("=================================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    displayStudents();
                    break;
                case "3":
                    displayStatistics();
                    break;
                case "4":
                    System.out.println("\nThank you for using Student Grade Tracker. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\n[!] Invalid option. Please select a valid option (1-4).");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n>>> MENU OPTIONS <<<");
        System.out.println("1. Add Student & Grade");
        System.out.println("2. Display All Students & Grades");
        System.out.println("3. Display Summary & Statistics");
        System.out.println("4. Exit");
        System.out.print("Choose an option (1-4): ");
    }

    private static void addStudent() {
        System.out.println("\n--- Add Student & Grade ---");
        
        String name;
        while (true) {
            System.out.print("Enter student's name: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                break;
            }
            System.out.println("[!] Student name cannot be empty. Please try again.");
        }
        double grade = -1;
        while (true) {
            System.out.print("Enter student's grade (0 - 100): ");
            String gradeInput = scanner.nextLine().trim();
            try {
                grade = Double.parseDouble(gradeInput);
                if (grade >= 0 && grade <= 100) {
                    break;
                } else {
                    System.out.println("[!] Grade must be between 0 and 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid input. Please enter a numerical value for the grade.");
            }
        }

        students.add(new Student(name, grade));
        System.out.println("[✓] Student \"" + name + "\" with grade " + grade + " successfully added.");
    }

    private static void displayStudents() {
        System.out.println("\n--- Student Records ---");
        if (students.isEmpty()) {
            System.out.println("No student records available yet.");
            return;
        }

        System.out.printf("%-4s | %-25s | %-6s\n", "No.", "Student Name", "Grade");
        System.out.println("----------------------------------------------");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("%-4d | %-25s | %-6.2f\n", (i + 1), s.getName(), s.getGrade());
        }
    }

    private static void displayStatistics() {
        System.out.println("\n--- Grade Statistics ---");
        if (students.isEmpty()) {
            System.out.println("No data available to calculate statistics. Please add students first.");
            return;
        }

        double total = 0;
        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;
        
        ArrayList<String> highestScorers = new ArrayList<>();
        ArrayList<String> lowestScorers = new ArrayList<>();

        for (Student s : students) {
            double grade = s.getGrade();
            total += grade;

            if (grade > highest) {
                highest = grade;
                highestScorers.clear();
                highestScorers.add(s.getName());
            } else if (grade == highest) {
                highestScorers.add(s.getName());
            }

            if (grade < lowest) {
                lowest = grade;
                lowestScorers.clear();
                lowestScorers.add(s.getName());
            } else if (grade == lowest) {
                lowestScorers.add(s.getName());
            }
        }

        double average = total / students.size();

        System.out.printf("Total Students Checked : %d\n", students.size());
        System.out.printf("Average Grade          : %.2f\n", average);
        System.out.printf("Highest Grade          : %.2f (Achieved by: %s)\n", highest, String.join(", ", highestScorers));
        System.out.printf("Lowest Grade           : %.2f (Achieved by: %s)\n", lowest, String.join(", ", lowestScorers));
    }
}
