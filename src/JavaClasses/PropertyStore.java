package JavaClasses;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.fxml.FXML;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class PropertyStore {

    @FXML
    ArrayList<Property> propertiesArray = new ArrayList<Property>();
    ArrayList<Admin> admins;
    ArrayList<Agent> agents;

    private MyListOfObjects propertyLinkedList = new MyListOfObjects();


    @FXML
    public void saveProperty() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream
                (new FileWriter("propertyFile.xml"));
        out.writeObject(propertyLinkedList);
        out.close();
    }

    @FXML
    public void loadProperty() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("propertyFile.xml"));
        propertyLinkedList = (MyListOfObjects) is.readObject();
        is.close();
    }


    @FXML
    public boolean addProperty(int propertyId, String description, String address, String category, String locationGeneral, String locationSpecific, String BER, String Eircode, double price) {
        if(propertyLinkedList.isEmpty()) {
            try {
                loadProperty();
                Property propertyVar = new Property(propertyId, description, address, category, locationGeneral, locationSpecific, BER, Eircode, price);
                propertyLinkedList.add(propertyVar);
                saveProperty();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        else {
            try {
                Property propertyVar = new Property(propertyId, description, address, category, locationGeneral, locationSpecific, BER, Eircode, price);
                propertyLinkedList.add(propertyVar);
                saveProperty();
                return true;
            } catch (Exception t) {
                return false;
            }
        }
    }

    @FXML
    public String listProperties() {
        int i = 0;
        String displayProperty = "All properties";
        for (Property item : propertiesArray) {
            displayProperty += "\n" + (i++) + " : " + item;
        }
        return displayProperty;
    }


    public Property getProdDetails(String id) {

        try {
            int id1 = (Integer.parseInt(id));  //Test only
            for (Property item : propertiesArray) {
                if (item.getPropertyId() == id1) {
                    return item;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }


    public String listCategoryProporties(String prop, String subProp)
    {
        String displayProporties;
        displayProporties=""+prop+"----"+subProp;
        for (Property item:propertiesArray){
            if (item.getCategory().equals(prop) && item.getCategory().equals(subProp)){
                displayProporties+= "\n"+ item.getPropertyId() + ": " + item.getAddress();
            }
            else if (prop.equals("All")){
                displayProporties+= "\n"+ item.getPropertyId() + ": " + item.getAddress();
            }
        }
        return displayProporties;

    }


    public void deleteAgents(String username){
        try{
            for(Agent item:agents){
                if(item.getUsername().equals(username)){
                    agents.remove(item);
                }
            }
        } catch (Exception e){

        }
    }

    public ArrayList<Property> SearchMethod(String propertyType, String location, String minPrice, String maxPrice) {

        MyListOfObjects proportyLoading = new MyListOfObjects();
        ArrayList<Property> tableProperty = new ArrayList<>();
        try {
            XStream xstream = new XStream(new DomDriver());
            ObjectInputStream is = xstream.createObjectInputStream
                    (new FileReader("propertyList.xml"));
            proportyLoading = (MyListOfObjects) is.readObject();
            is.close();
        }
        catch (Exception e){

        }

        for (int i = 0; i < proportyLoading.size(); i++) {
            Property propertyAdd = (Property) proportyLoading.get(i);
            tableProperty.add(propertyAdd);
        }

        if (propertyType == "Any" && location =="Any" && minPrice == "No Min" && maxPrice == "No Max"){
            return tableProperty;
        }
        else {
            ArrayList<Property> searchResults = new ArrayList<>();

            for (int i = 0; i < tableProperty.size(); i++) {

                if (propertyType.equals("Any") && tableProperty.get(i).getLocationGeneral().equals(location) && minPrice.equals("No Min") && maxPrice.equals("No Max")) {
                    searchResults.add(tableProperty.get(i));
                } else if (location.equals("Any") && tableProperty.get(i).getCategory().equals(propertyType) && minPrice.equals("No Min") && maxPrice.equals("No Max")) {
                    searchResults.add(tableProperty.get(i));
                } else if (tableProperty.get(i).getCategory().equals(propertyType) && tableProperty.get(i).getLocationGeneral().equals(location) && minPrice.equals("No Min") && maxPrice.equals("No Max")) {
                    searchResults.add(tableProperty.get(i));
                } else if (tableProperty.get(i).getCategory().equals(propertyType) && tableProperty.get(i).getLocationGeneral().equals(location) && minPrice != "No Min" && maxPrice.equals("No Max")) {
                    int price = Integer.parseInt(Double.toString(tableProperty.get(i).getPrice()));
                    if (price > Integer.parseInt(minPrice)) {
                        searchResults.add(tableProperty.get(i));
                    }
                }

            }
            return searchResults;
        }
    }




}



