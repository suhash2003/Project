package com.project;

import java.util.*;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private Set<String> enrolledStudents;

    public Course(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new HashSet<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents.size();
    }

    public boolean enrollStudent(String studentId) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(studentId);
            return true;
        }
        return false;
    }

    public boolean removeStudent(String studentId) {
        return enrolledStudents.remove(studentId);
    }
}

class Student {
    private String studentId;
    private String name;
    private Set<String> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new HashSet<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public Set<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(String courseCode) {
        return registeredCourses.add(courseCode);
    }

    public boolean dropCourse(String courseCode) {
        return registeredCourses.remove(courseCode);
    }
}

public class CourseRegistrationSystem {
    private Map<String, Course> courseDatabase;
    private Map<String, Student> studentDatabase;

    public CourseRegistrationSystem() {
        courseDatabase = new HashMap<>();
        studentDatabase = new HashMap<>();
    }

    public void addCourse(String code, String title, String description, int capacity, List<String> schedule) {
        Course course = new Course(code, title, description, capacity, schedule);
        courseDatabase.put(code, course);
    }

    public void addStudent(String studentId, String name) {
        Student student = new Student(studentId, name);
        studentDatabase.put(studentId, student);
    }

    public void displayAvailableCourses() {
        System.out.println("Available Courses:");
        for (Course course : courseDatabase.values()) {
            System.out.println(course.getCode() + ": " + course.getTitle() +
                    " (Capacity: " + course.getCapacity() + ", Available Slots: " +
                    course.getAvailableSlots() + ")");
        }
    }

    public void registerCourse(String studentId, String courseCode) {
        Student student = studentDatabase.get(studentId);
        Course course = courseDatabase.get(courseCode);
        if (student != null && course != null && course.enrollStudent(studentId)) {
            student.registerCourse(courseCode);
            System.out.println("Course registration successful for " + student.getName() + ".");
        } else {
            System.out.println("Course registration failed. Please check availability or student details.");
        }
    }

    public void dropCourse(String studentId, String courseCode) {
        Student student = studentDatabase.get(studentId);
        Course course = courseDatabase.get(courseCode);
        if (student != null && course != null && course.removeStudent(studentId)) {
            student.dropCourse(courseCode);
            System.out.println("Course dropped successfully for " + student.getName() + ".");
        } else {
            System.out.println("Course drop failed. Please check student or course details.");
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Add some courses
        system.addCourse("CS101", "Introduction to Computer Science", "Introductory course on computer science", 50,
                Arrays.asList("Mon 9-11am", "Wed 9-11am"));
        system.addCourse("MATH201", "Calculus I", "Fundamental concepts of calculus", 40,
                Arrays.asList("Tue 10-12am", "Thu 10-12am"));

        // Add some students
        system.addStudent("1001", "John Doe");
        system.addStudent("1002", "Jane Smith");

        // Display available courses
        system.displayAvailableCourses();

        // Register some students to courses
        system.registerCourse("1001", "CS101");
        system.registerCourse("1001", "MATH201");
        system.registerCourse("1002", "CS101");

        // Drop a course for a student
        system.dropCourse("1001", "MATH201");
    }
}
