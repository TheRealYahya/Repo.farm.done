import java.util.ArrayList;
import java.util.List;

class Animal extends Entity {
    private String species;
    private List<String> acceptableCropTypes = new ArrayList<>();

    public Animal(String name, String species, List<String> acceptableCropTypes) {
        super();
        this.name = name;
        this.species = species;
        this.acceptableCropTypes = acceptableCropTypes;
    }

    @Override
    public String GetDescription() {
        return super.GetDescription() + ", Species: " + species;
    }

    public void Feed(Crop crop) {
        // Check if the crop is acceptable for the animal
        if (acceptableCropTypes.contains(crop.cropType)) {
            // Feed the animal and reduce the crop quantity
            System.out.println(name + " is being fed with " + crop.cropType + ".");
            // You can implement additional logic here based on your requirements
        } else {
            // Crop type is not acceptable for the animal
            System.out.println(name + " cannot be fed with " + crop.cropType + ".");
        }
    }


    @Override
    public String getCSV() {
        return super.getCSV() + "@" + String.join(",", acceptableCropTypes);
    }
}
