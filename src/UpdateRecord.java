import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
import java.time.*;

public class UpdateRecord extends JDialog implements CancelInterface {

    @Override
    public boolean CancelBttn()
    {
        return cancel;
    }

    static public JSpinner weight = new JSpinner(new SpinnerNumberModel(45, 20, 300, 0.1));
    static public JSpinner bodyTemp = new JSpinner(new SpinnerNumberModel(36, 25, 45, 0.1));
    public LocalDateTime dateTime = LocalDateTime.now().withSecond(0).withNano(0);

    UpdateRecord(JFrame parent) {

        super(parent, "Edit Record", true);
        setPreferredSize(new Dimension(800, 600));
        setLayout(new GridBagLayout());

        Dimension preferredSize = new Dimension(200, 30);
        gbc.insets = new Insets(5, 5, 30, 5);

        // Set location and size of weight
        weightLabel.setPreferredSize(preferredSize);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(weightLabel, gbc);

        weight.setPreferredSize(preferredSize);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(weight, gbc);

        // Set location and size of body temperature
        bodyTempLabel.setPreferredSize(preferredSize);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(bodyTempLabel, gbc);

        bodyTemp.setPreferredSize(preferredSize);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(bodyTemp, gbc);

        // show a dialog box when the record is edited
        // close the JDialog when editButton is selected
        editButton.addActionListener(e -> {
            cancel = false;

            JOptionPane.showMessageDialog(null, "Successfully updated.", "Update successfully!",
                    JOptionPane.INFORMATION_MESSAGE);

            dispose();
        });

        // Set location and size of editButton
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.ipadx = 100;
        add(editButton, gbc);

        // close the JDialog when cancelButton is selected
        cancelButton.addActionListener(e -> {
            dispose();
        });

        // Set location and size of cancelButton
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.ipadx = 100;
        add(cancelButton, gbc);

        setSize(580, 400);

        // center JDialog on the screen
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private boolean cancel = true;

    // create JLabel
    private JLabel weightLabel = new JLabel("Weight : ");
    private JLabel bodyTempLabel = new JLabel("Body Tempearture : ");
    private GridBagConstraints gbc = new GridBagConstraints();

    private JButton editButton = new JButton("Edit");
    private JButton cancelButton = new JButton("Cancel");
}
