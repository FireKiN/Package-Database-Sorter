import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import javax.swing.*;
import java.util.regex.*;

public class applicationFunctions implements ActionListener {

    static int packageID;
    static File dataFile = new File("src//data.txt");
    static File databaseIDTracker = new File("src\\id.txt");
    File loginInfo = new File("src\\login.txt");

    public void actionPerformed(ActionEvent a) {
        // The reason why I use the class name to locate the button instead of
        // creating an object from the class can be located here:
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
            // This variable is going to be used to check if the information
            // entered is correct. There is going to be an if statement checking
            // to see if it is correct, and if it is, the program will proceed.
            boolean isInformationCorrect = true;
            boolean noInvalidEntries = true;
            try {
                packageID = Integer.parseInt(applicationBuild.txtPackageID.getText());
                applicationBuild.txtPackageID.setBackground(Color.WHITE);
                if (packageID < 0 || packageID > 99999999) {
                    // The packageID needs to be an 8 digit number so if it is
                    // more than 8 digits or a number less than 0, the packageID
                    // is incorrect,
                    // Rendering the information also incorrect hence the next
                    // boolean.
                    isInformationCorrect = false;
                    noInvalidEntries = false;
                    // This exception is thrown to make sure that the catch
                    // statement is run because the JOptionPane needs to be ran
                    // and the background
                    // needs to be changed to pink since the information is
                    // wrong.
                    throw new NumberFormatException();
                } else {
                    isInformationCorrect = true;
                    noInvalidEntries = true;
                }
            } catch (NumberFormatException error) {
                isInformationCorrect = false;
                applicationBuild.txtPackageID.setBackground(Color.PINK);
                JOptionPane.showMessageDialog(null, "Please enter a valid package ID", "Invalid PackageID", 3);
            }

            if (applicationBuild.txtPackageName.getText().equals("")) {
                isInformationCorrect = false;
                noInvalidEntries = false;
                applicationBuild.txtPackageName.setBackground(Color.PINK);
                JOptionPane.showMessageDialog(null, "Please enter a package name", "Invalid Package Name", 3);
            } else {
                noInvalidEntries = true;
                applicationBuild.txtPackageName.setBackground(Color.WHITE);
            }

            DecimalFormat packageIDDF = new DecimalFormat("00000000");
            String formattedPackageID = packageIDDF.format(packageID);

            if (noInvalidEntries == true) {
                // Try catch to check if the ID is already used in the textfile
                // This was placed here because the program needs to obtain the
                // formatted package ID
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
                //DELETE LATER
                System.out.println("Database ID: " + databaseBuild.databaseIDNum + " | Package ID: " + formattedPackageID + " Package Name: " + applicationBuild.txtPackageName.getText());

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

        if (a.getSource() == applicationBuild.btnClear) {
            applicationBuild.txtPackageID.setText("");
            applicationBuild.txtPackageID.setBackground(Color.WHITE);
            applicationBuild.txtPackageName.setText("");
            applicationBuild.txtPackageName.setBackground(Color.WHITE);
            applicationBuild.sm.setValue(0.0);
        }

        if (a.getSource() == applicationBuild.btnDelete) {
            String deleteID = JOptionPane.showInputDialog(null, "Enter the ID of the package you would like to delete",
                    "Delete Package", 3);
            File tempFile = new File("src\\temp.txt");
            try {
                String[] currentLineComponents;
                BufferedReader br = new BufferedReader(new FileReader(dataFile));
                BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
                String currentLine = br.readLine();
                boolean noIDFound = false;

                while (currentLine != null) {
                    currentLineComponents = currentLine.split("<>");
                    if (!currentLineComponents[0].equals(deleteID)) {
                        bw.write(currentLine);
                        bw.newLine();
                        noIDFound = false;
                    } else {
                        noIDFound = true;
                    }
                    currentLine = br.readLine();
                }
                if (noIDFound == false) {
                    JOptionPane.showMessageDialog(null, "Success!", "Deletion Completed", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "The selected ID was not found", "Deletion Failed", 3);
                }
                System.out.println("Done searching");
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

        if(a.getSource() == applicationLogin.btnLogin) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(loginInfo));
                String currentLine = br.readLine();
                Boolean correctLogin = false;
                String[] currentLineComponents;
                while (currentLine != null) {
                    currentLineComponents = currentLine.split("<>");
                    if (applicationLogin.txtUser.getText().equals(currentLineComponents[0]) && new String(applicationLogin.txtPass.getPassword()).equals(currentLineComponents[1])) {
                        correctLogin = true;
                        break;
                    }
                    currentLine = br.readLine();
                }
                if(correctLogin == false) {
                    JOptionPane.showMessageDialog(null, "Your username or password is incorrect. Please try again.", "Login Failed", 3);
                } else if(correctLogin) {
                    new applicationBuild();
                }
                br.close();
                applicationLogin.frame.dispose();
            } catch (IOException error) {
                System.out.println("Error reading login files.");
            }
        }
        if(a.getSource() == applicationLogin.btnSignUp) {
            new applicationSignUp();
        }
        if(a.getSource() == applicationSignUp.btnSignUp) {
            //regex
            String userPattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            String passPattern = "^[a-z0-9._]+$";
            Pattern patternUser = Pattern.compile(userPattern);
            Pattern patternPass = Pattern.compile(passPattern);
            Matcher matcherUser = patternUser.matcher(applicationSignUp.txtUserSignUp.getText());
            Matcher matcherPass = patternPass.matcher(new String(applicationSignUp.txtPassSignUp.getPassword()));
            if(matcherUser.matches()) {
                if(new String(applicationSignUp.txtPassSignUp.getPassword()).equals(new String(applicationSignUp.txtRePassSignUp.getPassword()))) {
                    if(matcherPass.matches()) {
                        applicationSignUp.signUpSuccess = true;
                    } else {
                        applicationSignUp. signUpSuccess = false;
                        JOptionPane.showMessageDialog(null, "Invalid characters exist in password", "Invalid Input", 3);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match", "Password Match", 3);
                }
            } else {
                applicationSignUp.signUpSuccess = false;
                JOptionPane.showMessageDialog(null, "Invalid characters exist in email", "Invalid Input", 3);
            }

            if (applicationSignUp.signUpSuccess == true) {
                JOptionPane.showMessageDialog(null, "A confirmation email has been sent to "+ applicationSignUp.txtUserSignUp.getText() + ".", "Signed Up!", 1);
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(loginInfo, true));
                    bw.write(applicationSignUp.txtUserSignUp.getText() + "<>" + new String(applicationSignUp.txtPassSignUp.getPassword()) + "<>");
                    bw.newLine();
                    bw.close();
                    applicationSignUp.frame.dispose();
                } catch (IOException error) {
                    System.out.println("Error writing login files.");
                }
            }
        }
    }
}
