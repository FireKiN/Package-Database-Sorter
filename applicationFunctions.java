import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class applicationFunctions implements ActionListener {

	public void actionPerformed(ActionEvent a) {
		// The reason why I use the class name to locate the button instead of
		// creating an object from the class can be located here:
		// http://www.dgp.toronto.edu/~trendall/course/108/lectures/L03node2.html
		if (a.getSource() == applicationBuild.btnView) {
			if (applicationBuild.isThereOneEntry == false) {
				JOptionPane.showMessageDialog(null, "You must enter at least one package", "Error", 0);
			} else {
				new databaseBuild();
			}
			System.out.println("View Button Clicked");
		}

		if (a.getSource() == applicationBuild.btnEnter) {

			int packageID = 0;

			// This variable is going to be used to check if the information
			// entered is correct. There is going to be an if statement checking
			// to see if it is correct, and if it is, the program will proceed.
			boolean isInformationCorrect = true;
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
					// This exception is thrown to make sure that the catch
					// statement is run because the JOptionPane needs to be ran
					// and the background
					// needs to be changed to pink since the information is
					// wrong.
					throw new NumberFormatException();
				} else {
					isInformationCorrect = true;
				}
			} catch (NumberFormatException error) {
				isInformationCorrect = false;
				applicationBuild.txtPackageID.setBackground(Color.PINK);
				JOptionPane.showMessageDialog(null, "Please enter a valid package ID", "Error", 0);
			}

			if (applicationBuild.txtPackageName.getText().equals("")) {
				isInformationCorrect = false;
				applicationBuild.txtPackageName.setBackground(Color.PINK);
				JOptionPane.showMessageDialog(null, "Please enter a package name", "Error", 0);
			}

			if (isInformationCorrect == true) {
				databaseBuild.databaseIDNum++;
				applicationBuild.lblDatabaseIDNum.setText(Integer.toString(databaseBuild.databaseIDNum));

				DecimalFormat packageIDDF = new DecimalFormat("00000000");
				String formattedPackageID = packageIDDF.format(packageID);

				System.out.println("Database ID: " + databaseBuild.databaseIDNum + " | Package ID: "
						+ formattedPackageID + " Package Name: " + applicationBuild.txtPackageName.getText());

				try {
					File dataFile = new File("src\\data.txt");
					dataFile.createNewFile();

					String[] informationTypes = { Integer.toString(databaseBuild.databaseIDNum), formattedPackageID,
							applicationBuild.txtPackageName.getText() };
					BufferedWriter out = new BufferedWriter(new FileWriter(dataFile));

					for (int i = 0; i < informationTypes.length; i++) {
						out.write(informationTypes[i] + " | ");
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
			applicationBuild.sm.setValue(0);
		}
		if (a.getSource() == applicationBuild.btnDelete) {

		}
	}
}
