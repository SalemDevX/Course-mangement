import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CourseManager {
    private ArrayList<Course> courses;

    public CourseManager() {
        courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("Course added successfully.");
    }

    public void viewAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses have been added yet.");
            return;
        }

        System.out.println("\n===== RECORDED COURSES =====");

        for (int i = 0; i < courses.size(); i++) {
            System.out.println("\nCourse " + (i + 1));
            courses.get(i).displayCourse();
        }
    }

    public Course searchCourse(String courseCode) {
        return recursiveSearch(courseCode.trim().toUpperCase(), 0);
    }

    private Course recursiveSearch(String courseCode, int index) {
        if (index >= courses.size()) {
            return null;
        }

        Course currentCourse = courses.get(index);

        if (currentCourse.getCourseCode().equalsIgnoreCase(courseCode)) {
            return currentCourse;
        }

        return recursiveSearch(courseCode, index + 1);
    }

    public int computeTotalUnits() {
        int totalUnits = 0;

        for (Course course : courses) {
            totalUnits = totalUnits + course.getUnits();
        }

        return totalUnits;
    }

    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter("courses.txt");

            for (Course course : courses) {
                writer.write(
                        course.getCourseCode() + "," +
                                course.getCourseTitle() + "," +
                                course.getUnits() + "\n"
                );
            }

            writer.close();
            System.out.println("Courses saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the courses.");
        }
    }

    public void loadFromFile() {
        try {
            File file = new File("courses.txt");
            Scanner fileReader = new Scanner(file);

            courses.clear();

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] courseData = line.split(",");

                if (courseData.length == 3) {
                    String courseCode = courseData[0];
                    String courseTitle = courseData[1];
                    int units = Integer.parseInt(courseData[2]);

                    Course course = new Course(courseCode, courseTitle, units);
                    courses.add(course);
                }
            }

            fileReader.close();
            System.out.println("Courses loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("The courses file was not found.");
        } catch (NumberFormatException e) {
            System.out.println("The file contains an invalid course unit.");
        }
    }
}
