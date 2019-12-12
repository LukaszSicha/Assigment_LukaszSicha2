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
import JavaClasses.Agent;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RegisterAgentController {


    @FXML
    private TextField firstname;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private TextField username2;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repeat_password;
    @FXML
    private TextArea txtAreaFeedback;
    @FXML
    private TextField txtphoneNumber;
    @FXML
    private TextField agentNumber;
    @FXML
    private Button readButton;
    @FXML
    private TextArea feedback;


    public void handleRegisterBtn(ActionEvent e) throws Exception {
        if (username.getText().length() < 4 || password.getText().length() < 4) {
            txtAreaFeedback.setText("Username and Password need to be 4 characters or more");
        } else if (!password.getText().equals(repeat_password.getText())) {
            txtAreaFeedback.setText("Password must match");
        } else if (register(username.getText(), password.getText(), firstname.getText(), surname.getText(), email.getText(), Integer.parseInt(txtphoneNumber.getText()))) {
            Main.set_pane(1);
            txtAreaFeedback.setText("Successful Registration");
        }
    }

    public boolean register(String username, String password, String agentId, String location, String name, int phoneNumber){
        MyListOfObjects agents = new MyListOfObjects();
        if(agents.isEmpty()) {
            try {
                try{
                    XStream xstream = new XStream(new DomDriver());
                    ObjectInputStream is = xstream.createObjectInputStream
                            (new FileReader("saveFiles/agents.xml"));
                    agents = (MyListOfObjects) is.readObject();
                    is.close();
                }
                catch(Exception e){
                    txtAreaFeedback.setText("Cannot Load Agents");
                }

                Agent agentLocal = new Agent(username, password, agentId, location, name, phoneNumber);
                XStream xstream = new XStream(new DomDriver());
                agents.add(agentLocal);
                ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("agents.xml"));
                out.writeObject(agents);
                out.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        else {
            try {
                Agent agentLocal = new Agent(username,  password, agentId, location, name, phoneNumber);
                XStream xstream = new XStream(new DomDriver());
                agents.add(agentLocal);
                ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("agents.xml"));
                out.writeObject(agents);
                out.close();
                return true;
            } catch (Exception t) {
                return false;
            }
        }
    }



   /* public Agent getAgentDetails(String id) {
        try {
            String id1 = (id);
            for (Agent item : agents) {
                if (item.getEmail() == id1) {
                }
            }
        } catch (Exception e) {
        }
        return null;
    }*/


   /*public void handleReadAgentBtn(ActionEvent e) throws Exception{
        txtAreaFeedback.setText("Reading Agents");
        String id = txtAreaFeedback.getText();
        Agent agent = getAgentDetails(id);
        int index = Integer.parseInt(agentNumber.getText());
        if(agents==null)
            txtAreaFeedback.setText("Invalid Agent");
        else{
            username.setText(""+ agent.getUsername());
            password.setText(""+ agent.getPassword());
            firstname.setText(""+agent.getFirstname());
            surname.setText(""+agent.getSurname());
            email.setText(""+agent.getEmail());
            txtphoneNumber.setText(""+ Integer.toString(agent.getPhoneNumber()));
        }
    }*/



    public void handleHomeBtn(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/SaleSite.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    public void handleButtonAdminBack(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/AdminSaleSite.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    /*public void handleDeleteAgent(ActionEvent e) throws Exception{
        String username = username2.getText();
        agentys.deleteAgents(username);
        txtAreaFeedback.setText("Agent deleted sucessfully");

    }*/
}
