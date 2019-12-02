package JavaClasses;

import java.io.*;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class LoginAgent {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private TextField firstName;
    @FXML private TextField surename;
    @FXML private TextField email;
    @FXML private TextField userType;
    @FXML private TextArea txtAreaFeedback;




    public void handleLoginBtn(ActionEvent e) throws Exception {
        if(username.getText().length()<4 || password.getText().length()<4 ){
            txtAreaFeedback.setText("Username and Password need to be 4 characters or more");
        }
        else if(login(username.getText(),password.getText())){
            txtAreaFeedback.setText("Successful Login");
            Main.set_pane(7);
        }
        else {
            txtAreaFeedback.setText("Un-Successful Login");
            password.clear();
        }
    }

    private boolean login(String username, String password) {
        MyListOfObjects agents;
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("agents.xml"));
            agents = (MyListOfObjects) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            agents =  new MyListOfObjects();
            txtAreaFeedback.setText("Password File not located");
            return false;

        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        for (Agent user: users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
                return true;
            System.out.print(users.get(0));
        }
        return false;
    }

    public void handleButtonBack(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }


}
