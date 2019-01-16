import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class applicationBuild extends JFrame {

    //Constructor Section
    //We use the static keyword here because we want these buttons to remain the same throughout the entire program.
    //That means if I click btnView in a different class, we want to be referencing the same button throughout the whole program.
    public static JTextField txtPackageID, txtPackageName;
    public JLabel lblPackageID, lblPackageName;
    public static JLabel lblDatabaseIDNum;
    public static SpinnerModel sm;
    public static JButton btnView, btnEnter, btnClear, btnDelete;
    //If there is only one entry then when the user clicks the view button, the database window does not display because there are
    //no entries into the database, and the database looks awkward.
    public static boolean isThereOneEntry = false;
    public static String formattedDate;
    public static ImageIcon box = new ImageIcon("src//box.png");

    public static void main(String[] args) {
        new applicationLogin();
    }

    public applicationBuild() {
        GridBagConstraints gbc = new GridBagConstraints();
        //Default values of weightx and weighty are 0, we need them to be 100 for this application.
        gbc.weightx = 100;
        gbc.weighty = 100;

        //Defined mainPanel at the top here because we can append objects to the main panel and then change the gridbagconstraints
        //properties for the next object.
        JPanel mainPanel = new JPanel(new GridBagLayout());

        //This is the title image that says "Package Sorter" at the top of the applicationBuild window.
        ImageIcon title = new ImageIcon("src//title.png");
        JLabel imgTitle = new JLabel(title);
        gbc.gridx = 0;
        gbc.gridy = 0;
        //There is a total of two columns of the mainPanel and we want the title image to stretch into the two columns. The next two lines
        // accomplish this.
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 50;
        mainPanel.add(imgTitle, gbc);

        //This is the box image.
        JLabel imgBox = new JLabel(box);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        //The reason why we have to set the fill and ipady back to their default values is because we want to make sure that this object
        //and the next objects appended to the mainPanel using gridbagconstraints don't have the same values as the imgTitle.
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipady = 0;
        mainPanel.add(imgBox, gbc);

        //This panel is the section that you will enter the information that is going to correspond with the database table.
        JPanel informationEntry = new JPanel(new GridLayout(5, 2, 10, 5));
        gbc.gridx = 1;

        //DATABASE ID
        JLabel databaseID = new JLabel("Database ID:");
        try {
            //This try statement reads into the databaseIDTracker file which can be located in src//id.txt in applicationFunctions.java.
            BufferedReader in = new BufferedReader(new FileReader(applicationFunctions.databaseIDTracker));
            //After reading into the file it updates the databaseIDNum which is used to track the global ID number.
            databaseBuild.databaseIDNum = Integer.parseInt(in.readLine());
            in.close();
        } catch (IOException error) {
            System.out.println("Could not read to id.txt");
        }
        //The lblDatabaseIDNum uses the number that has been updated in the try catch statement to display what the current number is
        //in the program.
        lblDatabaseIDNum = new JLabel(Integer.toString(databaseBuild.databaseIDNum));
        lblDatabaseIDNum.setHorizontalAlignment(JLabel.CENTER);
        //After each object is finished being made it will be added to the informationEntry panel because it is done it's
        //visual components.
        informationEntry.add(databaseID);
        informationEntry.add(lblDatabaseIDNum);

        //PACKAGE ID & PACKAGE NAME
        lblPackageID = new JLabel("Package ID (8):");
        txtPackageID = new JTextField();
        lblPackageName = new JLabel("Package Name:");
        txtPackageName = new JTextField();

        //Creating an array and adding the objects to informationEntry through a for loop seems to be a lot less code to write.
        JTextField[] txtInformationEntry = {txtPackageID, txtPackageName};
        JLabel[] lblInformationEntry = {lblPackageID, lblPackageName};

        for (int i = 0; i < txtInformationEntry.length; i++) {
            informationEntry.add(lblInformationEntry[i]);
            informationEntry.add(txtInformationEntry[i]);
        }

        //DATE ARRIVED TEXT BOX
        JLabel dateArrived = new JLabel("Date Arrived:");
        Date today = new Date();
        //The cheat sheet that I used to create the date format can be found here: https://i.stack.imgur.com/OWYww.png
        //In basic terms, the reason why MM is capitalized instead of lowercase is because lowercase m represents the minutes
        //in an hour and the uppercase M represents the month.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        //SimpleDateFormat works a lot like DecimalFormat does in the way that once you create the format you want the string
        //to be in, you must then format the string using NAMEOFFORMAT.format(STRING TO FORMAT);
        formattedDate = dateFormat.format(today);
        JLabel date = new JLabel(formattedDate);
        date.setHorizontalAlignment(JLabel.CENTER);

        informationEntry.add(dateArrived);
        informationEntry.add(date);

        //PACKAGE WEIGHT
        JLabel packageWeight = new JLabel("Weight (0 - 100Kg):");
        sm = new SpinnerNumberModel(0, 0, 100, 0.1);
        //A JSpinner is an object that allows you to only enter in a range of numbers based off of a SpinnerNumberModel.
        //The reason why we chose a JSpinner for this data is because of the range feature and for the arrows that increment
        //the number by a stepSize. This seems to be more useful and intuitive.
        JSpinner weight = new JSpinner(sm);
        //The next three lines centers the text of the JSpinner
        //This creates a JComponent which is like an empty component of the JFrame and grabs the components of the weight JSpinner.
        JComponent editor = weight.getEditor();
        //This casts the default components of a default JObject (eg. JLabel) onto the original JComponent that contained all the components of the
        //weight JSpinner. The "spinnerEditor" now has the default components that a normal JObject would have.
        JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
        //Now that the "spinnerEditor" has the default components that a normal JObject would have, it now has the ability to set the
        //text horizontally, which would not normally be allowed with the JSpinner's normal components.
        spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);

        informationEntry.add(packageWeight);
        informationEntry.add(weight);

        //This is the buttons panel that contains the view, enter, delete and clear buttons.
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        //The Dimension type was a good way to change the dimensions of a button in BoxLayout.
        Dimension buttonDimension = new Dimension(100, 35);

        btnView = new JButton("View");
        btnDelete = new JButton("Delete");
        btnEnter = new JButton("Enter");
        btnClear = new JButton("Clear");

        JButton[] buttonsArray = {btnView, btnDelete, btnEnter, btnClear};

        for (int i = 0; i < buttonsArray.length; i++) {
            buttonsArray[i].setMaximumSize(buttonDimension);
            buttonsArray[i].addActionListener(new applicationFunctions());
            buttonsPanel.add(buttonsArray[i]);
            //We don't want to add a horizontalStrut after the last button therefore we only want this if statement
            //to run until before the last run-through of the for loop.
            if (i < 3) {
                //A horizontal strut is a piece of blank space that will be added after each button.
                buttonsPanel.add(Box.createHorizontalStrut(10));
            }
        }

        //This panel is the total right side of the box image.
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
        gbc.ipady = 25;
        mainPanel.add(bottomText, gbc);

        setTitle("Package Sorter");
        setContentPane(mainPanel);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        //This part of the JFrame building part sets the location of the window to the middle of the screen.
        setLocationRelativeTo(null);
        //This evidently sets the icon image to the box that is used in the main applicationBuild window.
        setIconImage(box.getImage());
    }
}