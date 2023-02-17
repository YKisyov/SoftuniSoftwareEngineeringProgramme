//package ExamPreps.rabbits;
package rabbits;

public class Rabbit {

    String name;
    String species;
    boolean available; //true by default todo add this hex to the constructor

    public Rabbit(String name, String species) {
        this.name = name;
        this.species = species;
        available = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format("Rabbit (%s): %s", getSpecies(), getName());
    }
}
