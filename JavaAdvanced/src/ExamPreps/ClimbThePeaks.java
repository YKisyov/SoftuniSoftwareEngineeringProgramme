package ExamPreps;

import java.util.*;

public class ClimbThePeaks {
    static class PeaksOfPirin {
        private final String peakName;
        private final int peakDifficultyLevel;
        PeaksOfPirin(String peakName, int peakDifficultyLevel){
            this.peakName = peakName;
            this.peakDifficultyLevel = peakDifficultyLevel;
        }

        public String getPeakName() {
            return peakName;
        }

        public int getPeakDifficultyLevel() {
            return peakDifficultyLevel;
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ArrayDeque<Integer> foodSuppliesStk = new ArrayDeque<>();
        ArrayDeque<Integer> staminaKeeperQueue = new ArrayDeque<>();
        ArrayList<String> conqueredMountainPeaks = new ArrayList<>();
        final List<PeaksOfPirin> peaksOfPirinList = new ArrayList<>(5);

        peaksOfPirinList.add(new PeaksOfPirin("Vihren", 80));
        peaksOfPirinList.add(new PeaksOfPirin("Kutelo", 90));
        peaksOfPirinList.add(new PeaksOfPirin("Banski Suhodol", 100));
        peaksOfPirinList.add(new PeaksOfPirin("Polezhan", 60));
        peaksOfPirinList.add(new PeaksOfPirin("Kamenitza", 70));

        int[] footStock = Arrays.stream(scan.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] climberStamina = Arrays.stream(scan.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < 7; i++) {
            foodSuppliesStk.push(footStock[i]);
            staminaKeeperQueue.offer(climberStamina[i]);
        }

        Iterator<PeaksOfPirin> iteratorOfPeakList = peaksOfPirinList.iterator();
        PeaksOfPirin targetedPeak = iteratorOfPeakList.next();
        do {
            int climberDailyStrength = foodSuppliesStk.isEmpty() ? 0 : foodSuppliesStk.pop();
            climberDailyStrength += staminaKeeperQueue.isEmpty() ? 0 : staminaKeeperQueue.poll();
            if (climberDailyStrength >= targetedPeak.getPeakDifficultyLevel()){
                conqueredMountainPeaks.add(targetedPeak.getPeakName());
                if (iteratorOfPeakList.hasNext())
                    targetedPeak = iteratorOfPeakList.next();
                else break;
            }
        } while (!foodSuppliesStk.isEmpty() && !staminaKeeperQueue.isEmpty());

        if (conqueredMountainPeaks.size() == peaksOfPirinList.size()){
            System.out.println("Alex did it! He climbed all top five Pirin peaks in one week -> @FIVEinAWEEK");
        } else {
            System.out.println("Alex failed! He has to organize his journey better next time -> @PIRINWINS");
        }
        if (conqueredMountainPeaks.size() > 0) {
            System.out.println("Conquered peaks:");
            for (String peak: conqueredMountainPeaks) {
                System.out.println(peak);
            }
        }
    }
}