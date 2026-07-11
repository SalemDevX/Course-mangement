import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CourseManager manager = new CourseManager();

        int choice = 0;

        while (choice != 7) {
            System.out.println("\n==================================");
            System.out.println(" STUDENT COURSE MANAGEMENT SYSTEM");
            System.out.println("==================================");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Search Course by Code");
            System.out.println("4. Compute Total Units");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit Program");
            System.out.print("Enter your choice: ");

            try {
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter course code: ");
                        String code = input.nextLine().trim().toUpperCase();

                        System.out.print("Enter course title: ");
                        String title = input.nextLine().trim();

                        System.out.print("Enter number of units: ");
                        int units = input.nextInt();
                        input.nextLine();

                        if (code.isEmpty()) {
                            System.out.println("Course code cannot be empty.");
                            break;
                        }

                        if (title.isEmpty()) {
                            System.out.println("Course title cannot be empty.");
                            break;
                        }

                        if (units <= 0) {
                            System.out.println("Units must be greater than zero.");
                            break;
                        }

                        Course newCourse = new Course(code, title, units);
                        manager.addCourse(newCourse);
                        break;

                    case 2:
                        manager.viewAllCourses();
                        break;

                    case 3:
                        System.out.print("Enter course code to search: ");
                        String searchCode = input.nextLine().trim().toUpperCase();

                        Course foundCourse = manager.searchCourse(searchCode);

                        if (foundCourse == null) {
                            System.out.println("Course not found.");
                        } else {
                            System.out.println("\nCourse found:");
                            foundCourse.displayCourse();
                        }
                        break;

                    case 4:
                        int totalUnits = manager.computeTotalUnits();
                        System.out.println("Total course units: " + totalUnits);
                        break;

                    case 5:
                        manager.saveToFile();
                        break;

                    case 6:
                        manager.loadFromFile();
                        break;

                    case 7:
                        System.out.println("Thank you for using the program.");
                        break;

                    default:
                        System.out.println("Invalid choice. Enter a number from 1 to 7.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }

        input.close();
    }
}