class Crop extends Entity {
    protected String cropType;
    private int quantity;

    public Crop(String name, String cropType, int quantity) {
        super();
        this.name = name;
        this.cropType = cropType;
        this.quantity = quantity;
    }

    @Override
    public String GetDescription() {
        return super.GetDescription() + ", Crop Type: " + cropType + ", Quantity: " + quantity;
    }

    public void AddCrop(int quantity) {
        this.quantity += quantity;
    }

    public boolean TakeCrop(int quantity) {
        if (this.quantity >= quantity) {
            this.quantity -= quantity;
            return true;
        }
        return false;
    }

    @Override
    public String getCSV() {
        return super.getCSV() + "," + cropType + "," + quantity;
    }
}
