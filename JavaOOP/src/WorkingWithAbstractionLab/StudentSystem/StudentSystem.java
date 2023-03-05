package WorkingWithAbstractionLab.StudentSystem;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> repo;

    public StudentSystem() {
        this.repo = new HashMap<>();
    }

    public Map<String, Student> getRepo() {
        return Collections.unmodifiableMap(repo);
    }

    public void analyzeUserInput(String userInput) {
        String[] tokenizedUserInput = userInput.split("\\s+");
        String command = tokenizedUserInput[0];
        switch (command) {
            case "Create":
                executeCreateCommand(tokenizedUserInput);
                break;
            case "Show":
                executeShowCommand(tokenizedUserInput);
                break;
        }
    }

    private void executeShowCommand(String[] tokenizedUserInput) {
        String targetedStudentName = tokenizedUserInput[1];
        Student requestedStudent = repo.get(targetedStudentName);
        if (requestedStudent != null) {
            System.out.printf("%s is %d years old. %s.\n",
                    requestedStudent.getName(),
                    requestedStudent.getAge(),
                    studentCommentary(requestedStudent.getGrade()));
        }
    }

    private void executeCreateCommand(String[] tokenizedCommand) {
        String studentName = tokenizedCommand[1];
        int studentAge = Integer.parseInt(tokenizedCommand[2]);
        double grade = Double.parseDouble(tokenizedCommand[3]);

        if (!repo.containsKey(studentName)) {
            repo.put(studentName, new Student(studentName, studentAge, grade));
        }
    }

    private String studentCommentary(double studentGrade) {
        if (studentGrade >= 5.d) {
            return "Excellent student";
        } else if (studentGrade >= 3.5d) {
            return "Average student";
        }
        return "Very nice person";
    }
}
