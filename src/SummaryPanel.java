import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SummaryPanel extends JPanel {

    GridBagConstraints c = new GridBagConstraints();

    SummaryPanel() {
        setLayout(new GridLayout(3, 1));
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);

        JLabel jLabel2 = new JLabel("SUMMARY REPORT", SwingConstants.CENTER);
        jLabel2.setFont(new Font("Serif", Font.BOLD, 30));

        add(jLabel2, BorderLayout.NORTH);

        String[] sortMethod = { "Date", "Weight", "BMI" };
        String[] sortOrder = { "Ascending", "Descending" };

        JLabel jLabelView = new JLabel("View Record By : ");

        JComboBox<String> jComboBox1 = new JComboBox<>(sortMethod);
        JComboBox<String> jComboBox2 = new JComboBox<>(sortOrder);

        JButton generatedBttn = new JButton("Generated");

        JLabel jLabelResult = new JLabel();
        jLabelResult.setLayout(new GridBagLayout());

        generatedBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sortResult = "View " + jComboBox1.getItemAt(jComboBox1.getSelectedIndex()) + " in "
                        + jComboBox2.getItemAt(jComboBox2.getSelectedIndex());
                jLabelResult.setText(sortResult);

                generatedBttn.addActionListener(this);
            }
        });

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30; // make this component tall
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.CENTER; // bottom of space
        c.insets = new Insets(10, 80, 10, 30); // top padding
        jLabelResult.add(jLabelView, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30; // make this component tall
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.CENTER; // bottom of space
        c.insets = new Insets(10, 20, 10, 20); // top padding
        jLabelResult.add(jComboBox1, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30; // make this component tall
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.anchor = GridBagConstraints.CENTER; // bottom of space
        c.insets = new Insets(10, 20, 10, 20); // top padding
        jLabelResult.add(jComboBox2, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30; // make this component tall
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.CENTER; // bottom of space
        c.insets = new Insets(10, 20, 10, 80); // top padding
        jLabelResult.add(generatedBttn, c);

        String data[][] = { { "18/2/2022", "56", "160", "22.8", "36.5" }, { "27/2/2022", "78", "168", "24.3", "36.6" },
                { "14/3/2022", "42", "153", "20.2", "36.4" } };
        String column1[] = { "DATE", "WEIGHT (KG)", "HEIGHT (CM)", "BMI VALUE", "BODYTEMPERATURE (CELCIUS) " };
        // String column2[] = { "WEIGHT (KG)", "HEIGHT (CM)", "BMI VALUE", "BODY
        // TEMPERATURE (CELCIUS)", "DATE" };
        // String column3[] = { "BMI VALUE", "WEIGHT (KG)", "HEIGHT (CM)", "BODY
        // TEMPERATURE (CELCIUS)", "DATE" };

        JTable jt1 = new JTable(data, column1);
        jt1.setRowHeight(15);
        // JTable jt2 = new JTable(data, column2);
        // jt2.setRowHeight(15);
        // JTable jt3 = new JTable(data, column3);
        // jt3.setRowHeight(15);

        JScrollPane reportD = new JScrollPane(jt1);

        JPanel reportPanel = new JPanel();
        // reportPanel.setLayout(new FlowLayout(10));
        reportPanel.setBackground(Color.LIGHT_GRAY);
        reportPanel.setOpaque(true);
        reportPanel.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 80; // make this component tall
        c.weightx = 2.0;
        c.anchor = GridBagConstraints.CENTER; // bottom of space
        c.insets = new Insets(10, 80, 10, 80); // top padding
        reportPanel.add(reportD, c);

        add(jLabelResult);
        add(reportPanel);
        setBorder(BorderFactory.createEmptyBorder(40, 20, 60, 20));
    }
}
