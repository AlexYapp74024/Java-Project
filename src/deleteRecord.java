import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.time.*;

public class deleteRecord extends JDialog {

     GridBagConstraints gbc = new GridBagConstraints();
     public static LocalDateTime dateTime = LocalDateTime.now();

    static boolean cancel = true;

    deleteRecord(JFrame parent){

        Object[] options = { "Yes", "No" };

        int choice = JOptionPane.showOptionDialog(null, "Do you want to DELETE data?", "Confirm to Delete?",
        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options,options[1]);

            if (choice == 0) // Confirm Delete = Yes
            {
                cancel = false;
                            
                JOptionPane.showMessageDialog(null,"Record deleted successfully.", "Delete successfully!",
                JOptionPane.INFORMATION_MESSAGE);

                dispose();
            }
            
            else
            {
                dispose();
            }
          //     // i = the index of the selected row
          //     int i = table.getSelectedRow();
          //     if(i >= 0){
          //         // remove a row from jtable
          //         model.removeRow(i);
          //     }
        
        }
                        
}
