import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
import java.time.*;

public class updateRecord extends JDialog {

     GridBagConstraints gbc = new GridBagConstraints();
     public static LocalDateTime dateTime = LocalDateTime.now();

    static boolean cancel = true;
    private final static float initialWeight = 45;
    private final static float initialBodyTemp = 36;

    JLabel weightLabel = new JLabel("Weight : ");
    JLabel bodyTempLabel = new JLabel("Body Tempearture : ");

    // create JSpinner
    static JSpinner weight = new JSpinner(new SpinnerNumberModel(initialWeight, 20,300, 0.1));
    static JSpinner bodyTemp = new JSpinner(new SpinnerNumberModel(initialBodyTemp, 25, 45, 0.1));

    JButton editButton = new JButton("Edit");
    JButton cancelButton = new JButton("Cancel");


    updateRecord(JFrame parent){
    
        super(parent, "Edit Record",true);
        setPreferredSize(new Dimension(800, 600));
        setLayout(new GridBagLayout());

        Dimension preferredSize = new Dimension(200, 30);
        gbc.insets = new Insets(5, 5, 30, 5);

        weightLabel.setPreferredSize(preferredSize);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(weightLabel, gbc);

        weight.setPreferredSize(preferredSize);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(weight, gbc);


        bodyTempLabel.setPreferredSize(preferredSize);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(bodyTempLabel, gbc);

        bodyTemp.setPreferredSize(preferredSize);

        gbc.gridx = 2;
        gbc.gridy = 2;
        add(bodyTemp, gbc);

        editButton.addActionListener(e -> {
            cancel = false;
            dispose();
        });

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.ipadx = 100;
        add(editButton, gbc);

        cancelButton.addActionListener(e -> {
            dispose();
        });

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.ipadx = 100;
        add(cancelButton, gbc);

    // button add row
    editButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
           
            JOptionPane.showMessageDialog(null, "Successfully updated.", "Update successfully!",
                JOptionPane.INFORMATION_MESSAGE);
        }
    });

    cancelButton.addActionListener(event -> {
        dispose();
    });

    setSize(580, 400);
    setLocationRelativeTo(null);    // center JDialog on the screen
    setVisible(true);

}
}