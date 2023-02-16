// package softUni;
 package ExamPreps.softuniStudents.softUni;

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
        if (data.size() > capacity) {
            return "The hall is full.";
        }
        Student temp = getStudent(student.getFirstName(),
                student.getLastName());
        if (temp == null) {
            data.add(student);
            return String.format("Added student %s %s.",
                    student.getFirstName(),
                    student.getFirstName());
        }
        return "Student is already in the hall.";
    }

    public Student getStudent(String firstName, String lastName) {
        for (Student student : data) {
            if (firstName.equals(student.getFirstName())
                    && lastName.equals(student.getLastName())) {
                return student;
            }
        }
        return null;
    }

    public String remove(Student student) {
        Student tmp = getStudent(student.getFirstName(), student.getLastName());
        if (tmp == null) {
            return "Student not found.";
        }
        data.remove(student);
        return String.format("Removed student %s %s.",
                student.getFirstName(),
                student.getLastName());
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hall size: ").append(data.size());
        for (Student student : data) {
            sb.append(System.lineSeparator());
            sb.append(student);
        }
        return sb.toString();
    }

}
