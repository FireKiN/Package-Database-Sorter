import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class applicationFunctions implements ActionListener {

    public void actionPerformed(ActionEvent a) {
        //The reason why I use the class name to locate the button instead of creating an object from the class can be located here: http://www.dgp.toronto.edu/~trendall/course/108/lectures/L03node2.html
        if (a.getSource() == applicationBuild.btnView) {
            if (applicationBuild.isThereOneEntry == false) {
                JOptionPane.showMessageDialog(null, "You must enter at least one package", "Error", 0);
            } else {
                new databaseBuild();
            }
            System.out.println("View Button Clicked");
        }
   }
}