package ExamPreps;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Internship {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int tasksCount = Integer.parseInt(scan.nextLine());
        int applicantsCount = Integer.parseInt(scan.nextLine());
        ArrayDeque<String> tasksStack = new ArrayDeque<>();
        ArrayDeque<String> applicantsQueue = new ArrayDeque<>();

        for (int i = 0; i < tasksCount; i++) {
            tasksStack.push(scan.nextLine());
        }
        for (int i = 0; i < applicantsCount; i++) {
            String applicantName = scan.nextLine();
            if (isNameValid(applicantName)) {
                applicantsQueue.offer(applicantName);
            }
        }

        while ((!tasksStack.isEmpty() && !applicantsQueue.isEmpty())) {


            String taskName = tasksStack.pop();
            String applicantNames = applicantsQueue.poll();

            if (sumOfChars(applicantNames) > sumOfChars(taskName)) {
                //Task is solved
                applicantsQueue.offer(applicantNames);
                System.out.printf("%s solved %s.\n", applicantNames, taskName);
            } else {
                tasksStack.offer(taskName); //using the stack as Queue
                System.out.printf("%s failed %s.\n", applicantNames, taskName);
            }

            if (applicantsQueue.size() == 1) {
                System.out.printf("%s gets the job!\n", applicantsQueue.peek());
                return;
            }
        }


        if (tasksStack.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String applicants : applicantsQueue) {
                sb.append(applicants);
                sb.append(", ");
            }
            ;
            System.out.println(sb.substring(0, sb.length() - 2));
        }
    }



    public static boolean isNameValid(String applicantName) {
        String[] names = applicantName.split("\\s+");
        for (int i = 0; i < names.length; i++) {
            if (!Character.isUpperCase(names[i].charAt(0))) {
                return false;
            }
            for (int j = 1; j < names[i].length(); j++) {
                if (Character.isUpperCase(names[i].charAt(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int sumOfChars(String str) {
        int sumOfCharValues = 0;
        for (int i = 0; i < str.length(); i++) {
            sumOfCharValues += str.charAt(i);
        }
        return sumOfCharValues;
    }
}
