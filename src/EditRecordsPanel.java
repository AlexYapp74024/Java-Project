import javax.swing.*;
import java.awt.*;
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
           new addRecord(null);
            
            // if (!addRecord.cancel) {
            //     Records.list.add(new Record(
            //         UserProfile.data.getHeight(),
            //         ((Double) addRecord.weight.getValue()).floatValue(),
            //         ((Double) addRecord.bodyTemp.getValue()).floatValue(),
            //             addRecord.dateTime));
            //             ResetTableModel();
            // }
           
        });

        editBttn.addActionListener(e -> {

            if(table.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "No record is selected. Please select again.");
            }
            else
            {
                new updateRecord(null);
            }
        });


        deleteBttn.addActionListener(e -> {
            
            if(table.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "No record is selected. Please select again.");
            }
            else
            {
                new deleteRecord(null);
                if(table.getSelectedRow() != -1){
                    if (deleteRecord.cancel){
                        int row = table.getSelectedRow();
                        
                        if(table.getRowCount()>0){
                            DefaultTableModel model = (DefaultTableModel) table.getModel();
                            model.removeRow(row);

                            //Records.list.remove(row);
                            ResetTableModel();
                        }
                    }
                }
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
        //table.setModel(new DefaultTableModel(ConvertToTableDate(Records.list), tableHeader));
    }

    Object[][] ConvertToTableDate(ArrayList<Record> list) {
        var out = new Object[list.size()][5];

        for (int i = 0; i < list.size(); i++) {
            out[i] = new Object[] {
                    
                list.get(i).dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd  hh:mm a")),
                list.get(i).weight,
               list.get(i).height,
               list.get(i).Bmi().value,
               list.get(i).bodyTemp, };
                 
        }
        return out;
    }
}
