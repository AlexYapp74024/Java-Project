import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

public class UserDataPanel extends JPanel {

    UserDataPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(50, 50, 50, 50));
        setBackground(Color.LIGHT_GRAY);

        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(title);
        add(Box.createRigidArea(new Dimension(0, 50)));

        JPanel contentPanel = new JPanel();
        add(contentPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        height.setValue(UserProfile.profile.getHeight());

        contentPanel.add(labelPanel);
        contentPanel.add(inputPanel);
        labelPanel.setBackground(Color.LIGHT_GRAY);
        inputPanel.setBackground(Color.LIGHT_GRAY);

        var gridLayout = new GridLayout(5, 1); // Last row is left empty for spacing
        gridLayout.setVgap(20);

        labelPanel.setLayout(gridLayout);
        inputPanel.setLayout(gridLayout);

        labelPanel.add(new SizedText("Name:"));
        labelPanel.add(new SizedText("Blood Type:"));
        labelPanel.add(new SizedText("Height"));
        labelPanel.add(new SizedText("Health History"));

        inputPanel.add(name);
        inputPanel.add(bloodType);
        inputPanel.add(height);
        inputPanel.add(healthHistory);

        name.addActionListener(e -> {
            UserProfile.profile.setName(name.getText());
        });
        bloodType.addActionListener(e -> {
            var f = bloodType.getSelectedItem().toString();
            UserProfile.profile.setBloodType(f);
        });
        height.addChangeListener(e -> {
            UserProfile.profile.setHeight((Float) height.getValue());
        });
        healthHistory.addActionListener(e -> {
            UserProfile.profile.setMedicalHistory(healthHistory.getText());
        });
    }

    private class SizedText extends JLabel {
        SizedText(String s) {
            super(s);
            setFont(new Font("Serif", Font.BOLD, 20));
        }
    }

    private JLabel title = new JLabel("User Settings");

    private JPanel labelPanel = new JPanel();
    private JPanel inputPanel = new JPanel();

    private JTextField name = new JTextField(UserProfile.profile.getName());
    private JComboBox<String> bloodType = new JComboBox(BloodType.values().toArray());
    private JSpinner height = new JSpinner(new SpinnerNumberModel(0f, 0f, null, 0.01f));
    private JTextField healthHistory = new JTextField(UserProfile.profile.getMedicalHistory());
}