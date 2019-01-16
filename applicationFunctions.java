import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import javax.swing.*;
import java.util.regex.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class applicationFunctions implements ActionListener {

    static int packageID;
    static File dataFile = new File("src//data.txt");
    static File databaseIDTracker = new File("src\\id.txt");
    File loginInfo = new File("src\\login.txt");

    public void actionPerformed(ActionEvent a) {

        // The reason why I use the class name to locate the button instead of creating an object from the class can be located here:
        // http://www.dgp.toronto.edu/~trendall/course/108/lectures/L03node2.html
        if (a.getSource() == applicationBuild.btnView) {
            try {
                File dataFile = new File("src//data.txt");
                BufferedReader out = new BufferedReader(new FileReader(dataFile));
                String lineToCheck = out.readLine();
                if (lineToCheck != null) {
                    databaseBuild.isThereOneEntry = true;
                }
            } catch (IOException error) {
                System.out.print("Error in view button");
            }
            if (databaseBuild.isThereOneEntry == false) {
                JOptionPane.showMessageDialog(null, "You must enter at least one package", "Invalid Package Entry", 3);
            } else {
                new databaseBuild();
            }
        }

        if (a.getSource() == applicationBuild.btnEnter) {
            // This variable is going to be used to check if the information entered is correct. There is going to be an if statement checking to see if it is
            // correct, and if it is, the program will proceed.
            boolean isInformationCorrect = true;
            boolean noInvalidEntries = true;
            try {
                packageID = Integer.parseInt(applicationBuild.txtPackageID.getText());
                applicationBuild.txtPackageID.setBackground(Color.WHITE);
                if (packageID < 0 || packageID > 99999999) {
                    // The packageID needs to be an 8 digit number so if it is more than 8 digits or a number less than 0, the packageID is incorrect, Rendering
                    // the information also incorrect hence the next boolean.
                    isInformationCorrect = false;
                    noInvalidEntries = false;
                    // This exception is thrown to make sure that the catch statement is run because the JOptionPane needs to be ran and the background needs to be
                    // changed to pink since the information is wrong.
                    throw new NumberFormatException();
                } else {
                    isInformationCorrect = true;
                    noInvalidEntries = true;
                    if (applicationBuild.txtPackageName.getText().equals("")) {
                        isInformationCorrect = false;
                        noInvalidEntries = false;
                        applicationBuild.txtPackageName.setBackground(Color.PINK);
                        JOptionPane.showMessageDialog(null, "Please enter a package name", "Invalid Package Name", 3);
                    } else {
                        noInvalidEntries = true;
                        applicationBuild.txtPackageName.setBackground(Color.WHITE);
                    }
                }
            } catch (NumberFormatException error) {
                isInformationCorrect = false;
                noInvalidEntries = false;
                applicationBuild.txtPackageID.setBackground(Color.PINK);
                JOptionPane.showMessageDialog(null, "Please enter a valid package ID", "Invalid PackageID", 3);
            }

            DecimalFormat packageIDDF = new DecimalFormat("00000000");
            String formattedPackageID = packageIDDF.format(packageID);

            if (noInvalidEntries == true) {
                // Try catch to check if the ID is already used in the textfile This was placed here because the program needs to obtain the formatted package ID
                try {
                    String[] currentLineComponents;
                    BufferedReader br = new BufferedReader(new FileReader(dataFile));
                    String currentLine = br.readLine();

                    while (currentLine != null) {
                        currentLineComponents = currentLine.split("<>");
                        if (currentLineComponents[0].equals(formattedPackageID)) {
                            isInformationCorrect = false;
                            JOptionPane.showMessageDialog(null, "The following ID already exists!", "Existing ID", 3);
                            break;
                        } else {
                            isInformationCorrect = true;
                        }
                        currentLine = br.readLine();
                    }
                    br.close();
                } catch (IOException error) {
                    System.out.println("Unique ID error");
                }
            }

            if (isInformationCorrect == true) {
                JOptionPane.showMessageDialog(null, "Success!", "Data Entered", 1);
                databaseBuild.databaseIDNum++;
                applicationBuild.lblDatabaseIDNum.setText(Integer.toString(databaseBuild.databaseIDNum));
                databaseBuild.isThereOneEntry = true;

                String packageName = applicationBuild.txtPackageName.getText();
                String formattedPackageName = packageName.substring(0, 1).toUpperCase() + packageName.substring(1);
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(databaseIDTracker, false));
                    out.write(Integer.toString(databaseBuild.databaseIDNum));
                    out.close();
                } catch (IOException error) {
                    System.out.println("Could not create file");
                }

                try {
                    String[] informationTypes = {formattedPackageID, Integer.toString(databaseBuild.databaseIDNum), formattedPackageName, applicationBuild.formattedDate, applicationBuild.sm.getValue().toString()};

                    BufferedWriter out = new BufferedWriter(new FileWriter(dataFile, true));
                    for (int i = 0; i < informationTypes.length; i++) {
                        out.write(informationTypes[i] + "<>");
                    }
                    out.newLine();
                    out.close();
                } catch (IOException error) {
                    System.out.println("Could not create file");
                }
            }
        }

        // if the user presses the clear button run this. Resets all the textfields, turns them white and sets the weight to 0.0.
        if (a.getSource() == applicationBuild.btnClear) {
            applicationBuild.txtPackageID.setText("");
            applicationBuild.txtPackageID.setBackground(Color.WHITE);
            applicationBuild.txtPackageName.setText("");
            applicationBuild.txtPackageName.setBackground(Color.WHITE);
            applicationBuild.sm.setValue(0.0);
            applicationBuild.txtPackageID.requestFocus();
        }

        // if the user presses the delete button run this code.
        if (a.getSource() == applicationBuild.btnDelete) {
            // asks the user to enter the ID of the package they would like to remove from the database(data.txt).
            String deleteID = JOptionPane.showInputDialog(null, "Enter the ID of the package you would like to delete",
                    "Delete Package", 3);
            // this if statement is just to prevent an error if the user were to press either cancel or the close button. When the user presses cancel
            // or the close button without typing anything, deleteID seemed to equal null and caused an error when the next if statement tried to run.
            if (deleteID != null) {
                // if the user presses the ok button but there was no entry, tell them that their ID was not found.
                if (deleteID.equals("")) {
                    JOptionPane.showMessageDialog(null, "The selected ID was not found", "Deletion Failed", 3);
                } else {
                    // this creates a temporary txt file that is going to hold all the information from data.txt while we look for the ID to get rid of.
                    // through this try statement, we read each line of data.txt and as long as what is read does not match the ID the user wanted to delete,
                    // write it to the temporary txt file. At the end we write the contents of tempData.txt back to data.txt. At this point, whether an ID was
                    // actually deleted is determined.
                    File tempFile = new File("src\\tempData.txt");
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(dataFile));
                        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

                        String[] currentLineComponents;
                        String currentLine = br.readLine();

                        // this boolean is just used to check if the ID is never found so that the program knows which JOptionPane to display after
                        // it is done searching through the program.
                        boolean noIDFound = true;

                        while (currentLine != null) {
                            currentLineComponents = currentLine.split("<>");
                            if(currentLineComponents[0].equals(deleteID)) {
                                // set the boolean mentioned above to false if the ID in the txt file matches the ID the user wanted to delete.
                                noIDFound = false;
                            }
                            if (!currentLineComponents[0].equals(deleteID)) {
                                bw.write(currentLine);
                                bw.newLine();
                            }
                            currentLine = br.readLine();
                        }
                        if (noIDFound == false) {
                            JOptionPane.showMessageDialog(null, "Success!", "Deletion Completed", 1);
                        } else {
                            JOptionPane.showMessageDialog(null, "The selected ID was not found", "Deletion Failed", 3);
                        }
                        br.close();
                        bw.close();
                    } catch (IOException error) {
                        System.out.print("Error with deleting.");
                    }
                    try {
                        dataFile.delete();
                        dataFile = new File("src\\data.txt");
                        BufferedReader br = new BufferedReader(new FileReader(tempFile));
                        BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile));
                        String currentLine = br.readLine();
                        while (currentLine != null) {
                            bw.write(currentLine);
                            bw.newLine();
                            currentLine = br.readLine();
                        }
                        br.close();
                        bw.close();
                    } catch (IOException error) {
                        System.out.println("Error rewriting the file.");
                    }
                }
            }
        }

        // if the user hits the login button at the beginning of the program run this code.
        if (a.getSource() == applicationLogin.btnLogin) {
            // this try statement will read through a txt file named id.txt that holds all the usernames and passwords of the people that
            // signed up for our package sorter.
            try {
                BufferedReader br = new BufferedReader(new FileReader(loginInfo));

                String[] currentLineComponents;
                String currentLine = br.readLine();

                // boolean that will be turned to true if the username and password the user entered matches any of the username and passwords in id.txt.
                Boolean correctLogin = false;

                while (currentLine != null) {
                    currentLineComponents = currentLine.split("<>");
                    if (applicationLogin.txtUser.getText().equals(currentLineComponents[0]) && new String(applicationLogin.txtPass.getPassword())
                            .equals(currentLineComponents[1])) {
                        correctLogin = true;
                        break;
                    }
                    currentLine = br.readLine();
                }
                if (correctLogin == false) {
                    JOptionPane.showMessageDialog(null, "Your username or password is incorrect. Please try again.", "Login Failed", 0);
                } else if (correctLogin) {
                    // if the boolean mentioned above returns true, start a new instance of the class applicationBuild that hold our main program.
                    // also, dispose of the login frame as the user has entered the correct information required to access the program.
                    new applicationBuild();
                    applicationLogin.frame.dispose();
                }
                br.close();
            } catch (IOException error) {
                System.out.println("Error reading login files.");
            }
        }

        // if the user presses the sign up button, create a new instance of the applicationSignUp class. This is where the user is able to sign up
        // in order to gain access to the main program.
        if (a.getSource() == applicationLogin.btnSignUp) {
            new applicationSignUp();
        }

        // if the user attempts to sign up run this code.
        if (a.getSource() == applicationSignUp.btnSignUp) {
            // regex to determine if an email and password is valid.
            String userPattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            String passPattern = "^[a-z0-9._]+$";

            // creates the pattern that will be used to check if the username and password the user attempts to use to sign up is valid.
            Pattern patternUser = Pattern.compile(userPattern);
            Pattern patternPass = Pattern.compile(passPattern);

            // creates the two matchers that will be used to match what the user entered with the pattern.
            Matcher matcherUser = patternUser.matcher(applicationSignUp.txtUserSignUp.getText());
            Matcher matcherPass = patternPass.matcher(new String(applicationSignUp.txtPassSignUp.getPassword()));

            // if the username matches the regex continue to the next if statement.
            if (matcherUser.matches()) {
                // if the password and re-typed password match continue to the next if statement.
                if (new String(applicationSignUp.txtPassSignUp.getPassword()).equals(new String(applicationSignUp.txtRePassSignUp.getPassword()))) {
                    // if the password matches the regex run this if statement.
                    if (matcherPass.matches()) {
                        // turns the boolean that determines a successful signup to true.
                        applicationSignUp.signUpSuccess = true;
                    } else {
                        applicationSignUp.signUpSuccess = false;
                        JOptionPane.showMessageDialog(null, "Invalid characters exist in password", "Invalid Input", 0);
                    }
                } else {
                    applicationSignUp.signUpSuccess = false;
                    JOptionPane.showMessageDialog(null, "Passwords do not match", "Password Match", 0);
                }
            } else {
                applicationSignUp.signUpSuccess = false;
                JOptionPane.showMessageDialog(null, "Invalid characters exist in email", "Invalid Input", 0);
            }

            // if the boolean mentioned above is true, send an email to them confirming their sign up.
            if (applicationSignUp.signUpSuccess) {

                // sets the to portion of the email to the email of the user.
                String to = applicationSignUp.txtUserSignUp.getText();
                // sets the email host. in this case we use gmail because it is free while most smtp servers require a monthly subscription.
                String emailHost = "smtp.gmail.com";
                // sets the from to the email of the sender (in this case it would be the program's "automated service") I created a new gmail for
                // the program since it looks more professional for a receiver to see this email instead of one of my personal emails for example.
                // I also had to add the password for the gmail so that the program can technically log into the email and send it out to the receiver.
                String from = "packagesortersignup";
                String fromPass = "ryanjaycpt";

                // sets properties so that the program knows who the host is, the username, password, ports, etc. This is all required to send an
                // email in Java.
                Properties properties = System.getProperties();
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", emailHost);
                properties.put("mail.smtp.user", from);
                properties.put("mail.smtp.password", fromPass);
                properties.put("mail.smtp.port", "587");
                properties.put("mail.smtp.auth", "true");

                // creates a new session where the program logs into the gmail.
                Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, fromPass);
                    }
                });

                // creates the message that will be sent.
                MimeMessage message = new MimeMessage(session);
                // sets all the components of a regular email using the strings provided above.
                try {
                    message.setFrom(new InternetAddress(from));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject("Confirmation of account creation with Package Sorter.");
                    message.setContent("<h1>hi</h1>, ", "text/html");
                    Transport.send(message, to, from);
                } catch (Exception m) {
                    System.out.println("Error sending email.");
                }

                // then after the email is successfully sent, write the username and password to a txt file called login.txt.
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(loginInfo, true));
                    bw.write(applicationSignUp.txtUserSignUp.getText() + "<>" + new String(applicationSignUp.txtPassSignUp.getPassword()) + "<>");
                    bw.newLine();
                    bw.close();
                    // once the username and password is successfully wrtten, dispose of the sign up frame as we do not need it anymore.
                    applicationSignUp.frame.dispose();
                } catch (IOException error) {
                    System.out.println("Error writing login files.");
                }
                JOptionPane.showMessageDialog(null, "A confirmation email has been sent to " + applicationSignUp.txtUserSignUp.getText() + ".", "Signed Up!", 1);
            }
        }
    }
}