package JavaClasses;

public class Property {


    private int propertyId;
    private String description;
    private String address;
    private String category;
    private String locationGeneral;
    private String locationSpecific;
    private String BER;
    private String Eircode;
    private double price;

    public Property(int propertyId, String description, String address, String category, String locationGeneral, String locationSpecific, String BER, String eircode, double price) {
        this.propertyId = propertyId;
        this.description = description;
        this.address = address;
        this.category = category;
        this.locationGeneral = locationGeneral;
        this.locationSpecific = locationSpecific;
        this.BER = BER;
        this.Eircode = eircode;
        this.price = price;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocationGeneral() {
        return locationGeneral;
    }

    public void setLocationGeneral(String locationGeneral) {
        this.locationGeneral = locationGeneral;
    }

    public String getLocationSpecific() {
        return locationSpecific;
    }

    public void setLocationSpecific(String locationSpecific) {
        this.locationSpecific = locationSpecific;
    }

    public String getBER() {
        return BER;
    }

    public void setBER(String BER) {
        this.BER = BER;
    }

    public String getEircode() {
        return Eircode;
    }

    public void setEircode(String eircode) {
        Eircode = eircode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  " \n propertyId: " + propertyId +
                " \n Description: " + description +
                " \n Address: " + address +
                " \n Category: " + category +
                " \n Location: " + locationGeneral +
                " \n locationSpecific: " + locationSpecific +
                " \n BER: " + BER +
                " \n Eircode: " + Eircode +
                " \n price: " + price;
    }
}
