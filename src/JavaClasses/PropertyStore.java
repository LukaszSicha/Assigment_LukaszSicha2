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



