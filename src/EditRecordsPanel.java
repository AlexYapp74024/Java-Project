import javax.swing.*;
import java.awt.*;

public class EditRecordsPanel extends JPanel {

    GridBagConstraints c = new GridBagConstraints();

    EditRecordsPanel() {
        setLayout(new GridLayout(3, 1));
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);

        String data[][] = { { "18/2/2022", "56", "160", "22.8", "36.5" }, { "27/2/2022", "78", "168", "24.3", "36.6" },
                { "14/3/2022", "42", "153", "20.2", "36.4" } };
        String column1[] = { "DATE", "WEIGHT (KG)", "HEIGHT (CM)", "BMI VALUE", "BODYTEMPERATURE (CELCIUS) " };

        JTable jt1 = new JTable(data, column1);
        jt1.setRowHeight(15);

        JLabel jLabel3 = new JLabel("RECORDS", SwingConstants.CENTER);
        jLabel3.setFont(new Font("Serif", Font.BOLD, 35));

        JPanel editJPanel = new JPanel();
        editJPanel.setBackground(Color.LIGHT_GRAY);
        editJPanel.setLayout(new GridBagLayout());

        JButton addRecord = new JButton("Add a record");
        c.ipady = 15; // make this component tall
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.CENTER; // bottom of space
        c.insets = new Insets(10, 80, 0, 20); // top padding
        editJPanel.add(addRecord, c);

        JButton editRecord = new JButton("Edit a record");
        c.ipady = 15; // make this component tall
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.CENTER; // bottom of space
        c.insets = new Insets(10, 20, 0, 20); // top padding
        editJPanel.add(editRecord, c);

        JButton deleteRecord = new JButton("Delete a record");
        c.ipady = 15; // make this component tall
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.CENTER; // bottom of space
        c.insets = new Insets(10, 20, 0, 80); // top padding
        editJPanel.add(deleteRecord, c);

        JTable jt4 = new JTable(data, column1);
        jt4.setRowHeight(15);
        JScrollPane record = new JScrollPane(jt4);

        JPanel jPanel3 = new JPanel();
        jPanel3.setBackground(Color.LIGHT_GRAY);
        jPanel3.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 80; // make this component tall
        c.weightx = 2.0;
        c.anchor = GridBagConstraints.CENTER; // bottom of space
        c.insets = new Insets(10, 80, 10, 80); // top padding
        jPanel3.add(record, c);

        add(jLabel3, BorderLayout.PAGE_START);
        add(editJPanel);
        add(jPanel3);
        setBorder(BorderFactory.createEmptyBorder(40, 20, 60, 20));
    }
}
