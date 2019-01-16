import javax.swing.*;
import java.awt.*;
import java.io.*;

public class databaseBuild extends JFrame {
    // this int keeps track of which databaseID the program is on. It just counts the number of packages that have been entered.
    static int databaseIDNum;
    public static JButton btnDatabaseID, btnPackageName, btnPackageID, btnDateArrived, btnWeight;
    public static JPanel databaseMainPanel;
    // the number of rows that the window will have. this number changes as new data is entered and more rows need to be added.
    int maxRows;
    //If there is only one entry then when the user clicks the view button, the database window does not display because there are
    //no entries into the database, and the database looks awkward.
    public static boolean isThereOneEntry = false;

    public databaseBuild() {
        databaseMainPanel = new JPanel();
        databaseMainPanel.setLayout(new GridLayout(maxRows++, 5));

        btnDatabaseID = new JButton("Database ID");
        btnPackageName = new JButton("Package Name");
        btnPackageID = new JButton("Package ID");
        btnDateArrived = new JButton("Date Arrived");
        btnWeight = new JButton("Weight");

        // adds the buttons to an array so that it can be added to the main panel more efficiently.
        JButton[] topButtons = {btnPackageID, btnDatabaseID, btnPackageName, btnDateArrived, btnWeight};
        // set the dimensions that will be applied to the buttons.
        Dimension buttonDimension = new Dimension(0, 35);

        for (int i = 0; i < topButtons.length; i++) {
            topButtons[i].setMaximumSize(buttonDimension);
            databaseMainPanel.add(topButtons[i]);
            topButtons[i].addActionListener(new databaseFunctions());
        }

        // try statement that writes all the contents of data.txt to the databaseBuild window so that the user can view all
        // of the package entries and all the information that follows.
        try {
            File dataFile = new File("src//data.txt");
            BufferedReader in = new BufferedReader(new FileReader(dataFile));

            String currentLine = in.readLine();
            String[] currentLineComponents;

            while (currentLine != null) {
                currentLineComponents = currentLine.split("<>");
                for (int i = 0; i < currentLineComponents.length; i++) {
                    // we use a placeholder JLabel to add each line of data.txt to the database window. This is easier than 
                    // creating a new JLabel for each line of data.
                    JLabel placeHolder = new JLabel(currentLineComponents[i]);
                    placeHolder.setHorizontalAlignment(JLabel.CENTER);
                    databaseMainPanel.add(placeHolder);
                }
                // add one to the maximum rows so that more data can be added as needed.
                maxRows++;
                currentLine = in.readLine();
            }
            in.close();
        } catch (IOException error) {
            System.out.println("Database JLabel");
        }

        setTitle("Package Sorter");
        setContentPane(databaseMainPanel);
        setSize(1000, maxRows * 30);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
