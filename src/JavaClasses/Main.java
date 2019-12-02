package JavaClasses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    static AnchorPane root;
    static List<AnchorPane> anchor = new ArrayList<AnchorPane>();
    private static int sceneIndex = 0;
    private static Admin admin = null;
    private static Agent agent = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = (AnchorPane)FXMLLoader.load(getClass().getResource("/FXML/Anchor.fxml"));

        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("SaleSite.fxml"))); //index 0
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("LoginAgent.fxml"))); //index 1
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"))); //index 2
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("RegisterAdmin.fxml"))); //index 3
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("RegisterAgent.fxml"))); //index 4
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("ViewerSaleSite.fxml"))); //index 5
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AdminSaleSite.fxml"))); //index 6
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AgentSaleSite.fxml"))); //index 7
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AgentCreateSaleSite.fxml"))); //index 8
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AgentUpdateSaleSite.fxml"))); //index 9
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AdminCreateAgentSite.fxml"))); //index 10
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AdminReadAgentSite.fxml"))); //index 11

        root.getChildren().add(anchor.get(0));

        primaryStage.setTitle("Property Sale Site");
        primaryStage.setScene(new Scene(root, 550, 500));
        primaryStage.show();
    }

    private void init_app(){
        for(int i=0; i<anchor.size();i++){
            //can be used to initiate some details about each pane
        }
    }

    public static AnchorPane get_pane(int index){
        return anchor.get(index);
    }

    public static void set_pane(int index){
        root.getChildren().remove(anchor.get(sceneIndex));  //remove the existing pane from the root
        root.getChildren().add(anchor.get(index));          //add the selected pane to the root
        sceneIndex=index;                                   //update the stored sceneIndex
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin Admin) {
        Main.admin = admin;
    }

    public static Agent getAgent() {
        return agent;
    }

    public static void setAgent(Agent agent) {
        Main.agent = agent;
    }
}