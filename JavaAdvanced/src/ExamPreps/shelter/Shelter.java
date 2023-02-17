//package ExamPreps.shelter;
package shelter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Shelter {
    private List<Animal> data;
    private int capacity;

    public Shelter(int capacity) {
        this.capacity = capacity;
        data = new ArrayList<>();
    }

/*
    •	Method add(Animal animal) – adds an entity to the data if there is an empty cell for the animal.
•	Method getAnimal(String name, String caretaker) – returns the animal with the given name and caretaker or null if no such animal exists.
•	Method getOldestAnimal() – returns the oldest Animal.
•	Getter getCount – returns the number of animals.
•	getStatistics() – returns a String in the following format:
o	"The shelter has the following animals:
{name} {caretaker}
{name} {caretaker}
*/

    public boolean remove(String name) {
        return data.removeIf(a -> a.getName().equals(name));
    }

    public Animal getOldestAnimal() {
        int maxAge = data.get(0).getAge();
        Animal oldestPet = data.get(0);
        for (Animal pet : data) {
            if (pet.getAge() > maxAge) {
                maxAge = pet.getAge();
                oldestPet = pet;
            }
        }
        return oldestPet;
    }

    public int getCount() {
        return data.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("The shelter has the following animals:");
        for (Animal pet : data) {
            sb.append(System.lineSeparator());
            sb.append(pet.getName());
            sb.append(" ");
            sb.append(pet.getCaretaker());
        }
        return sb.toString();
    }

    public Animal getAnimal(String name, String caretaker) {
        for (Animal pet : data) {
            if (pet.getName().equals(name) && pet.getCaretaker().equals(caretaker)) {
                return pet;
            }
        }
        return null;
    }

    public void add(Animal pet) {
        if (capacity > data.size()) {
            data.add(pet);
        }

    }
}
