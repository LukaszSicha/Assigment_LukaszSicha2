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
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

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
    @FXML
    private TextArea feedbackTableView;
    @FXML
    private TableView tableView;
    @FXML
    private TextField txtCategory;
    @FXML
    private TextField txtCounty;

    @FXML private TableColumn<Property, Integer> idColumn;  // notice the use of the wrapper class
    @FXML private TableColumn<Property, String> nameColumn;
    @FXML private TableColumn<Property, String> descriptionColumn;
    @FXML private TableColumn<Property, String> countyColum;
    @FXML private TableColumn<Property, String> addressColum;
    @FXML private TableColumn<Property, String> eircodeColum;
    @FXML private TableColumn<Property, Double> costColumn;





    ObservableList<String> county = FXCollections.observableArrayList("Any", "Waterford", "Wexford", "Limerick", "Cork", "Tipperary", "Galway");
    ObservableList<String> minPrice = FXCollections.observableArrayList("No Min", "10000", "15000", "20000", "25000", "30000", "35000", "40000", "45000", "55000");
    ObservableList<String> maxPrice = FXCollections.observableArrayList("No Max", "10000", "15000", "20000", "25000", "30000", "35000", "40000", "45000", "55000");
    ObservableList<String> propertyType = FXCollections.observableArrayList("Any", "Apartment", "Single House", "Double House", "Detached");


    public void initialize(URL location, ResourceBundle resources) {

        try {
            MyListOfObjects propertiesOne;
            ArrayList<Property> tableProperty = new ArrayList<>();
            XStream xstream = new XStream(new DomDriver());
            ObjectInputStream is = xstream.createObjectInputStream
                    (new FileReader("propertyFile.xml"));
            propertiesOne = (MyListOfObjects) is.readObject();
            is.close();
            for (int i = 0; i < propertiesOne.size(); i++) {
                Property forProperty = (Property) propertiesOne.get(i);
                tableProperty.add(forProperty);
            }

            ObservableList<Property> data = FXCollections.observableArrayList(tableProperty);

            idColumn.setCellValueFactory(new PropertyValueFactory<Property, Integer>("propertyId"));
            costColumn.setCellValueFactory(new PropertyValueFactory<Property, Double>("price"));
            countyColum.setCellValueFactory(new PropertyValueFactory<Property, String>("locationGeneral"));
            addressColum.setCellValueFactory(new PropertyValueFactory<Property, String>("address"));
            eircodeColum.setCellValueFactory(new PropertyValueFactory<Property, String>("eircode"));

            tableView.setItems(data);
        }

        catch (Exception e){
        }

        property = new PropertyStore();
        comboCounty.setItems(county);
        comboPropertyType.setItems(propertyType);
        comboMinPrice.setItems(minPrice);
        comboMaxPrice.setItems(maxPrice);
        comboCounty.setValue("Any");
        comboPropertyType.setValue("Any");
        comboMinPrice.setValue("No Min");
        comboMaxPrice.setValue("No Max");
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
        try{
            int propertiesId;
            try {
                propertiesId = Integer.parseInt(txtpropertyId.getText());
            } catch (Exception t) {
                txtfeedback.setText("Enter number");
                propertiesId = Integer.parseInt(null);
            }
                String description = txtdescription.getText();
                String address = txtaddress.getText();
                String propertyType = comboPropertyType.getValue();
                String comboCountyValue = comboCounty.getValue();
                String locationSpecificValue = txtlocationSpecific.getText();
                String BER = txtBER.getText();
                String Eircode = txtEircode.getText();
                double price = Double.parseDouble(txtprice.getText());

                       if( property.addProperty(propertiesId, description, address, propertyType, comboCountyValue, locationSpecificValue, BER, Eircode, price)) {
                           txtpropertyId.setText("");
                           txtdescription.setText("");
                           txtaddress.setText("");
                           comboPropertyType.setValue("Any");
                           comboCounty.setValue("Any");
                           txtlocationSpecific.setText("");
                           txtBER.setText("");
                           txtEircode.setText("");
                           txtprice.setText("");
                           txtfeedback.setText("Property Added");
                           Main.set_pane(0);
                       }
                       else{
                       txtfeedback.setText("Testing  \n");
                       }

        }
        catch(Exception d){
            txtfeedback.setText("Please Make sure you\nentered Everything correctly\n" + d);
        }
     }





    /*public void handleLoadBtn(ActionEvent e) throws Exception{
        property.loadProperty();
        txtfeedback.setText(property.listProperties());

    }*/

    public void handleButtonCreatePropertySite(ActionEvent e) throws Exception{
        Main.set_pane(8);
    }

    public void handleButtonViewPropertiesAgent(ActionEvent e) throws Exception{
        Main.set_pane(9);
    }

   /* public void handleReadBtn(ActionEvent e) throws Exception{
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
    }*/




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

        String propertyType = comboPropertyType.getValue();
        String locationProperty = comboCounty.getValue();
        String minPrice = comboMinPrice.getValue();
        String maxPrice = comboMaxPrice.getValue();

        ArrayList<Property>propertyAdding = new ArrayList<>();
        propertyAdding = property.SearchMethod(propertyType, locationProperty, minPrice, maxPrice);

        ObservableList<Property> Newdata = FXCollections.observableArrayList(propertyAdding);
        idColumn.setCellValueFactory(new PropertyValueFactory<Property, Integer>("propertyId"));
        costColumn.setCellValueFactory(new PropertyValueFactory<Property, Double>("price"));
        countyColum.setCellValueFactory(new PropertyValueFactory<Property, String>("locationGeneral"));
        addressColum.setCellValueFactory(new PropertyValueFactory<Property, String>("address"));
        eircodeColum.setCellValueFactory(new PropertyValueFactory<Property, String>("eircode"));
        tableView.setItems(Newdata);
    }

    public void changeSceneToDetailedViewBtn(ActionEvent e) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/viewProportiesSite.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }



}
