public class Course {
    private String courseCode;
    private String courseTitle;
    private int units;

    public Course(String courseCode, String courseTitle, int units) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.units = units;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public int getUnits() {
        return units;
    }

    public void displayCourse() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Title: " + courseTitle);
        System.out.println("Units: " + units);
    }
}