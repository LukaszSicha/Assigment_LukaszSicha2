package JavaClasses;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class MainController implements Initializable {

    protected PropertyStore property;
    /*protected PropertyAgentController agenty;*/

    String StrCounty, StrPropertyType;
    String DbMin, DbMax;


    @FXML
    private ComboBox<String> comboCounty = new ComboBox<>();
    @FXML
    private ComboBox<String> comboMinPrice = new ComboBox<>();
    @FXML
    private ComboBox<String> comboMaxPrice = new ComboBox<>();
    @FXML
    private ComboBox<String> comboPropertyType = new ComboBox<>();
    @FXML
    private TextField txtpropertyId;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField txtaddress;
    @FXML
    private TextField txtlocationSpecific;
    @FXML
    private TextField txtBER;
    @FXML
    private TextField txtEircode;
    @FXML
    private TextField txtprice;
    @FXML
    private TextArea txtfeedback;
    @FXML
    private TextArea txtfeedback1;



    ObservableList<String> county = FXCollections.observableArrayList("Any", "Waterford", "Wexford", "Limerick", "Cork", "Tipperary", "Galway");
    ObservableList<String> minPrice = FXCollections.observableArrayList("No Min", "10000", "15000", "20000", "25000", "30000", "35000", "40000", "45000", "55000");
    ObservableList<String> maxPrice = FXCollections.observableArrayList("No Max", "10000", "15000", "20000", "25000", "30000", "35000", "40000", "45000", "55000");
    ObservableList<String> propertyType = FXCollections.observableArrayList("Any", "Apartment", "Single House", "Double House", "Detached");


    public void initialize(URL location, ResourceBundle resources) {
        property = new PropertyStore();

        comboCounty.setItems(county);
        comboMinPrice.setItems(minPrice);
        comboMaxPrice.setItems(maxPrice);
        comboPropertyType.setItems(propertyType);

        comboCounty.setValue("Any");
        comboMinPrice.setValue("No Min");
        comboMaxPrice.setValue("No Max");
        comboPropertyType.setValue("Any");
    }


    public void handleButtonLoginAgent(ActionEvent e) throws Exception {
        Main.set_pane(1);
    }

    public void handleButtonLoginAdmin(ActionEvent e) throws Exception {
        Main.set_pane(2);
    }

    public void handleButtonBack(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }

    public void handleButtonCreateProperty(ActionEvent e) throws Exception {

        property.loadProperty();
        int propertyId = Integer.parseInt(txtpropertyId.getText());
        String description = txtdescription.getText();
        String address = txtaddress.getText();
        String StrPropertyType = comboCounty.getValue();
        String StrCounty = comboPropertyType.getValue();
        String locationSpecific = txtlocationSpecific.getText();
        String BER = txtBER.getText();
        String Eircode = txtEircode.getText();
        double price = Double.parseDouble(txtprice.getText());

        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("propertyFile.xml"));
            property.propertiesArray = (ArrayList<Property>) is.readObject();
            is.close();
        } catch (FileNotFoundException t) {
            property.propertiesArray = new ArrayList<Property>();
            txtfeedback.setText("");
        } catch (Exception t) {
            txtfeedback.setText("");
        }


        try {
            Property propertyLocal = new Property(propertyId, description, address, StrPropertyType, StrCounty, locationSpecific, BER, Eircode, price);
            property.propertiesArray.add(propertyLocal);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("propertyFile.xml"));
            out.writeObject(property.propertiesArray);
            out.close();
        } catch (Exception f) {
            txtfeedback.setText("");
        }
        Main.set_pane(7);

    }

    public void handleSaveBtn(ActionEvent e) throws Exception{
        property.saveProperty();
    }

    public void handleLoadBtn(ActionEvent e) throws Exception{
        property.loadProperty();
        txtfeedback.setText(property.listProperties());

    }

    public void handleButtonCreatePropertySite(ActionEvent e) throws Exception{
        Main.set_pane(8);
    }

    public void handleButtonViewPropertiesAgent(ActionEvent e) throws Exception{
        Main.set_pane(9);
    }

    public void handleReadBtn(ActionEvent e) throws Exception{
        property.loadProperty();
        txtfeedback.setText("Reading properties");
        String id = txtpropertyId.getText();
        Property properties = property.getProdDetails(id);
        if(properties==null)
            txtfeedback.setText("Invalid Read");
        else{
            txtdescription.setText(properties.getDescription());
            txtaddress.setText(""+properties.getAddress());
            txtlocationSpecific.setText(""+properties.getLocationSpecific());
            txtBER.setText(""+properties.getBER());
            txtEircode.setText(""+properties.getEircode());
            txtprice.setText(""+properties.getPrice());
        }
    }



    public void handleButtonBackToAgentSite(ActionEvent e) throws Exception{
        Main.set_pane(7);
    }

    /*public void handleButtonDeleteProperty(ActionEvent e) throws Exception{

        try {

            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("propertyFile.xml"));
            out.writeObject(property.propertiesArray);
            out.close();
        } catch (Exception f) {
            txtfeedback.setText("");
        }
        Main.set_pane(7);
    }*/

    public void handleSearchButton(ActionEvent e) throws Exception {
        if (property.propertiesArray.size() == 0) {
            try {
                property.loadProperty();
            } catch (Exception z) {
                txtfeedback.setText("No Properties to Load");
            }
        }

        if (comboMinPrice.getValue() == "No Min" && comboMaxPrice.getValue() == "No Max" && comboPropertyType.getValue() != "Any" && comboCounty.getValue() != "Any") {
            StrPropertyType = comboPropertyType.getValue();
            StrCounty = comboCounty.getValue();
            txtfeedback.setText(property.listAllPropertiesSearchCountyPropertyType(StrCounty, StrPropertyType));
        }

        if (comboMaxPrice.getValue() == "No Max" && comboPropertyType.getValue() == "Any" && comboMinPrice.getValue() != "No Min" && comboCounty.getValue() != "Any") {
            DbMin = comboMinPrice.getValue();
            double minimumPrice = Double.parseDouble(DbMin);
            StrCounty = comboCounty.getValue();
            txtfeedback.setText(property.listAllPropertiesSearchCountyMin(StrCounty, minimumPrice));
        }

        if (comboMaxPrice.getValue() == "No Max" && comboPropertyType.getValue() == "Any" && comboMinPrice.getValue() == "No Min" && comboCounty.getValue() != "Any") {
            StrCounty = comboCounty.getValue();
            txtfeedback.setText(property.listAllPropertiesSearchCounty(StrCounty));
        }

        if (comboMaxPrice.getValue() != "No Max" && comboPropertyType.getValue() == "Any" && comboMinPrice.getValue() == "No Min" && comboCounty.getValue() != "Any") {
            DbMax = comboMaxPrice.getValue();
            double maximumPrice = Double.parseDouble(DbMax);
            StrCounty = comboCounty.getValue();
            txtfeedback.setText(property.listAllPropertiesSearchCountyMax(StrCounty, maximumPrice));
        }

        if (comboMaxPrice.getValue() != "No Max" && comboPropertyType.getValue() == "Any" && comboMinPrice.getValue() != "No Min" && comboCounty.getValue() != "Any") {
            DbMax = comboMaxPrice.getValue();
            double maximumPrice = Double.parseDouble(DbMax);
            DbMin = comboMinPrice.getValue();
            double minimumPrice = Double.parseDouble(DbMin);
            StrCounty = comboCounty.getValue();
            if (minimumPrice > maximumPrice) {
                txtfeedback1.setText("Please Check you Minimum\nand Maximum values are correct");
            } else {
                txtfeedback.setText(property.listAllPropertiesSearchCountyMinMax(StrCounty, minimumPrice, maximumPrice));
            }
        }

        if (comboMaxPrice.getValue() != "No Max" && comboPropertyType.getValue() != "Any" && comboMinPrice.getValue() != "No Min" && comboCounty.getValue() != "Any") {
            DbMax = comboMaxPrice.getValue();
            double maximumPrice = Double.parseDouble(DbMax);
            DbMin = comboMinPrice.getValue();
            double minimumPrice = Double.parseDouble(DbMin);
            if (minimumPrice > maximumPrice) {
                txtfeedback.setText("Please Check you Minimum\nand Maximum values are correct");
            } else {
                StrPropertyType = comboPropertyType.getValue();
                StrCounty = comboCounty.getValue();
                txtfeedback.setText(property.listAllPropertiesSearchCountyMinMaxType(StrCounty, minimumPrice, maximumPrice, StrPropertyType));
            }
        }

        if (comboMaxPrice.getValue() != "No Max" && comboPropertyType.getValue() == "Any" && comboMinPrice.getValue() != "No Min" && comboCounty.getValue() == "Any") {
            DbMax = comboMaxPrice.getValue();
            double maximumPrice = Double.parseDouble(DbMax);
            DbMin = comboMinPrice.getValue();
            double minimumPrice = Double.parseDouble(DbMin);
            if (minimumPrice > maximumPrice) {
                txtfeedback1.setText("Please Check you Minimum\nand Maximum values are correct");
            } else {
                txtfeedback.setText(property.listAllPropertiesSearchMinMax(minimumPrice, maximumPrice));
            }
        }

        if (comboMaxPrice.getValue() != "No Max" && comboPropertyType.getValue() != "Any" && comboMinPrice.getValue() != "No Min" && comboCounty.getValue() == "Any") {
            DbMax = comboMaxPrice.getValue();
            double maximumPrice = Double.parseDouble(DbMax);
            DbMin = comboMinPrice.getValue();
            double minimumPrice = Double.parseDouble(DbMin);
            if (minimumPrice > maximumPrice) {
                txtfeedback1.setText("Please Check you Minimum\nand Maximum values are correct");
            } else {
                StrPropertyType = comboPropertyType.getValue();
                txtfeedback.setText(property.listAllPropertiesSearchMinMaxType(minimumPrice, maximumPrice,StrPropertyType));
            }
        }

        if (comboMaxPrice.getValue() == "No Max" && comboPropertyType.getValue() != "Any" && comboMinPrice.getValue() != "No Min" && comboCounty.getValue() == "Any") {
            DbMin = comboMinPrice.getValue();
            double minimumPrice = Double.parseDouble(DbMin);
            StrPropertyType = comboPropertyType.getValue();
            txtfeedback.setText(property.listAllPropertiesSearchMinType(minimumPrice, StrPropertyType));
        }

        if (comboMaxPrice.getValue() != "No Max" && comboPropertyType.getValue() != "Any" && comboMinPrice.getValue() == "No Min" && comboCounty.getValue() == "Any") {
            DbMax = comboMaxPrice.getValue();
            double maximumPrice = Double.parseDouble(DbMax);
            StrPropertyType = comboPropertyType.getValue();
            txtfeedback.setText(property.listAllPropertiesSearchMaxType(maximumPrice, StrPropertyType));
        }

        if (comboMaxPrice.getValue() != "No Max" && comboPropertyType.getValue() != "Any" && comboMinPrice.getValue() == "No Min" && comboCounty.getValue() != "Any") {
            DbMax = comboMaxPrice.getValue();
            double maximumPrice = Double.parseDouble(DbMax);
            StrPropertyType = comboPropertyType.getValue();
            StrCounty = comboCounty.getValue();
            txtfeedback.setText(property.listAllPropertiesSearchCountyMaxType(StrCounty,maximumPrice, StrPropertyType));
        }

        if (comboMaxPrice.getValue() != "No Max" && comboPropertyType.getValue() == "Any" && comboMinPrice.getValue() == "No Min" && comboCounty.getValue() == "Any") {
            DbMax = comboMaxPrice.getValue();
            double maximumPrice = Double.parseDouble(DbMax);
            txtfeedback.setText(property.listAllPropertiesSearchMax(maximumPrice));
        }

        if (comboMaxPrice.getValue() == "No Max" && comboPropertyType.getValue() == "Any" && comboMinPrice.getValue() != "No Min" && comboCounty.getValue() == "Any") {
            StrPropertyType = comboPropertyType.getValue();
            txtfeedback.setText(property.listAllPropertiesSearchType(StrPropertyType));
        }

        if (comboMaxPrice.getValue() == "No Max" && comboPropertyType.getValue() != "Any" && comboMinPrice.getValue() != "No Min" && comboCounty.getValue() != "Any") {
            DbMin = comboMinPrice.getValue();
            double minimumPrice = Double.parseDouble(DbMin);
            StrPropertyType = comboPropertyType.getValue();
            StrCounty = comboCounty.getValue();
            txtfeedback.setText(property.listAllPropertiesSearchCountyMinType(StrCounty,minimumPrice, StrPropertyType));
        }

        if(comboMaxPrice.getValue() == "No Max" && comboPropertyType.getValue() == "Any" && comboMinPrice.getValue() == "No Min" && comboCounty.getValue() == "Any"){
            txtfeedback.setText(property.listAllPropertiesSmall());
        }

        if (comboMaxPrice.getValue() == "No Max" && comboPropertyType.getValue() == "Any" && comboMinPrice.getValue() != "No Min" && comboCounty.getValue() == "Any") {
            DbMin = comboMinPrice.getValue();
            double minimumPrice = Double.parseDouble(DbMin);
            txtfeedback.setText(property.listAllPropertiesSearchMin(minimumPrice));
        }
    }



}
