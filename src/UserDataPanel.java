import javax.swing.*;
import java.awt.*;

public class UserDataPanel extends JPanel {

    GridBagConstraints gbc = new GridBagConstraints();

    UserDataPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);

        JLabel jLabel4 = new JLabel("USER PROFILE", SwingConstants.CENTER);
        jLabel4.setFont(new Font("Serif", Font.BOLD, 35));

        JLabel userName = new JLabel("User Name : ");
        userName.setFont(new Font("Serif", Font.BOLD, 15));
        JLabel bloodType = new JLabel("Blood Type : ");
        bloodType.setFont(new Font("Serif", Font.BOLD, 15));
        JLabel height = new JLabel("Height : ");
        height.setFont(new Font("Serif", Font.BOLD, 15));
        JLabel healthHistory = new JLabel("Health History : ");
        healthHistory.setFont(new Font("Serif", Font.BOLD, 15));

        JTextField text1 = new JTextField(25);
        JTextField text2 = new JTextField(25);
        JTextField text3 = new JTextField(25);
        JTextField text4 = new JTextField(25);
        JTextField text5 = new JTextField(25);

        JButton editUser = new JButton("Edit Profile");

        add(jLabel4, BorderLayout.PAGE_START);
        setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout(new GridBagLayout());
        jPanel4.setBackground(Color.LIGHT_GRAY);
        jPanel4.setOpaque(true);
        jPanel4.setBorder(BorderFactory.createEmptyBorder(40, 200, 90, 200));

        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // center of space
        gbc.insets = new Insets(40, 0, 20, 50); // top padding
        jPanel4.add(userName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // center of space
        gbc.insets = new Insets(60, 10, 20, 0); // top padding
        jPanel4.add(text1, gbc);

        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER; // center of space
        gbc.insets = new Insets(10, 0, 20, 50); // top padding
        jPanel4.add(bloodType, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER; // center of space
        gbc.insets = new Insets(10, 10, 20, 0); // top padding
        jPanel4.add(text2, gbc);

        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER; // center of space
        gbc.insets = new Insets(10, 10, 20, 40); // top padding
        jPanel4.add(height, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER; // center of space
        gbc.insets = new Insets(10, 10, 20, 0); // top padding
        jPanel4.add(text3, gbc);

        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER; // center of space
        gbc.insets = new Insets(10, 0, 10, 40); // top padding
        jPanel4.add(healthHistory, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER; // center of space
        gbc.insets = new Insets(10, 10, 20, 0); // top padding
        jPanel4.add(text4, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER; // center of space
        gbc.insets = new Insets(0, 10, 10, 0); // top padding
        jPanel4.add(text5, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER; // center of space
        gbc.insets = new Insets(50, 0, 70, 80); // top padding
        jPanel4.add(editUser, gbc);

        add(jPanel4, BorderLayout.CENTER);
    }
}
