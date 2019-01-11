import javax.swing.*;
import java.awt.*;
import java.io.*;

public class databaseBuild extends JFrame {
    public static void main(String[] args) {
        new databaseBuild();
    }

    static int databaseIDNum;
    public static JButton btnDatabaseID, btnPackageName, btnPackageID, btnDateArrived, btnWeight;
    public static JPanel databaseMainPanel;
    int maxRows = 0;
    //If there is only one entry then when the user clicks the view button, the database window does not display because there are
    //no entrys into the database, and the database looks awkward.
    public static boolean isThereOneEntry = false;

    public databaseBuild() {

        databaseMainPanel = new JPanel();
        databaseMainPanel.setLayout(new GridLayout(maxRows++, 5));

        btnDatabaseID = new JButton("Database ID");
        btnPackageName = new JButton("Package Name");
        btnPackageID = new JButton("Package ID");
        btnDateArrived = new JButton("Date Arrived");
        btnWeight = new JButton("Weight");
        
        JButton[] topButtons = {btnPackageID, btnDatabaseID, btnPackageName, btnDateArrived, btnWeight};
        Dimension buttonDimension = new Dimension(0, 35);

        for (int i = 0; i < topButtons.length; i++) {
            topButtons[i].setMaximumSize(buttonDimension);
            databaseMainPanel.add(topButtons[i]);
        }
        
        try {
            File dataFile = new File("src//data.txt");
            BufferedReader in = new BufferedReader(new FileReader(dataFile));
            String currentLine = in.readLine();

            String[] currentLineComponents;

            while (currentLine != null) {
                currentLineComponents = currentLine.split(" | ");
                for (int i = 0; i < currentLineComponents.length; i+=2) {
                    JLabel placeHolder = new JLabel(currentLineComponents[i]);
                    databaseMainPanel.add(placeHolder);
                }
                maxRows++;
                currentLine = in.readLine();
            }
            in.close();
        } catch (IOException error) {
            System.out.println("Database JLabel ");
        }

        setTitle("Package Sorter");
        setContentPane(databaseMainPanel);
        setSize(1000,maxRows*30);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}