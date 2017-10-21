package model;

import exceptions.CourseFullException;
import exceptions.GPATooLowException;
import exceptions.MissingPrereqException;
import exceptions.NoCoursesTakenException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transcript {

    private String name;
    private int year;
    private int id;
    private double gpa;
    private List<Course> currentCourses;
    private List<Course> pastCourses;

    public Transcript(String studentName, int academicYear, int studentID) {
        this.name = studentName;
        this.year = academicYear;
        this.id = studentID;
        currentCourses = new ArrayList<>();
        pastCourses = new ArrayList<>();
    }

    // getters
    public String getName() {
        return name;
    }

    public int getAcademicYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    public List<Course> getCurrentCourses() {
        return currentCourses;
    }

    public List<Course> getPastCourses() {
        return pastCourses;
    }

    // REQUIRES: a non-empty list of past courses, i.e. size of pastCourses must be non-zero
    //           use the following formula to convert into a GPA
    //           GPA (4.0 scale) = (total percentage/20) - 1
    //           **Do you need a helper?**
    // EFFECTS: computes cGPA. In this case, we take it to mean that it is the total grades received so far, divided
    //          by the number of past courses taken
    public double computeGPA() throws NoCoursesTakenException {
        if (pastCourses.isEmpty()) {
            throw new NoCoursesTakenException("You suck, no courses taken");
        }
        double totalPercentage = 0;
        for (Course course : pastCourses) {
            totalPercentage += course.getGrade();
        }
        return totalPercentage / (20 * pastCourses.size()) - 1;
    }

    // REQUIRES: the GPA computed from the pastCourses field needs to be over 2.6 (out of 4.0 scale)
    // EFFECTS: promotes the student represented by the transcript
    //          to the next academic year if the REQUIRES clause is met, and return true
    //          else return false with no change to the year field
    public boolean promoteStudent() throws GPATooLowException, NoCoursesTakenException {
        if (pastCourses.isEmpty()) {
            throw new NoCoursesTakenException("Sry, no courses taken");
        }
        if (computeGPA() <= 2.6) {
            throw new GPATooLowException("You suck, your GPA is low");
        }
        year++;
        return true;
    }


    // MODIFIES: this
    // EFFECTS: adds the given course to the list of past courses and returns true,
    //          unless pastCourses contains given course, then does not add and returns false
    public boolean addToPastCourses(Course c) {
        if (pastCourses.contains(c)) {
            return false;
        }
        pastCourses.add(c);
        return true;
    }

    // REQUIRES: this transcript must have all the necessary prerequisites in the pastCourses field
    //           The course you want to add must have space for more students to register
    // MODIFIES: this
    // EFFECTS: adds a course (c) into the record
    public boolean addCourse(Course course) throws MissingPrereqException, CourseFullException {
        if (!pastCourses.containsAll(course.getPrereq())) {
            throw new MissingPrereqException("Not all prerequisites met");
        }
        if (course.isCourseFull()) {
            throw new CourseFullException("The course is too popular for you");
        }
        currentCourses.add(course);
        return true;
    }


}