import javax.swing.*;
import java.awt.*;

public class applicationLogin extends JFrame {
    public static JTextField txtUser;
    public static JPasswordField txtPass;
    public static JButton btnLogin, btnSignUp;
    public static JFrame frame;

    public applicationLogin() {
        frame = new JFrame();
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel mainPanel = new JPanel(new GridBagLayout());
        gbc.weightx = 100;
        gbc.weighty = 100;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblLogin = new JLabel("Username:");
        mainPanel.add(lblLogin, gbc);
        gbc.gridy = 1;
        JLabel lblPass = new JLabel("Password:");
        mainPanel.add(lblPass, gbc);
        gbc.gridy = 2;
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new applicationFunctions());
        mainPanel.add(btnLogin, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        txtUser = new JTextField(10);
        mainPanel.add(txtUser, gbc);
        gbc.gridy = 1;
        txtPass = new JPasswordField(10);
        mainPanel.add(txtPass, gbc);
        gbc.gridy = 2;
        btnSignUp = new JButton("Sign Up");
        btnSignUp.addActionListener(new applicationFunctions());
        mainPanel.add(btnSignUp, gbc);

        frame.setTitle("Login");
        frame.setContentPane(mainPanel);
        frame.setSize(250, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
