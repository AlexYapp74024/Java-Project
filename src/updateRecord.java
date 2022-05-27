import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public class updateRecord {

    JFrame frame = new JFrame("Edit The Record");
    JTable table = new JTable();

    DefaultTableModel model = new DefaultTableModel();

    JTable getDefaultTable() {

        // create a table model and set a Column Identifiers to this model
        Object[] columns = { "Date", "Weight", "Height", "BMI", "Body Temperature" };

        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);

        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", 1, 15);
        table.setFont(font);
        table.setRowHeight(30);

        return table;
    }

    // create JTextFields
    JTextField dateNew = new JTextField();
    JTextField weightNew = new JTextField();
    JTextField bodyTempNew = new JTextField();

    // create JButtons
    JButton btnEdit = new JButton("Edit");
    JButton btnCancel = new JButton("Cancel");

    // create an array of objects to set the row data
    Object[] row = new Object[5];

    updateRecord() {

        getDefaultTable();

        JLabel dateLabel = new JLabel("Date : ");
        JLabel weightLabel = new JLabel("Weight : ");
        JLabel bodyTempLabel = new JLabel("Body Tempearture : ");

        dateLabel.setBounds(20, 220, 120, 25);
        weightLabel.setBounds(20, 250, 120, 25);
        bodyTempLabel.setBounds(20, 280, 120, 25);

        dateNew.setBounds(180, 220, 100, 25);
        weightNew.setBounds(180, 250, 100, 25);
        bodyTempNew.setBounds(180, 280, 100, 25);

        btnEdit.setBounds(400, 220, 100, 25);
        btnCancel.setBounds(400, 265, 100, 25);

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);

        frame.setLayout(null);

        frame.add(pane);

        // add JTextFields to the jframe
        frame.add(dateLabel);
        frame.add(dateNew);
        frame.add(weightLabel);
        frame.add(weightNew);
        frame.add(bodyTempLabel);
        frame.add(bodyTempNew);

        // add JButtons to the jframe
        frame.add(btnEdit);
        frame.add(btnCancel);

        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                dateNew.setText(model.getValueAt(i, 0).toString());
                weightNew.setText(model.getValueAt(i, 1).toString());
                bodyTempNew.setText(model.getValueAt(i, 4).toString());
            }
        });

        // button update row
        btnEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                if (i >= 0) {
                    model.setValueAt(dateNew.getText(), i, 0);
                    model.setValueAt(weightNew.getText(), i, 1);
                    model.setValueAt(bodyTempNew.getText(), i, 4);
                }
                JOptionPane.showMessageDialog(frame, "Successfully updated.", "Update successfully!",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                frame.dispose();
            }
        });

        frame.setSize(900, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}