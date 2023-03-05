package WorkingWithAbstractionExercise.TrafficLights;

public enum LightsOrder {
    RED("RED"),
    GREEN("GREEN"),
    YELLOW("YELLOW");

    String currenColor;
    String nextColorState;
    LightsOrder(String currentColorState){
        currenColor = currentColorState;
        switch (currentColorState){
            case "RED":
                nextColorState = "GREEN";
                break;
            case "GREEN":
                nextColorState = "YELLOW";
                break;
            case "YELLOW":
                nextColorState = "RED";
                break;
        }
    }
    public String getNextColorState(){
        return nextColorState;
    }

}
