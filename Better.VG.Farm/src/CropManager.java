import java.util.Scanner;

class CropManager {
    private Farm farm;

    public CropManager(Farm farm) {
        this.farm = farm;
    }

    public void CropMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            try {
                System.out.println("1. View Crops");
                System.out.println("2. Add Crop");
                System.out.println("3. Remove Crop");
                System.out.println("4. Back to Main Menu");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        ViewCrops();
                        break;
                    case 2:
                        AddCrop();
                        break;
                    case 3:
                        RemoveCrop();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the input buffer
                choice = -1; // Set choice to an invalid value to continue the loop
            }
        } while (choice != 4);
    }

    public void ViewCrops() {
        for (Crop crop : farm.getCrops()) {
            System.out.println(crop.GetDescription());
        }
    }

    public void AddCrop() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter crop name: ");
            String name = scanner.next();

            System.out.print("Enter crop type: ");
            String cropType = scanner.next();

            System.out.print("Enter initial quantity: ");
            int quantity = scanner.nextInt();

            Crop newCrop = new Crop(name, cropType, quantity);
            farm.getCrops().add(newCrop);

            System.out.println("Crop added successfully.");
        } catch (Exception e) {
            System.out.println("Invalid input. Crop not added.");
            scanner.nextLine(); // Clear the input buffer
        }
    }



    public void RemoveCrop() {
        Scanner scanner = new Scanner(System.in);

        // Display current crops with IDs
        System.out.println("Current Crops:");
        for (Crop crop : farm.getCrops()) {
            System.out.println(crop.GetDescription());
        }

        System.out.print("Enter the ID of the crop to remove: ");
        int cropIdToRemove = scanner.nextInt();

        // Find and remove the crop with the specified ID
        for (Crop crop : farm.getCrops()) {
            if (crop.id == cropIdToRemove) {
                farm.getCrops().remove(crop);
                System.out.println("Crop removed successfully.");
                return;
            }
        }

        System.out.println("Crop with ID " + cropIdToRemove + " not found.");
    }

}

