package JavaClasses;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class PropertyStore {

    @FXML
    ArrayList<Property> propertiesArray = new ArrayList<Property>();
    ArrayList<Admin> admins;
    ArrayList<Agent> agents;


    @FXML
    public void saveProperty() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream
                (new FileWriter("propertyFile.xml"));
        out.writeObject(propertiesArray);
        out.close();
    }

    @FXML
    public void loadProperty() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("propertyFile.xml"));
        propertiesArray = (ArrayList<Property>) is.readObject();
        is.close();
    }


    @FXML
    public void addProperty(int propertyId, String description, String address, String category, String locationGeneral, String locationSpecific, String BER, String Eircode, double price) {
        Property property = new Property(propertyId, description, address, category, locationGeneral, locationSpecific, BER, Eircode, price);
        propertiesArray.add(property);
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

    @FXML
    public String listAllPropertiesSmall() {
        String displayPropertys ="";
        for(int i = 0; i < propertiesArray.size(); i++){
            displayPropertys += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                    "" + propertiesArray.get(i).getPrice() + "\n";
        }
        return displayPropertys;
    }

    @FXML
    public String listAllPropertiesSearchCountyPropertyType(String county, String propertyType) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getLocationGeneral().equals(county) && propertiesArray.get(i).getCategory().equals(propertyType)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchCountyMin(String county, double min) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getLocationGeneral().equals(county) && propertiesArray.get(i).getPrice() > min) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchCounty(String county) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getLocationGeneral().equals(county)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchCountyMax(String county,double max) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getLocationGeneral().equals(county) && propertiesArray.get(i).getPrice() < max) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }
    @FXML
    public String listAllPropertiesSearchCountyMinMax(String county,double min, double max) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getLocationGeneral().equals(county) && propertiesArray.get(i).getPrice() < max && propertiesArray.get(i).getPrice() > min) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchCountyMinMaxType(String county,double min, double max,String type) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getLocationGeneral().equals(county) && propertiesArray.get(i).getPrice() < max && propertiesArray.get(i).getPrice() > min && propertiesArray.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchMinMax(double min, double max) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getPrice() < max && propertiesArray.get(i).getPrice() > min) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchMinMaxType(double min, double max,String type) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getPrice() < max && propertiesArray.get(i).getPrice() > min && propertiesArray.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }
    @FXML
    public String listAllPropertiesSearchMinType(double min, String type) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getPrice() > min && propertiesArray.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchMaxType(double max, String type) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getPrice() < max && propertiesArray.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchCountyMaxType(String county, double max, String type) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getLocationGeneral().equals(county) && propertiesArray.get(i).getPrice() < max && propertiesArray.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchMax(double max) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getPrice() < max) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchMin(double min) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getPrice() > min) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchType(String type) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchCountyMinType(String county, double min, String type) {
        String displayProperties ="";
        for(int i = 0; propertiesArray.size() > i; i++){
            if(propertiesArray.get(i).getLocationGeneral().equals(county) && propertiesArray.get(i).getPrice() > min && propertiesArray.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertiesArray.get(i).getAddress() + "\n" + propertiesArray.get(i).getLocationGeneral() + "\n" +
                        "" + propertiesArray.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
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

    @FXML
    public void saveAgent() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream
                (new FileWriter("propertyFile.xml"));
        out.writeObject(agents);
        out.close();
    }


}



