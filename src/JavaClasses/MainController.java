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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

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
    @FXML private TableColumn<Property, Integer> idColumn;  // notice the use of the wrapper class
    @FXML private TableColumn<Property, String> nameColumn;
    @FXML private TableColumn<Property, String> descriptionColumn;
    @FXML private TableColumn<Property, Double> costColumn;



    ObservableList<String> county = FXCollections.observableArrayList("Any", "Waterford", "Wexford", "Limerick", "Cork", "Tipperary", "Galway");
    ObservableList<String> minPrice = FXCollections.observableArrayList("No Min", "10000", "15000", "20000", "25000", "30000", "35000", "40000", "45000", "55000");
    ObservableList<String> maxPrice = FXCollections.observableArrayList("No Max", "10000", "15000", "20000", "25000", "30000", "35000", "40000", "45000", "55000");
    ObservableList<String> propertyType = FXCollections.observableArrayList("Any", "Apartment", "Single House", "Double House", "Detached");


    public void initialize(URL location, ResourceBundle resources) {
        property = new PropertyStore();


        idColumn.setCellValueFactory(new PropertyValueFactory<Property, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("description"));
        costColumn.setCellValueFactory(new PropertyValueFactory<Property, Double>("cost"));

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

    }



}
