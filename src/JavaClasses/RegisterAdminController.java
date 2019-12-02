package JavaClasses;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        MyListOfObjects admins;
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("admins.xml"));
            admins = (MyListOfObjects) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            admins =  new MyListOfObjects();
            txtAreaFeedback.setText("New Password File");
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        try {
            Admin admin = new Admin(username, password, name, surname, email, phoneNumbertxt);
            admins.add(admin);
            Main.setAdmin(admin);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("admins.xml"));
            out.writeObject(admins);
            out.close();
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error writing to Password File");
            return false;
        }
        return true;
    }


    public void handleHomeBtn(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }



}
