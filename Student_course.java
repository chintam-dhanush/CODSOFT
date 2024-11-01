import java.util.*;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    List<String> registeredStudents; 

    Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>(); 
    }
}

class Student {
    String studentID;
    String name;
    String courseCode;

    Student(String studentID, String name, String courseCode) {
        this.studentID = studentID;
        this.name = name;
        this.courseCode = courseCode;
    }
}

class Student_course {
    Course[] courses = new Course[3]; 
    List<Student> students = new ArrayList<>(); 

    public Student_course() {
        courses[0] = new Course("CS101", "Intro to Computer Science", "Basics of programming and algorithms.", 50, "Mon/Wed 10:00 AM - 11:30 AM");
        courses[1] = new Course("MATH101", "Basic Mathematics", "Introduction to fundamental math concepts.", 40, "Tue/Thu 1:00 PM - 2:30 PM");
        courses[2] = new Course("ENG101", "English Fundamentals", "Overview of English language and literature.", 30, "Mon/Fri 9:00 AM - 10:30 AM");
    }

    public void displayCourses() {
        System.out.println("Course Details:");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.courseCode);
            System.out.println("Title: " + course.title);
            System.out.println("Description: " + course.description);
            System.out.println("Capacity: " + course.capacity);
            System.out.println("Schedule: " + course.schedule);
            System.out.println("Registered Students: " + course.registeredStudents.size() + "/" + course.capacity);
            System.out.println();
        }
    }

    public void displayStudents() {
        System.out.println("Students Registered for Courses:");
        for (Course course : courses) {
            System.out.println("Course: " + course.title);
            if (course.registeredStudents.isEmpty()) {
                System.out.println("No students registered.");
            } else {
                System.out.println("Registered Students: " + course.registeredStudents);
            }
            System.out.println();
        }
    }

    public void registerForCourse(String studentID, String name, String courseCode) {
        for (Student student : students) {
            if (student.studentID.equals(studentID) && student.courseCode.equals(courseCode)) {
                System.out.println("Student is already registered.");
                return;
            }
        }

        for (Course course : courses) {
            if (course.courseCode.equals(courseCode)) {
                if (course.registeredStudents.size() < course.capacity) {
                    course.registeredStudents.add(studentID);
                    students.add(new Student(studentID, name, courseCode));
                    System.out.println("Student " + name + " registered for " + courseCode);
                    return;
                } else {
                    System.out.println("Course " + courseCode + " is full.");
                    return;
                }
            }
        }
        System.out.println("Course not found.");
    }

    public void dropCourse(String studentID, String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equals(courseCode)) {
                if (course.registeredStudents.remove(studentID)) {
                    System.out.println("Student " + studentID + " dropped from " + courseCode);
                    students.removeIf(student -> student.studentID.equals(studentID) && student.courseCode.equals(courseCode));
                    return;
                } else {
                    System.out.println("Student " + studentID + " is not registered for " + courseCode);
                }
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public static void main(String[] args) {
        Student_course regSystem = new Student_course();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu: 1. Display Courses 2. Display Students 3. Register for Course 4. Drop Course 5. Exit");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    regSystem.displayCourses();
                    break;
                case 2:
                    regSystem.displayStudents();
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    regSystem.registerForCourse(studentID, name, courseCode);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();
                    regSystem.dropCourse(studentID, courseCode);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice <= 5 && choice > 0);
        
        
    }
}

