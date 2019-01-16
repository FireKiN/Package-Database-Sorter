import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;

public class databaseBuild extends JFrame {
    public static void main(String[] args) {
        new databaseBuild();
    }

    static int databaseIDNum;
    public static JButton btnDatabaseID, btnPackageName, btnPackageID, btnDateArrived, btnWeight;
    public static JFrame frame;
    public static JPanel databaseMainPanel;
    int maxRows;
    //If there is only one entry then when the user clicks the view button, the database window does not display because there are
    //no entrys into the database, and the database looks awkward.
    public static boolean isThereOneEntry = false;

    public databaseBuild() {
        frame = new JFrame();
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
            topButtons[i].setBackground(Color.LIGHT_GRAY);
            databaseMainPanel.add(topButtons[i]);
            topButtons[i].addActionListener(new databaseFunctions());
        }

        try {
            File dataFile = new File("src//data.txt");
            BufferedReader in = new BufferedReader(new FileReader(dataFile));
            String currentLine = in.readLine();
            String[] currentLineComponents;

            while (currentLine != null) {
                currentLineComponents = currentLine.split("<>");
                for (int i = 0; i < currentLineComponents.length; i++) {
                    JLabel placeHolder = new JLabel(currentLineComponents[i]);
                    Border borderPlaceHolder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
                    placeHolder.setBorder(borderPlaceHolder);
                    placeHolder.setHorizontalAlignment(JLabel.CENTER);
                    databaseMainPanel.add(placeHolder);
                }
                maxRows++;
                currentLine = in.readLine();
            }
            in.close();
        } catch (IOException error) {
            System.out.println("Database JLabel");
        }

        frame.setTitle("Package Sorter");
        frame.setContentPane(databaseMainPanel);
        frame.setSize(1000, maxRows * 30);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}