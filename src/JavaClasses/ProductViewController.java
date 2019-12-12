package JavaClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.Random;

import java.io.IOException;

public class ProductViewController {
    @FXML
    private ImageView image;
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


    public void initData(Property property)
    {

        Random random = new Random();
            int s = random.nextInt(6);

            Image img = new Image("Images/house"+s+".jpg");
            image.setImage(img);

        txtpropertyId.setText(""+property.getPropertyId());
        txtdescription.setText(property.getDescription());
        txtaddress.setText(property.getAddress());
        txtlocationSpecific.setText(""+property.getLocationSpecific());
        txtBER.setText(""+property.getBER());
        comboPropertyType.setValue(property.getCategory());
        comboCounty.setValue(property.getLocationGeneral());
        txtEircode.setText(property.getEircode());
        txtprice.setText(Double.toString(property.getPrice()));
    }


    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../FXML/SaleSite.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }



}