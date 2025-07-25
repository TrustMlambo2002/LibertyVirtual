import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.scene.control.TextArea;
import java.util.HashMap;

public class PartyPageController {

    @FXML
    private AnchorPane ancPartyPage;

    @FXML
    private Button btnVote;

    @FXML
    private HBox hbxAnc;

    @FXML
    private HBox hbxDA;

    @FXML
    private HBox hbxEFF;

    @FXML
    private HBox hbxMK;

    @FXML
    private ImageView ivAnc;

    @FXML
    private ImageView ivDA;

    @FXML
    private ImageView ivEFF;

    @FXML
    private ImageView ivMK;

    @FXML
    private Label lblTitleParty;

    @FXML
    private RadioButton rbtnAnc;

    @FXML
    private RadioButton rbtnDA;

    @FXML
    private RadioButton rbtnEFF;

    @FXML
    private RadioButton rbtnMK;

    @FXML
    private ToggleGroup vote;
    
    private int national;
    private int provincial;
    private int local;
    private int counter;
    
    private String partyName;

    @FXML
    void vote(ActionEvent event)
    {
        voted(VotingPageController.btnName);
    }
    
    void voted(String btnName)
    {
        switch(btnName)
        {
            case "national":
                if(rbtnAnc.isSelected())
                {
                    counter++;
                    partyName = "ANC";
                    switchPage();
                }
                else if(rbtnDA.isSelected())
                {
                    counter++;
                    partyName = "DA";
                    switchPage();
                }
                else if(rbtnEFF.isSelected())
                {
                    counter++;
                    partyName = "EFF";
                    switchPage();
                }
                else if(rbtnMK.isSelected())
                {
                    counter++;
                    partyName = "MK";
                    switchPage();
                }
                saveVotes();
                break;
                
            case "provincial":
                if(rbtnAnc.isSelected())
                {
                    counter++;
                    partyName = "ANC";
                    switchPage();
                }
                else if(rbtnDA.isSelected())
                {
                    counter++;
                    partyName = "DA";
                    switchPage();
                }
                else if(rbtnEFF.isSelected())
                {
                    counter++;
                    partyName = "EFF";
                    switchPage();
                }
                else if(rbtnMK.isSelected())
                {
                    counter++;
                    partyName = "MK";
                    switchPage();
                }
                saveVotes();
                break;
                
            case "local":
                if(rbtnAnc.isSelected())
                {
                    counter++;
                    partyName = "ANC";
                    switchPage();
                }
                else if(rbtnDA.isSelected())
                {
                    counter++;
                    partyName = "DA";
                    switchPage();
                }
                else if(rbtnEFF.isSelected())
                {
                    counter++;
                    partyName = "EFF";
                    switchPage();
                }
                else if(rbtnMK.isSelected())
                {
                    counter++;
                    partyName = "MK";
                    
                    switchPage();
                }
                saveVotes();
                break;
        }
        
    }
    
    void switchPage()
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
            
            Stage stage = (Stage) btnVote.getScene().getWindow();
            stage.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    void saveVotes()
    {
        File file = new File("votes.txt");
        
        try
        {
            FileWriter out = new FileWriter(file, true);
            
            if(partyName != null)
            {
                out.write(VotingPageController.btnName + "," + partyName + "," + counter + "\n");
            }
            
            out.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        } 
    }
    
    void display()
    {
        // Create a HashMap to store the vote counts for each party and vote type
        HashMap<String, HashMap<String, Integer>> voteCounts = new HashMap<>();
        voteCounts.put("EFF", new HashMap<>());
        voteCounts.put("DA", new HashMap<>());
        voteCounts.put("ANC", new HashMap<>());
        voteCounts.put("MK", new HashMap<>());

        // Initialize vote counts for each party and vote type
        for (String party : voteCounts.keySet()) {
            voteCounts.get(party).put("national", 0);
            voteCounts.get(party).put("provincial", 0);
            voteCounts.get(party).put("local", 0);
        }

        // Read the file and count the votes
        try {
            BufferedReader reader = new BufferedReader(new FileReader("votes.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String voteType = parts[0].trim();
                String party = parts[1].trim().toUpperCase();
                // Increment the vote count for the corresponding party and vote type
                int currentCount = voteCounts.get(party).get(voteType);
                voteCounts.get(party).put(voteType, currentCount + 1);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        // Print the vote counts for each party and vote type
        for (String party : voteCounts.keySet()) {
            System.out.println("Party: " + party);
            HashMap<String, Integer> partyVotes = voteCounts.get(party);
            for (String voteType : partyVotes.keySet()) {
                System.out.println("\t" + voteType + ": " + partyVotes.get(voteType));
            }
        }
        
    }
    
}
