import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import java.io.*;
import javax.swing.JOptionPane;
import javafx.scene.image.*;
import java.util.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class loginPageController
{
    @FXML
    private AnchorPane ancLogingPage;
    
    @FXML
    private Button btnLogin;
    
    @FXML
    private Button btnSignup;
    
    @FXML
    private Hyperlink hyperForgot;
    
    @FXML
    private Label lblUsername;
    
    @FXML
    private Label lblPassword;
    
    @FXML
    private TextField tfUsername;
    
    @FXML
    private PasswordField pfPassword;
    
    @FXML
    private Label lblSlogan;
    
    @FXML
    private ImageView  imvFirstPicture;
    
    @FXML
    private ImageView imvSecondPicture;
    
    File file = new File("userDetails.txt");
    
    boolean isValid = false;
    boolean isSignedUp = false;
    static int voters;
    
    public void SignUpButton() throws IOException
    {
        String username = tfUsername.getText();
        String password = pfPassword.getText();
        boolean singUpPressed = true;
        
        
        FileWriter output = new FileWriter(file, true);
        try
        {
            if(file.exists() )
            {
                while(singUpPressed)
                {
                    output.write(username +","+ password + "\n");
                    isSignedUp = true;
                    JOptionPane.showMessageDialog(null, "Sign up Successful");
                    singUpPressed = false;
                }
                output.close();
                tfUsername.setText("");
                pfPassword.setText("");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "File does not exist");
            }
            
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        
    }
    
    public void loginButton() throws IOException
    {
          
        String username = null;
        String password = null;
        
        try
        {
            Scanner sc = new Scanner(file);
    
            if (file.exists())
            {
                String line;
    
                while (sc.hasNextLine())
                {
                    line = sc.nextLine();
                    
                    String[] parts = line.split(",");
                    username = parts[0].trim();
                    password = parts[1].trim();
    
                    JOptionPane.showMessageDialog(null, username + "," + password);
    
                    if (username.equals(tfUsername.getText()))
                    {
                        if (password.equals(pfPassword.getText()))
                        {
                            isValid = true;
                            tfUsername.setText("");
                            pfPassword.setText("");
                            JOptionPane.showMessageDialog(null, "Total voters: " + voters);
                            break;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Password incorrect");
                        }
                    }
                    
                }
                
                sc.close();
    
                if (isValid)
                {
                    try
                    {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VotingPageApp.fxml"));
                        Parent parent = (Parent) fxmlLoader.load();
                        
                        
                        Stage primaryStage = new Stage();
                        Scene scene = new Scene(parent);
                        
                        primaryStage.setTitle("Liberty Virtual");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        
                        Stage mainStage = (Stage) btnLogin.getScene().getWindow();
                        mainStage.close();
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, ex.toString());
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Username incorrect");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "File does not exist");
            }
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        
    }

}

