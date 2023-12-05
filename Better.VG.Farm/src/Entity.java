import java.util.Arrays;
import java.util.List;

class Entity {
    protected static int idCounter = 1;
    protected int id;
    protected String name;

    public Entity() {
        this.id = idCounter++;
    }

    public String GetDescription() {
        return "ID: " + id + ", Name: " + name;
    }

    public String getCSV() {
        return id + "," + name;
    }

    public static Entity createEntity(String[] parts) {
        if (parts.length < 2) {
            // Insufficient data to create an entity
            return null;
        }

        int id = Integer.parseInt(parts[0]);
        String name = parts[1];

        if (parts.length == 3) {
            // Assuming it's an Animal
            String[] cropTypes = parts[2].split("@");
            List<String> acceptableCropTypes = Arrays.asList(cropTypes[1].split(","));
            return new Animal(name, cropTypes[0], acceptableCropTypes);
        } else if (parts.length == 4) {
            // Assuming it's a Crop
            String cropType = parts[2];
            int quantity = Integer.parseInt(parts[3]);
            return new Crop(name, cropType, quantity);
        }

        // Invalid data format
        return null;
    }

}