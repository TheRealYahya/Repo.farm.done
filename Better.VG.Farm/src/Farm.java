
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Farm {
    private AnimalManager animalManager;
    private CropManager cropManager;
    private List<Animal> animals = new ArrayList<>();
    private List<Crop> crops = new ArrayList<>();

    public Farm() {
        this.animalManager = new AnimalManager(this, crops);
        this.cropManager = new CropManager(this);
        // Load animals and crops from files or create default ones
        loadFromFile("animals.txt", animals);
        loadFromFile("crops.txt", crops);

        if (animals.isEmpty() && crops.isEmpty()) {
            createDefaultEntities();
        }
    }

    public void save() {
        saveToFile("animals.txt", animals);
        saveToFile("crops.txt", crops);
        System.out.println("Data saved successfully.");
    }

    private void createDefaultEntities() {
        // Create and add default animals
        Animal defaultAnimal1 = new Animal("Cow", "Mammal", List.of("Grass", "Hay"));
        Animal defaultAnimal2 = new Animal("Chicken", "Bird", List.of("Seeds", "Insects"));
        animals.add(defaultAnimal1);
        animals.add(defaultAnimal2);

        // Create and add default crops
        Crop defaultCrop1 = new Crop("Wheat Field", "Wheat", 100);
        Crop defaultCrop2 = new Crop("Vegetable Garden", "Carrot", 50);
        crops.add(defaultCrop1);
        crops.add(defaultCrop2);
    }

    public void MainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            try {
                System.out.println("1. Animal Menu");
                System.out.println("2. Crop Menu");
                System.out.println("3. Save");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        animalManager.AnimalMenu();
                        break;
                    case 2:
                        cropManager.CropMenu();
                        break;
                    case 3:
                        save();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the input buffer
                choice = -1; // Set choice to an invalid value to continue the loop
            }
        } while (choice != 4);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Crop> getCrops() {
        return crops;
    }

    private static <T extends Entity> void loadFromFile(String fileName, List<T> list) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    T entity = (T) Entity.createEntity(parts);
                    if (entity != null) {
                        list.add(entity);
                    }
                } catch (Exception e) {
                    // Handle mismatched input or other exceptions
                    System.err.println("Error reading line: " + line + ". Skipping...");
                }
            }
        } catch (IOException e) {
            // Handle file not found or other IO exceptions
        }
    }


    private static void saveToFile(String fileName, List<? extends Entity> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Entity entity : list) {
                bw.write(entity.getCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            // Handle IO exceptions
        }
    }
}

