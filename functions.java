import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class functions implements ActionListener {
    static applicationBuild app = new applicationBuild();

    public void actionPerformed(ActionEvent a) {
        String buttonName = a.getActionCommand();

        if (buttonName == "Create") {
            app.btnCreate.setBackground(Color.BLUE);
            System.out.println("test");
        }
   }
}