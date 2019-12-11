package JavaClasses;

import java.io.*;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PropertyStore {

    @FXML

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


    public ArrayList<Property> SearchMethod(String propertyType, String location, String minPrice, String maxPrice) {

        MyListOfObjects propertiesSize = new MyListOfObjects();
        ArrayList<Property> searchTBL = new ArrayList<>();
        try {
            XStream xstream = new XStream(new DomDriver());
            ObjectInputStream is = xstream.createObjectInputStream
                    (new FileReader("propertyFile.xml"));
            propertiesSize = (MyListOfObjects) is.readObject();
            is.close();
        }
        catch (Exception e){

        }

        for (int i = 0; i < propertiesSize.size(); i++) {
            Property forProperty = (Property) propertiesSize.get(i);
            searchTBL.add(forProperty);
        }

        if (propertyType == "Any" && location =="Any" && minPrice == "No Min" && maxPrice == "No Max"){
            return searchTBL;
        }
        else {
            ArrayList<Property> searchResults = new ArrayList<>();

            for (int i = 0; i < searchTBL.size(); i++) {

                if (propertyType.equals("Any") && searchTBL.get(i).getLocationGeneral().equals(location) && minPrice.equals("No Min") && maxPrice.equals("No Max")) {
                    searchResults.add(searchTBL.get(i));
                } else if (location.equals("Any") && searchTBL.get(i).getCategory().equals(propertyType) && minPrice.equals("No Min") && maxPrice.equals("No Max")) {
                    searchResults.add(searchTBL.get(i));
                } else if (searchTBL.get(i).getCategory().equals(propertyType) && searchTBL.get(i).getLocationGeneral().equals(location) && minPrice.equals("No Min") && maxPrice.equals("No Max")) {
                    searchResults.add(searchTBL.get(i));
                } else if (searchTBL.get(i).getCategory().equals(propertyType) && searchTBL.get(i).getLocationGeneral().equals(location) && minPrice != "No Min" && maxPrice.equals("No Max")) {
                    double price = searchTBL.get(i).getPrice();
                    double checker = Double.parseDouble(minPrice);
                    if (price > checker) {
                        searchResults.add(searchTBL.get(i));
                    }
                }
                else if(searchTBL.get(i).getCategory().equals(propertyType) && searchTBL.get(i).getLocationGeneral().equals(location) && minPrice == "No Min" && maxPrice != "No Max") {
                    double price = searchTBL.get(i).getPrice();
                    double checker = Double.parseDouble(maxPrice);
                    if (price < checker) {
                        searchResults.add(searchTBL.get(i));
                    }
                }
                else if (propertyType.equals("Any") && location.equals("Any") && minPrice != "No Min" && maxPrice.equals("No Max")) {
                    double price = searchTBL.get(i).getPrice();
                    double checker = Double.parseDouble(minPrice);
                    if (price > checker) {
                        searchResults.add(searchTBL.get(i));
                    }
                }
                else if(propertyType.equals("Any") && location.equals("Any") && minPrice == "No Min" && maxPrice != "No Max") {
                    double price = searchTBL.get(i).getPrice();
                    double checker = Double.parseDouble(maxPrice);
                    if (price < checker) {
                        searchResults.add(searchTBL.get(i));
                    }
                }
                else if(propertyType.equals("Any") && searchTBL.get(i).getLocationGeneral().equals(location) && minPrice != "No Min" && maxPrice == "No Max") {
                    double price = searchTBL.get(i).getPrice();
                    double checker = Double.parseDouble(minPrice);
                    if (price > checker) {
                        searchResults.add(searchTBL.get(i));
                    }
                }
                else if(propertyType.equals("Any") && searchTBL.get(i).getLocationGeneral().equals(location) && minPrice == "No Min" && maxPrice != "No Max") {
                    double price = searchTBL.get(i).getPrice();
                    double checker = Double.parseDouble(maxPrice);
                    if (price < checker) {
                        searchResults.add(searchTBL.get(i));
                    }
                }
                else if(propertyType.equals("Any") && searchTBL.get(i).getLocationGeneral().equals(location) && minPrice != "No Min" && maxPrice != "No Max") {
                    double price = searchTBL.get(i).getPrice();
                    double Minchecker = Double.parseDouble(minPrice);
                    double Maxchecker = Double.parseDouble(maxPrice);
                    if (price > Minchecker && price < Maxchecker) {
                        searchResults.add(searchTBL.get(i));
                    }
                }
                else if(searchTBL.get(i).getCategory().equals(propertyType) && searchTBL.get(i).getLocationGeneral().equals(location) && minPrice != "No Min" && maxPrice != "No Max") {
                    double price = searchTBL.get(i).getPrice();
                    double Minchecker = Double.parseDouble(minPrice);
                    double Maxchecker = Double.parseDouble(maxPrice);
                    if (price > Minchecker && price < Maxchecker) {
                        searchResults.add(searchTBL.get(i));
                    }
                }
                else if(propertyType.equals("Any") && location.equals("Any") && minPrice != "No Min" && maxPrice != "No Max") {
                    double price = searchTBL.get(i).getPrice();
                    double Minchecker = Double.parseDouble(minPrice);
                    double Maxchecker = Double.parseDouble(maxPrice);
                    if (price > Minchecker && price < Maxchecker) {
                        searchResults.add(searchTBL.get(i));
                    }
                }
                else if(searchTBL.get(i).getCategory().equals(propertyType) && location.equals("Any") && minPrice != "No Min" && maxPrice != "No Max") {
                    double price = searchTBL.get(i).getPrice();
                    double Minchecker = Double.parseDouble(minPrice);
                    double Maxchecker = Double.parseDouble(maxPrice);
                    if (price > Minchecker && price < Maxchecker) {
                        searchResults.add(searchTBL.get(i));
                    }
                }
                else if(searchTBL.get(i).getCategory().equals(propertyType) && location.equals("Any") && minPrice != "No Min" && maxPrice == "No Max") {
                    double price = searchTBL.get(i).getPrice();
                    double Minchecker = Double.parseDouble(minPrice);
                    if (price > Minchecker) {
                        searchResults.add(searchTBL.get(i));
                    }
                }
                else if(searchTBL.get(i).getCategory().equals(propertyType) && location.equals("Any") && minPrice == "No Min" && maxPrice != "No Max") {
                    double price = searchTBL.get(i).getPrice();
                    double Maxchecker = Double.parseDouble(maxPrice);
                    if (price < Maxchecker) {
                        searchResults.add(searchTBL.get(i));
                    }
                }
                else if(searchTBL.get(i).getCategory().equals(propertyType) && searchTBL.get(i).getLocationGeneral().equals(location) && minPrice != "No Min" && maxPrice == "No Max") {
                    double price = searchTBL.get(i).getPrice();
                    double Minchecker = Double.parseDouble(minPrice);
                    if (price > Minchecker) {
                        searchResults.add(searchTBL.get(i));
                    }
                }
            }
            return searchResults;
        }
    }
}



