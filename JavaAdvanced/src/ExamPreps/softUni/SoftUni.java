package ExamPreps.softUni;

import java.util.ArrayList;
import java.util.List;

public class SoftUni {
    int capacity;
    List<Student> data;

    public SoftUni(int capacity) {
        this.capacity = capacity;
        data = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCount() {
        return data.size();
    }

    public String insert(Student student) {
        if (data.contains(student)) {
            return "Student is already in the hall.";
        }
        if (data.size() >= capacity) {
            return "The hall is full.";
        }

        data.add(student);
        return String.format("Added student %s %s.",
                student.firstName, student.lastName);
    }

    public String remove(Student student) {
        boolean wasStudentRemoved = data.remove(student);
        if (wasStudentRemoved) {
            return String.format("Removed student %s %s.",
                    student.firstName, student.lastName);
        }
        return "Student not found.";
    }

    public Student getStudent(String firstName, String lastName) {
        for (Student student : data) {
            if (student.firstName.equals(firstName)
                    && student.lastName.equals(lastName)) {
                return student;
            }
        }
        return null;
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hall size: ");
        sb.append(data.size());
        sb.append(System.lineSeparator());
        for (Student student : data) {
            sb.append(student.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
