import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class EditRecordsPanel extends JPanel {

    GridBagConstraints gbc = new GridBagConstraints();

    JScrollPane scrollPane = new JScrollPane();
    JTable table = new JTable();
    String[] tableHeader = { "DATE", "WEIGHT (KG)", "HEIGHT (CM)", "BMI VALUE", "BODY TEMPERATURE (CELCIUS) " };

    EditRecordsPanel() {
        setLayout(new GridLayout(3, 1));
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);

        JLabel jLabel3 = new JLabel("RECORDS", SwingConstants.CENTER);
        jLabel3.setFont(new Font("Serif", Font.BOLD, 35));

        JPanel editJPanel = new JPanel();
        editJPanel.setBackground(Color.LIGHT_GRAY);
        editJPanel.setLayout(new GridBagLayout());

        JButton addBttn = new JButton("Add a record");
        gbc.ipady = 15; // make this component tall
        gbc.ipadx = 150;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER; // center of space
        gbc.insets = new Insets(10, 80, 0, 20); // padding
        editJPanel.add(addBttn, gbc);

        JButton editBttn = new JButton("Edit a record");
        gbc.ipady = 15; // make this component tall
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER; // center of space
        gbc.insets = new Insets(10, 20, 0, 20); // padding
        editJPanel.add(editBttn, gbc);

        JButton deleteBttn = new JButton("Delete a record");
        gbc.ipady = 15; // make this component tall
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER; // center of space
        gbc.insets = new Insets(10, 20, 0, 80); // padding
        editJPanel.add(deleteBttn, gbc);

        addBttn.addActionListener(event -> {
            var addRecordDialog = new AddRecord(null);

            if (!addRecordDialog.cancel) {
                Records.Add(new Record(new Height(UserProfile.profile.getHeight()),
                        new Weight(((Double) AddRecord.weight.getValue()).floatValue()),
                        new Temperature(((Double) AddRecord.bodyTemp.getValue()).floatValue()),
                        addRecordDialog.dateTime));
                ResetTableModel();
            }

        });

        editBttn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(null, "No record is selected. Please select again.");
                return;
            }

            var updateRecordDialog = new UpdateRecord(null);

            if (row != -1 && !updateRecordDialog.cancel) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd  hh:mm a");
                String text = table.getValueAt(row, 0).toString();
                LocalDateTime datetime = LocalDateTime.parse(text, formatter);

                Records.Update(
                        new Record(new Height((float) table.getValueAt(row, 2)),
                                new Weight((float) table.getValueAt(row, 1)),
                                new Temperature((float) table.getValueAt(row, 4)), datetime),
                        new Record(new Height(UserProfile.profile.getHeight()),
                                new Weight((Float) UpdateRecord.weight.getValue()),
                                new Temperature((Float) UpdateRecord.bodyTemp.getValue()),
                                updateRecordDialog.dateTime));

                ResetTableModel();

            }
        });

        deleteBttn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(null, "No record is selected. Please select again.");
                return;
            }

            var deleteRecordDialog = new DeleteRecord(null);
            if (row != -1 && !deleteRecordDialog.cancel) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd  hh:mm a");
                String text = table.getValueAt(row, 0).toString();
                LocalDateTime datetime = LocalDateTime.parse(text, formatter);

                Records.Delete(new Record(new Height((float) table.getValueAt(row, 2)),
                        new Weight((float) table.getValueAt(row, 1)), new Temperature((float) table.getValueAt(row, 4)),
                        datetime));

                ResetTableModel();
            }
        });

        ResetTableModel();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridheight = 1;

        add(jLabel3, BorderLayout.PAGE_START);
        add(editJPanel);

        scrollPane.add(table.getTableHeader());
        scrollPane.add(table);
        scrollPane.setViewportView(table);
        add(scrollPane, gbc);

        setBorder(BorderFactory.createEmptyBorder(40, 20, 30, 20));
    }

    void ResetTableModel() {
        table.setModel(new DefaultTableModel(ConvertToTableDate(Records.CloneFullList()), tableHeader));
    }

    Object[][] ConvertToTableDate(ArrayList<Record> list) {
        var out = new Object[list.size()][5];

        for (int i = 0; i < list.size(); i++) {
            out[i] = new Object[] {

                    list.get(i).dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd  hh:mm a")),
                    list.get(i).weight.value, list.get(i).height.value, list.get(i).Bmi().value,
                    list.get(i).bodyTemp.value, };

        }
        return out;
    }
}
