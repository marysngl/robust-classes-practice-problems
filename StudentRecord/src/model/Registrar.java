package model;
import exceptions.CourseFullException;
import exceptions.GPATooLowException;
import exceptions.MissingPrereqException;
import exceptions.NoCoursesTakenException;

import java.util.ArrayList;
import java.util.List;

public class Registrar {

    private String name;
    private List<Transcript> students;

    public Registrar(String name) {
        this.name = name;
        students = new ArrayList<>();
    }

    // getters
    public String getName() {
        return name;
    }
    public List<Transcript> getStudents() {
        return students;
    }

    // MODIFIES: this
    // EFFECTS: returns true if the student (represented as a transcript) was successfully added to the
    //          Registrar's list. Remember to check if the student already exists in the list
    public boolean addStudent(Transcript stu) {
        if (students.contains(stu)) {
            return false;
        }
        students.add(stu);
        return true;
    }

    //REQUIRES: that the student represented by the transcript has the necessary prerequisite required
    //          to take the course, and the course is not full
    // EFFECTS: registers a given student represented by tct to a course c.
    public boolean registerStudent(Course c, Transcript tct) throws CourseFullException, MissingPrereqException {
        if (!tct.getPastCourses().containsAll(c.getPrereq())) {
            throw new MissingPrereqException("Not all prerequisites present");
        }
        if (c.isCourseFull()) {
            throw new CourseFullException("The course is full");
        }
        c.addStudent();
        tct.addCourse(c);
        return true;
    }

    // This is ridiculous: what, if at least one student has low GPA, we won't promote the other students?
    // EFFECTS: promotes all students to their next year level.
    public void promoteAllStudents() throws GPATooLowException, NoCoursesTakenException {
        for (Transcript student : students) {
            student.promoteStudent();
        }
    }


}