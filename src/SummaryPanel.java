import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JPanel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JPanel {

    SummaryPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);
        setBorder(new EmptyBorder(50, 50, 50, 50));

        JLabel jLabel2 = new JLabel("SUMMARY REPORT", SwingConstants.CENTER);
        jLabel2.setFont(new Font("Serif", Font.BOLD, 30));
        jLabel2.setPreferredSize(new Dimension(100, 100));

        add(jLabel2, BorderLayout.NORTH);

        ArrayList<Record> list = Records.CloneFullList();
        TableModel tableModel = new JTableSort(list);

        JTable jt1 = new JTable(tableModel) {
            public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
                Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
                // Set color for alternate rows
                if (Index_row % 2 == 0) {
                    comp.setBackground(Color.white);
                } else {
                    comp.setBackground(new Color(230, 230, 230));
                }
                return comp;
            }
        };

        jt1.setAutoCreateRowSorter(true);

        add(new JScrollPane(jt1), BorderLayout.CENTER);

    }

}
