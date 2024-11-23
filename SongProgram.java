package hashingAndDocumentation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SongProgram {

    // HashMap to store SongRecords with the song's ID as the key
    private HashMap<String, SongRecord> songMap;

    // Constructor
    public SongProgram() {
        songMap = new HashMap<>();//innitialize the hashmap(初始化hashmap)
    }

    // Method to load songs from a CSV file
    public void loadSongsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            //read in first line and do nothing with it
            br.readLine();
            
            while ((line = br.readLine()) != null) {
            	
            	//System.out.println(line);//for testing(測試用，非必要)
                // Create a SongRecord from the line and add it to the map
                SongRecord song = new SongRecord(line);
                songMap.put(song.getId(), song);
            }
            System.out.println("Songs successfully loaded from CSV.");
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }// to catch the error situation
    }

    // Method to retrieve a SongRecord by ID
    public SongRecord getSongById(String id) {
        return songMap.get(id);
    }

    // Method to print all songs (for debugging or display purposes)
    public void printAllSongs() {
        for (SongRecord song : songMap.values()) {
            System.out.println(song);
        }
    }//this si a for-each loop, first, scan the whole hashmap
    
    // GUI method to search for a song by ID
    public void openSearchGui() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Song Lookup");
            frame.setSize(650, 450);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            JPanel panel = new JPanel(new FlowLayout());
            JLabel label = new JLabel("Enter Song ID:");
            JTextField idField = new JTextField(20);
            JButton searchButton = new JButton("Search");
            JTextArea resultArea = new JTextArea(20, 50);
            resultArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(resultArea);
    
            panel.add(label);
            panel.add(idField);
            panel.add(searchButton);
            panel.add(scrollPane);
    
            searchButton.addActionListener(e -> {
                String id = idField.getText().trim();
                if (id.isEmpty()) {
                    resultArea.setText("Please enter a valid Song ID.");
                    return;
                }
                SongRecord song = getSongById(id);
                if (song != null) {
                    resultArea.setText("Song Found:\n" + song.toString());
                } else {
                    resultArea.setText("Song with ID " + id + " not found.");
                }
            });
    
            frame.add(panel);
            frame.setVisible(true);
        });
    }

    // Main method to demonstrate functionality and open GUI
   

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        SongProgram program = new SongProgram();
        
        // 載入CSV檔案(load CVS file)
        String filePath = "/Users/benson/Downloads/hashingAndDocumentation/data.csv";
        program.loadSongsFromCSV(filePath);
        
        // 啟動GUI介面(open GUI)
        program.openSearchGui();
    }
}

/*I'm not sure what should we do for this assignment,
 so I ust change the GUI size and add some Chinese to help me understand those code.*/
