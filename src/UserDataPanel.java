import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

public class UserDataPanel extends JPanel {
    private class SizedText extends JLabel {
        SizedText(String s) {
            super(s);
            setFont(new Font("Serif", Font.BOLD, 20));
        }
    }

    JLabel title = new JLabel("User Settings");

    JPanel labelPanel = new JPanel();
    JPanel inputPanel = new JPanel();

    UserDataPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(50, 50, 50, 50));

        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(title);

        JPanel contentPanel = new JPanel();
        add(contentPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(labelPanel);
        contentPanel.add(inputPanel);

        var gridLayout = new GridLayout(5, 1); // Last row is left empty for spacing
        gridLayout.setVgap(20);

        labelPanel.setLayout(gridLayout);
        inputPanel.setLayout(gridLayout);

        labelPanel.add(new SizedText("Name:"));
        labelPanel.add(new SizedText("Blood Type:"));
        labelPanel.add(new SizedText("Height"));
        labelPanel.add(new SizedText("Health History"));

        inputPanel.add(new JTextField());
        inputPanel.add(new JComboBox<>());
        inputPanel.add(new JTextField());
        inputPanel.add(new JTextField());
    }
}