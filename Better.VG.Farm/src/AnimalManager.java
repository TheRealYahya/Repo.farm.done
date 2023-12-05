import java.util.*;

class AnimalManager {
    private Farm farm;
    private List<Crop> crops;

    public AnimalManager(Farm farm, List<Crop> crops) {
        this.farm = farm;
        this.crops = crops;
    }

    public void AnimalMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            try {
                System.out.println("1. View Animals");
                System.out.println("2. Add Animal");
                System.out.println("3. Remove Animal");
                System.out.println("4. Feed Animals");
                System.out.println("5. Back to Main Menu");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        ViewAnimals();
                        break;
                    case 2:
                        AddAnimal();
                        break;
                    case 3:
                        RemoveAnimal();
                        break;
                    case 4:
                        FeedAnimals();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the input buffer
                choice = -1; // Set choice to an invalid value to continue the loop
            }
        } while (choice != 5);
    }

    public void ViewAnimals() {
        for (Animal animal : farm.getAnimals()) {
            System.out.println(animal.GetDescription());
        }
    }

    public void AddAnimal() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter animal name: ");
            String name = scanner.next();

            System.out.print("Enter animal species: ");
            String species = scanner.next();

            System.out.print("Enter acceptable crop types (comma-separated): ");
            String cropTypesInput = scanner.next();
            List<String> acceptableCropTypes = Arrays.asList(cropTypesInput.split(","));

            Animal newAnimal = new Animal(name, species, acceptableCropTypes);
            farm.getAnimals().add(newAnimal);

            System.out.println("Animal added successfully.");
        } catch (Exception e) {
            System.out.println("Invalid input. Animal not added.");
            scanner.nextLine(); // Clear the input buffer
        }
    }


    public void RemoveAnimal() {
        Scanner scanner = new Scanner(System.in);

        // Display current animals with IDs
        System.out.println("Current Animals:");
        for (Animal animal : farm.getAnimals()) {
            System.out.println(animal.GetDescription());
        }

        System.out.print("Enter the ID of the animal to remove: ");
        int animalIdToRemove = scanner.nextInt();

        // Find and remove the animal with the specified ID
        for (Animal animal : farm.getAnimals()) {
            if (animal.id == animalIdToRemove) {
                farm.getAnimals().remove(animal);
                System.out.println("Animal removed successfully.");
                return;
            }
        }

        System.out.println("Animal with ID " + animalIdToRemove + " not found.");
    }


    public void FeedAnimals() {
        Scanner scanner = new Scanner(System.in);

        // Display current crops with IDs
        System.out.println("Available Crops for Feeding:");
        for (Crop crop : farm.getCrops()) {
            System.out.println(crop.GetDescription());
        }

        System.out.print("Enter the ID of the crop to feed: ");
        int cropIdToFeed = scanner.nextInt();

        // Find the crop with the specified ID
        Crop selectedCrop = null;
        for (Crop crop : farm.getCrops()) {
            if (crop.id == cropIdToFeed) {
                selectedCrop = crop;
                break;
            }
        }

        if (selectedCrop == null) {
            System.out.println("Crop with ID " + cropIdToFeed + " not found.");
            return;
        }

        // Display current animals with IDs
        System.out.println("Available Animals:");
        for (Animal animal : farm.getAnimals()) {
            System.out.println(animal.GetDescription());
        }

        System.out.print("Enter the ID of the animal to feed: ");
        int animalIdToFeed = scanner.nextInt();

        // Find the animal with the specified ID
        Animal selectedAnimal = null;
        for (Animal animal : farm.getAnimals()) {
            if (animal.id == animalIdToFeed) {
                selectedAnimal = animal;
                break;
            }
        }

        if (selectedAnimal == null) {
            System.out.println("Animal with ID " + animalIdToFeed + " not found.");
            return;
        }

        // Feed the animal with the selected crop
        if (selectedCrop.TakeCrop(1)) {
            selectedAnimal.Feed(selectedCrop);
            System.out.println(selectedAnimal.name + " fed successfully with " + selectedCrop.name + ".");
        } else {
            System.out.println("Could not feed " + selectedAnimal.name + " because there were no " + selectedCrop.name + " available.");
        }
    }

}

