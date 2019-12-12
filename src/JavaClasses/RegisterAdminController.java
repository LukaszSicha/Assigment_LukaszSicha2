package JavaClasses;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RegisterAdminController {


    @FXML
    private TextField firstname;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repeat_password;
    @FXML
    private TextArea txtAreaFeedback;
    @FXML
    private TextField phoneNumbertxt;



    public void handleRegisterBtn(ActionEvent e) throws Exception {
        if (username.getText().length() < 4 || password.getText().length() < 4) {
            txtAreaFeedback.setText("Username and Password need to be 4 characters or more");
        } else if (!password.getText().equals(repeat_password.getText())) {
            txtAreaFeedback.setText("Passwords must match");
        } else if (register(username.getText(), password.getText(), email.getText(), surname.getText(), email.getText(), Integer.parseInt(phoneNumbertxt.getText()))) {
            Main.set_pane(1);
            txtAreaFeedback.setText("Successful Registration");
        }
    }

    private boolean register(String username, String password, String name, String surname, String email, int phoneNumbertxt) {
        MyListOfObjects admins = new MyListOfObjects();
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("admins.xml"));
            admins = (MyListOfObjects) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            admins =  new MyListOfObjects();
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Cannot access password file");
            return false;
        }


        try {
            Admin admin = new Admin(username, password, name, surname, email, phoneNumbertxt);
            admins.add(admin);
            Main.setAdmin(admin);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("admins.xml"));
            out.writeObject(admins);
            out.close();
            return true;
        } catch (Exception e) {
            txtAreaFeedback.setText("ERROR, cannot edit file");
            return false;
        }
    }


    public void handleHomeBtn(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/SaleSale.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void handleButtonAdminRead(ActionEvent e) throws Exception {
        Main.set_pane(11);
    }

    public void handleButtonAdminCreate(ActionEvent e) throws Exception {
        Main.set_pane(10);
    }



}
