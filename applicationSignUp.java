import javax.swing.*;
import java.awt.*;

public class applicationSignUp extends JFrame {
    public static JFrame frame;
    public static JTextField txtUserSignUp;
    public static JPasswordField txtPassSignUp, txtRePassSignUp;
    public static JButton btnSignUp;
    public static Boolean signUpSuccess = false;

    public applicationSignUp() {
        frame = new JFrame();
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel mainPanel = new JPanel(new GridBagLayout());
        gbc.weightx = 100;
        gbc.weighty = 100;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblLogin = new JLabel("Email:");
        mainPanel.add(lblLogin, gbc);
        gbc.gridy = 1;
        JLabel lblPass = new JLabel("Password:");
        mainPanel.add(lblPass, gbc);
        gbc.gridy = 2;
        JLabel lblRePass = new JLabel("Re-type password:");
        mainPanel.add(lblRePass, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        txtUserSignUp = new JTextField(10);
        mainPanel.add(txtUserSignUp, gbc);
        gbc.gridy = 1;
        txtPassSignUp = new JPasswordField(10);
        mainPanel.add(txtPassSignUp, gbc);
        gbc.gridy = 2;
        txtRePassSignUp = new JPasswordField(10);
        mainPanel.add(txtRePassSignUp, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        Dimension buttonDimension = new Dimension(100, 35);
        btnSignUp = new JButton("Sign Up");
        btnSignUp.setMaximumSize(buttonDimension);
        btnSignUp.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSignUp.addActionListener(new applicationFunctions());
        buttonsPanel.add(btnSignUp);
        mainPanel.add(buttonsPanel, gbc);

        frame.setTitle("Signup");
        frame.setContentPane(mainPanel);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
