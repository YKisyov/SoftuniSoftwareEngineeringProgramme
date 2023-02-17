//package ExamPreps.rabbits;
package rabbits;

import java.util.ArrayList;
import java.util.List;

public class Cage {
    List<Rabbit> data;
    String name;
    int capacity;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        data = new ArrayList<>();
    }

    int getCapacity() {
        return capacity;
    }

    String getName() {
        return name;
    }

    boolean removeRabbit(String name) {
        return data.removeIf(data -> data.getName().equals(name));
    }

    void removeSpecies(String species) {
        while (data.removeIf(data -> data.getSpecies().equals(species))) {
        }
        ;
    }

    Rabbit sellRabbit(String name) {
        for (Rabbit rabbit : data) {
            if (rabbit.getName().equals(name)) {
                rabbit.setAvailable(false);
                return rabbit;
            }
        }
        return null;
    }

    List<Rabbit> sellRabbitBySpecies(String species) {
        List<Rabbit> soldRabbitsList = new ArrayList<>();

        for (Rabbit rabbit : data) {
            if (rabbit.getSpecies().equals(species)) {
                rabbit.setAvailable(false);
                soldRabbitsList.add(rabbit);
            }
        }
        return soldRabbitsList;
    }

    int count() {
        return data.size();
    }

    String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Rabbits available at %s:", getName()));
        for (Rabbit rabbit : data) {
            if (rabbit.isAvailable()) {
                sb.append(System.lineSeparator());
                sb.append(rabbit.toString());
            }
        }
        return sb.toString();
    }

    void add(Rabbit rabbit){
        if (count() +1 < capacity){
            data.add(rabbit);
        }
    }

}
