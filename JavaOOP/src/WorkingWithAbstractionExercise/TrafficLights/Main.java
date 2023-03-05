package WorkingWithAbstractionExercise.TrafficLights;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] trafficLightsCurrentStateArr = scan.nextLine().split("\\s+");
        int timesToChangeEachTragicLight = Integer.parseInt(scan.nextLine());

        String[] trafficLightsNextStateArr = new String[trafficLightsCurrentStateArr.length];

        for (int i = 0; i < timesToChangeEachTragicLight; i++) {
            for (int j = 0; j < trafficLightsCurrentStateArr.length; j++) {
                String currentLight = trafficLightsCurrentStateArr[j];
                LightsOrder lightOrderEnum = LightsOrder.valueOf(currentLight);
                trafficLightsNextStateArr[j] = lightOrderEnum.getNextColorState();
            }
            printTrafficLightsStates(trafficLightsNextStateArr);
            trafficLightsCurrentStateArr = trafficLightsNextStateArr;
        }
    }

    private static void printTrafficLightsStates(String[] trafficLightsNextStateArr) {
        StringBuilder sb = new StringBuilder();
        for (String light : trafficLightsNextStateArr) {
            sb.append(light);
            sb.append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
