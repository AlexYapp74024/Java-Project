import javax.swing.JPanel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Component;
import javax.swing.*;
import java.awt.*;

//Size for summary panel can be bigger?
public class SummaryPanel_v2 extends JPanel {
    
    GridBagConstraints c = new GridBagConstraints();
    JTable jt1;
    

    SummaryPanel_v2() {
        setLayout(new GridLayout(3, 1));
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);

        JLabel jLabel2 = new JLabel("SUMMARY REPORT", SwingConstants.CENTER);
        jLabel2.setFont(new Font("Serif", Font.BOLD, 30));

        add(jLabel2, BorderLayout.NORTH);

        String data[][] = { { "18/2/2022", "56", "160", "22.8", "36.5" }, { "27/2/2022", "78", "168", "24.3", "36.6" },
                { "14/3/2022", "42", "153", "20.2", "36.4" }, { "21/2/2022", "68", "168", "24.3", "36.6" }, { "24/2/2022", "78", "161", "24.3", "36.6" }, { "28/2/2022", "88", "168", "24.3", "36.6" }, { "24/2/2022", "78", "161", "24.3", "36.6" }, { "28/2/2022", "98", "168", "24.3", "36.6" }, { "24/2/2022", "78", "161", "24.3", "36.6" }, { "28/2/2022", "88", "168", "24.3", "36.6" }, { "29/2/2022", "98", "171", "25.3", "32.6" }, { "28/2/2022", "99", "169", "24.2", "39.6" } };
        String column1[] = { "DATE", "WEIGHT (KG)", "HEIGHT (CM)", "BMI VALUE", "BODYTEMPERATURE (CELCIUS) " };

        jt1 = new JTable(data, column1){
            //Prevent editing the data in the table
            public boolean isCellEditable(int data, int column1){
                return false;
            }

            public Component prepareRenderer(TableCellRenderer r, int data, int column1)
            {
                Component c = super.prepareRenderer(r, data, column1);

                //Set white background color to ODD rows 
                if (data % 2 == 0)
                c.setBackground(Color.WHITE);

                //Set light gray background color to EVEN rows 
                else
                c.setBackground(new Color(230,230,230));

                return c;
            }
        };

        jt1.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jt1.getModel());
        jt1.setRowSorter(sorter);  //Sort by (String)date not working correctly, maybe works for LocalDateTime 
        jt1.setRowHeight(15);
        jt1.setPreferredScrollableViewportSize(jt1.getPreferredSize());
        jt1.setFillsViewportHeight(true);

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

        add(reportPanel);
        setBorder(BorderFactory.createEmptyBorder(40, 20, 60, 20));
    }
}
