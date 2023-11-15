import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Farm {
    private List<Animal> animals;
    private List<Crop> crops;

    public Farm() {
        this.animals = new ArrayList<>();
        this.crops = new ArrayList<>();
        loadData();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void addCrop(Crop crop) {
        Crop existingCrop = getCropById(crop.getId());
        if (existingCrop != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Crop with ID " + crop.getId() + " already exists. Enter quantity to add: ");
            int quantityToAdd = scanner.nextInt();
            existingCrop.setQuantity(existingCrop.getQuantity() + quantityToAdd);
        } else {
            crops.add(crop);
        }
    }

    public Crop getCropById(int cropId) {
        for (Crop crop : crops) {
            if (crop.getId() == cropId) {
                return crop;
            }
        }
        return null;
    }

    public boolean removeAnimal(int animalId) {

        Animal animalToRemove = getAnimalById(animalId);
        if (animalToRemove != null) {
            animals.remove(animalToRemove);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeCrop(int cropId) {
        Crop cropToRemove = getCropById(cropId);
        if (cropToRemove != null) {
            crops.remove(cropToRemove);
            return true;
        } else {
            return false;
        }
    }

    public void viewAnimals() {
        animals.forEach(animal -> System.out.println(animal.toString()));
    }

    public void viewCrops() {
        crops.forEach(crop -> System.out.println(crop.toString()));
    }

    public void saveData() {
        try (PrintWriter animalWriter = new PrintWriter("animals.csv");
             PrintWriter cropWriter = new PrintWriter("crops.csv")) {
            for (Animal animal : animals) {
                animalWriter.println(animal.getId() + "," + animal.getName() + "," + animal.getAge());
            }
            for (Crop crop : crops) {
                cropWriter.println(crop.getId() + "," + crop.getType() + "," + crop.getQuantity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        File animalFile = new File("animals.csv");
        File cropFile = new File("crops.csv");

        if (animalFile.exists()) {
            loadAnimals(animalFile);
        } else {
            addAnimal(new Animal(1, "Cow", 3));
            addAnimal(new Animal(2, "Chicken", 1));
        }

        if (cropFile.exists()) {
            loadCrops(cropFile);
        } else {
            addCrop(new Crop(1, "Wheat", 100));
            addCrop(new Crop(2, "Corn", 50));
        }
    }

    private void loadAnimals(File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                addAnimal(new Animal(id, name, age));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCrops(File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int id = Integer.parseInt(data[0]);
                String type = data[1];
                int quantity = Integer.parseInt(data[2]);
                addCrop(new Crop(id, type, quantity));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Animal getAnimalById(int animalId) {
        for (Animal animal : animals) {
            if (animal.getId() == animalId) {
                return animal;
            }
        }
        return null;
    }
}


