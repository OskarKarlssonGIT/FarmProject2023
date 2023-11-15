import java.util.Scanner;

public class FarmProgram {
    public static void main(String[] args) {
        Farm farm = new Farm();
        showMainMenu(farm);
    }

    private static void showMainMenu(Farm farm) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. View Animals");
            System.out.println("2. View Crops");
            System.out.println("3. Add Animal");
            System.out.println("4. Add Crop");
            System.out.println("5. Remove Animal");
            System.out.println("6. Remove Crop");
            System.out.println("7. Save Data");
            System.out.println("8. Feed Animal");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    farm.viewAnimals();
                    break;
                case 2:
                    farm.viewCrops();
                    break;
                case 3:
                    addAnimalMenu(farm);
                    break;
                case 4:
                    addCropMenu(farm);
                    break;
                case 5:
                    removeAnimalMenu(farm);
                    break;
                case 6:
                    removeCropMenu(farm);
                    break;
                case 7:
                    farm.saveData();
                    System.out.println("Data saved");
                    break;
                case 8:
                    feedAnimalMenu(farm);
                    break;
                case 9:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);

        scanner.close();
    }

    private static void addAnimalMenu(Farm farm) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        int id = scanner.nextInt();

        Animal existingAnimal = farm.getAnimalById(id);
        if (existingAnimal != null) {
            System.out.println("Animal with ID " + id + " already exists. Cannot add a new animal with the same ID.");
        } else {
            System.out.print("Enter animal name: ");
            String name = scanner.next();
            System.out.print("Enter animal age: ");
            int age = scanner.nextInt();

            farm.addAnimal(new Animal(id, name, age));
            System.out.println("Animal added successfully.");
        }
    }

    private static void addCropMenu(Farm farm) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter crop ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter crop type: ");
        String type = scanner.next();
        System.out.print("Enter crop quantity: ");
        int quantity = scanner.nextInt();

        farm.addCrop(new Crop(id, type, quantity));
        System.out.println("Crop added successfully.");
    }

    private static void removeAnimalMenu(Farm farm) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Current Animals:");
        farm.viewAnimals();

        System.out.print("Enter animal ID to remove: ");
        int id = scanner.nextInt();

        if (farm.removeAnimal(id)) {
            System.out.println("Animal removed successfully.");
        } else {
            System.out.println("Animal with ID " + id + " does not exist.");
        }
    }

    private static void removeCropMenu(Farm farm) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Current Crops:");
        farm.viewCrops();

        System.out.print("Enter crop ID to remove: ");
        int id = scanner.nextInt();

        if (farm.removeCrop(id)) {
            System.out.println("Crop removed successfully.");
        } else {
            System.out.println("Crop with ID " + id + " does not exist.");
        }
    }

    private static void feedAnimalMenu(Farm farm) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter crop ID to feed: ");
        int cropId = scanner.nextInt();
        System.out.print("Enter animal ID to feed: ");
        int animalId = scanner.nextInt();

        Crop crop = farm.getCropById(cropId);
        Animal animal = farm.getAnimalById(animalId);

        if (crop != null && animal != null) {
            boolean feedingSuccessful = animal.feed(crop);

            if (feedingSuccessful) {
                System.out.println("Animal fed successfully.");
            } else {
                System.out.println("Animal could not be fed due to insufficient crop quantity.");
            }
        } else {
            System.out.println("Invalid crop or animal ID.");
        }
    }
}
