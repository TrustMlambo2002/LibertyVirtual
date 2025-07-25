import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VoteCounter {
    public static void main(String[] args) {
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

        // Determine the winner for each vote type
        HashMap<String, String> winners = new HashMap<>();
        for (String voteType : new String[]{"national", "provincial", "local"}) {
            String winner = "";
            int maxVotes = 0;
            for (String party : voteCounts.keySet()) {
                int votes = voteCounts.get(party).get(voteType);
                if (votes > maxVotes) {
                    maxVotes = votes;
                    winner = party;
                } else if (votes == maxVotes) {
                    winner = "TIE";
                }
            }
            winners.put(voteType, winner);
        }

        // Print the vote counts for each party and vote type
        for (String party : voteCounts.keySet()) {
            System.out.println("Party: " + party);
            HashMap<String, Integer> partyVotes = voteCounts.get(party);
            for (String voteType : partyVotes.keySet()) {
                System.out.println("\t" + voteType + ": " + partyVotes.get(voteType));
            }
        }

        // Print the winners for each vote type
        System.out.println("\nWinners:");
        for (Map.Entry<String, String> entry : winners.entrySet()) {
            System.out.println(entry.getKey() + " winner: " + entry.getValue());
        }

        // Determine the overall poll winner based on the most votes
        String overallWinner = "";
        int maxOverallVotes = 0;
        for (String party : voteCounts.keySet()) {
            int totalVotes = 0;
            for (int votes : voteCounts.get(party).values()) {
                totalVotes += votes;
            }
            if (totalVotes > maxOverallVotes) {
                maxOverallVotes = totalVotes;
                overallWinner = party;
            } else if (totalVotes == maxOverallVotes) {
                overallWinner = "TIE";
            }
        }
        System.out.println("\nOverall winner: " + overallWinner);
    }
}
