import javax.swing.*;
import java.awt.*;

public class databaseBuild extends JFrame {
    public static void main(String[] args) {
        new databaseBuild();
    }

    public static JButton btnDatabaseID, btnPackageName, btnPackageID, btnDateArrived, btnWeight;
    public static JPanel databaseMainPanel;
    int maxRows = 0;

    public databaseBuild() {

        databaseMainPanel = new JPanel();
        databaseMainPanel.setLayout(new GridLayout(maxRows++, 5));

        btnDatabaseID = new JButton("Database ID");
        btnPackageName = new JButton("Package Name");
        btnPackageID = new JButton("Package ID");
        btnDateArrived = new JButton("Date Arrived");
        btnWeight = new JButton("Weight");

        JButton[] topButtons = {btnDatabaseID, btnPackageName, btnPackageID, btnDateArrived, btnWeight};
        Dimension buttonDimension = new Dimension(0, 35);

        for (int i = 0; i < topButtons.length; i++) {
            topButtons[i].setMaximumSize(buttonDimension);
            databaseMainPanel.add(topButtons[i]);
        }

        setTitle("Package Sorter");
        setContentPane(databaseMainPanel);
        setSize(1000,maxRows*45);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
