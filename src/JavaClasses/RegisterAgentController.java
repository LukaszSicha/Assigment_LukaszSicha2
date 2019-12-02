package JavaClasses;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    private boolean register(String username, String password, String name, String surname, String email, int txtphoneNumber) {
        MyListOfObjects agents;
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("agents.xml"));
            agents = (MyListOfObjects) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            agents =  new MyListOfObjects();
            txtAreaFeedback.setText("New Password File");
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        try {
            Agent agent = new Agent(username, password, name, surname, email, txtphoneNumber);
            agents.add(agent);
            Main.setAgent(agent);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("agents.xml"));
            out.writeObject(agents);
            out.close();
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error writing to Password File");
            return false;
        }
        return true;
    }


    /*@FXML
    public void saveAgent() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream
                (new FileWriter("propertyFile.xml"));
        out.writeObject(agents);
        out.close();
    }

    @FXML
    public void loadAgent() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("agents.xml"));
        agents = (ArrayList<Agent>) is.readObject();
        is.close();
    }*/



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
        Main.set_pane(0);
    }

    public void handleButtonAdminCreate(ActionEvent e) throws Exception {
        Main.set_pane(10);
    }

    public void handleButtonAdminBack(ActionEvent e) throws Exception {
        Main.set_pane(6);
    }

    public void handleButtonAdminRead(ActionEvent e) throws Exception {
        Main.set_pane(11);
    }

   /* public void handleDeleteAgent(ActionEvent e) throws Exception{
        String username = username2.getText();
        agentys.deleteAgents(username);
        txtAreaFeedback.setText("Agent deleted sucessfully");

    }*/
}
