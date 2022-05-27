import javax.swing.*;
import java.awt.*;

public class UserDataPanel extends JPanel {

    GridBagConstraints c = new GridBagConstraints();

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

        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER; // center of space
        c.insets = new Insets(40, 0, 20, 50); // top padding
        jPanel4.add(userName, c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER; // center of space
        c.insets = new Insets(60, 10, 20, 0); // top padding
        jPanel4.add(text1, c);

        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; // center of space
        c.insets = new Insets(10, 0, 20, 50); // top padding
        jPanel4.add(bloodType, c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; // center of space
        c.insets = new Insets(10, 10, 20, 0); // top padding
        jPanel4.add(text2, c);

        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER; // center of space
        c.insets = new Insets(10, 10, 20, 40); // top padding
        jPanel4.add(height, c);

        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER; // center of space
        c.insets = new Insets(10, 10, 20, 0); // top padding
        jPanel4.add(text3, c);

        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER; // center of space
        c.insets = new Insets(10, 0, 10, 40); // top padding
        jPanel4.add(healthHistory, c);

        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER; // center of space
        c.insets = new Insets(10, 10, 20, 0); // top padding
        jPanel4.add(text4, c);

        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER; // center of space
        c.insets = new Insets(0, 10, 10, 0); // top padding
        jPanel4.add(text5, c);

        c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.CENTER; // center of space
        c.insets = new Insets(50, 0, 70, 80); // top padding
        jPanel4.add(editUser, c);

        add(jPanel4, BorderLayout.CENTER);
    }
}
