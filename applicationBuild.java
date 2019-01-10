import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class applicationBuild extends JFrame {

    //Constructor Section
    public static JTextField txtPackageID, txtPackageName;
    public JLabel lblPackageID, lblPackageName;
    public static JLabel lblDatabaseIDNum;
    //We use the static keyword here because we want these buttons to remain the same throughout the entire program.
    //That means if I click btnView in a different class, we want to be referencing the same button throughout the whole program.
    public static JButton btnView, btnEnter, btnClear, btnDelete;;
    //If there is only one entry then when the user clicks the view button, the database window does not display because there are
    //no entrys into the database, and the database looks awkward.
    public static boolean isThereOneEntry = false;
    public static SpinnerModel sm;

    public static void main(String[] args) {
        new applicationBuild();
    }

    public applicationBuild() {
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel mainPanel = new JPanel(new GridBagLayout());
        //Default values of weightx and weighty are 0, we need them to be 100 for this application.
        gbc.weightx = 100;
        gbc.weighty = 100;

        //This is the title image
        ImageIcon title = new ImageIcon("src//title.png");
        JLabel imgTitle = new JLabel(title);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.ipady = 50;
        mainPanel.add(imgTitle, gbc);

        //This is the box image.
        ImageIcon box = new ImageIcon("src//box.png");
        JLabel imgBox = new JLabel(box);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipady = 0;
        mainPanel.add(imgBox, gbc);

        //It is here that the information will be entered.
        //TODO Things to be entered so far: package ID, package name, Database ID(++ each package), date added, weight
        JPanel informationEntry = new JPanel(new GridLayout(5, 2, 10, 5));
        gbc.gridx = 1;
        JLabel databaseID = new JLabel("Database ID:");
        lblDatabaseIDNum = new JLabel(Integer.toString(databaseBuild.databaseIDNum));
        lblDatabaseIDNum.setHorizontalAlignment(JLabel.CENTER);
        informationEntry.add(databaseID);
        informationEntry.add(lblDatabaseIDNum);

        lblPackageID = new JLabel("Package ID (8):");
        txtPackageID = new JTextField();
        lblPackageName = new JLabel("Package Name:");
        txtPackageName = new JTextField();

        JTextField[] txtInformationEntry = {txtPackageID, txtPackageName};
        JLabel[] lblInformationEntry = {lblPackageID, lblPackageName};

        for (int i = 0; i < txtInformationEntry.length; i++) {
            informationEntry.add(lblInformationEntry[i]);
            informationEntry.add(txtInformationEntry[i]);
        }

        JLabel dateArrived = new JLabel("Date Arrived:");
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        JLabel date = new JLabel(df.format(today));
        date.setHorizontalAlignment(JLabel.CENTER);

        informationEntry.add(dateArrived);
        informationEntry.add(date);

        JLabel packageWeight = new JLabel("Weight (0 - 100Kg):");
        sm = new SpinnerNumberModel(0, 0, 100, 0.1);
        JSpinner weight = new JSpinner(sm);
        //Centers the text of the JSpinner
        //TODO Explain more in depth and in detail so that piazza knows about what you are talking about
        JComponent editor = weight.getEditor();
        JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)editor;
        spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);

        informationEntry.add(packageWeight);
        informationEntry.add(weight);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        Dimension buttonDimension = new Dimension(100, 35);

        btnView = new JButton("View");
        btnView.setMaximumSize(buttonDimension);
        btnView.addActionListener(new applicationFunctions());
        buttonsPanel.add(btnView);
        buttonsPanel.add(Box.createHorizontalStrut(10));
        btnDelete = new JButton("Delete");
        btnDelete.setMaximumSize(buttonDimension);
        btnDelete.addActionListener(new applicationFunctions());
        buttonsPanel.add(btnDelete);
        buttonsPanel.add(Box.createHorizontalStrut(10));
        btnEnter = new JButton("Enter");
        btnEnter.setMaximumSize(buttonDimension);
        btnEnter.addActionListener(new applicationFunctions());
        buttonsPanel.add(btnEnter);
        buttonsPanel.add(Box.createHorizontalStrut(10));
        btnClear = new JButton("Clear");
        btnClear.setMaximumSize(buttonDimension);
        btnClear.addActionListener(new applicationFunctions());
        buttonsPanel.add(btnClear);

        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        rightPanel.add(informationEntry);
        rightPanel.add(buttonsPanel);

        mainPanel.add(rightPanel, gbc);

        JLabel bottomText = new JLabel("Package Installer created by Ryan and Jay. 2019", SwingConstants.CENTER);
        //Added as += 1 so that no matter how many components we add, this one will still remain at the bottom.
        gbc.gridy += 1;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(bottomText, gbc);

        setTitle("Package Sorter");
        setContentPane(mainPanel);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        //This part of the JFrame building part sets the location of the window to the middle of the screen.
        setLocationRelativeTo(null);
    }
}