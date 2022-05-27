import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public class deleteRecord {

    // create JFrame and JTable
    JFrame frame = new JFrame("Delete The Record");
    JTable table = new JTable(); 

    DefaultTableModel model = new DefaultTableModel();

    JTable getDefaultTable() {

        // create a table model and set a Column Identifiers to this model 
        Object[] columns = {"Date","Weight","Height","BMI","Body Temperature"};
        
        model.setColumnIdentifiers(columns);
        
        // set the model to the table
        table.setModel(model);
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,15);
        table.setFont(font);
        table.setRowHeight(30);
        
        return table;
    }

    JLabel dateLabel = new JLabel("Date : ");
    JLabel weightLabel = new JLabel("Weight : ");
    JLabel bodyTempLabel = new JLabel("Body Tempearture : ");

    // create JTextFields
    JTextField dateNew = new JTextField();
    JTextField weightNew = new JTextField();
    JTextField bodyTempNew = new JTextField();

    // create an array of objects to set the row data
    Object[] row = new Object[5];

    deleteRecord(){
        
        getDefaultTable();

        // create JButtons
        JButton btnDelete = new JButton("Delete");
        JButton btnCancel = new JButton("Cancel");
        
        dateLabel.setBounds(20, 220, 120, 25);
        weightLabel.setBounds(20, 250, 120, 25);
        bodyTempLabel.setBounds(20, 280, 120, 25);
        
        dateNew.setBounds(180, 220, 100, 25);
        weightNew.setBounds(180, 250, 100, 25);
        bodyTempNew.setBounds(180, 280, 100, 25);
        
        btnDelete.setBounds(400, 220, 100, 25);
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
        frame.add(btnDelete);
        frame.add(btnCancel);
        
        // button remove row
        btnDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = { "Yes", "No" };

                int choice = JOptionPane.showOptionDialog(null, "Do you want to Delete data?", "Confirm to Delete?",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,options[1]);

                        if (choice == 0) // Confirm Delete = Yes
                        {
                            // i = the index of the selected row
                            int i = table.getSelectedRow();
                            if(i >= 0){
                                // remove a row from jtable
                                model.removeRow(i);
                            }
                        }
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                frame.dispose();
            }
        });
        
        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
