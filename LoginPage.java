import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.io.File;
import java.util.Scanner;
import java.awt.Label;
import java.io.LineNumberReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class LoginPage extends Application
{    
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent parent = FXMLLoader.load(getClass().getResource("LVApp.fxml"));
        
        Scene scene = new Scene(parent);
        primaryStage.setTitle("Liberty Virtual");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        File file = new File("userDetails.txt");
        try
        {
            FileReader fr = new FileReader(file);
            LineNumberReader lnr = new LineNumberReader(fr);
            lnr.skip(Long.MAX_VALUE);
            
            loginPageController.voters = lnr.getLineNumber();
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
