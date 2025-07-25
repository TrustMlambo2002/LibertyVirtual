import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javax.swing.JOptionPane;

public class VotingPageController {

    @FXML
    private AnchorPane ancVotingPage;

    @FXML
    private Button btnLocal;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnNational;

    @FXML
    private Button btnProvincial;

    @FXML
    private ImageView ivLocal;

    @FXML
    private ImageView ivNational;

    @FXML
    private ImageView ivProvincial;
    
    static String btnName;

    @FXML
    void btnLocal(ActionEvent event) {
        btnName = "local";
        switchPage();

    }

    @FXML
    void btnLogout(ActionEvent event) {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LVApp.fxml"));
            Parent parent = (Parent) fxmlLoader.load();
            
            Stage primaryStage = new Stage();
            Scene scene = new Scene(parent);
            
            primaryStage.setTitle("Liberty Virtual");
            primaryStage.setScene(scene);
            primaryStage.show();
            
            primaryStage = (Stage) btnLogout.getScene().getWindow();
            primaryStage.close();
        }
        
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    @FXML
    void btnNational(ActionEvent event) {
        btnName = "national";
        switchPage();

    }

    @FXML
    void btnProvincial(ActionEvent event) {
        btnName = "provincial";
        switchPage();

    }
    
    void switchPage()
    {
        try
        {
            
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PartyPage.fxml"));
            
            Parent parent = (Parent) fxmlLoader.load();
            
            PartyPageController ppc = fxmlLoader.getController();
            ppc.voted(btnName);
            
            Stage primaryStage = new Stage();
            Scene scene = new Scene(parent);
            
            primaryStage.setTitle("Liberty Virtual");
            primaryStage.setScene(scene);
            primaryStage.show();
            
            primaryStage = (Stage) btnProvincial.getScene().getWindow();
            primaryStage.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

}
