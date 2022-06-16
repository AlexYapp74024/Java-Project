import javax.swing.*;
import javax.swing.JFrame;

public class DeleteRecord extends JDialog implements CancelInterface{

    private boolean cancel = true;

    @Override
    public boolean CancelBttn()
    {
        return cancel;
    }

    DeleteRecord(JFrame parent) {

        // Option of the dialogBox
        Object[] options = { "Yes", "No" };

        // Confirmation Message for user to DELETE records
        int choice = JOptionPane.showOptionDialog(null, "Do you want to DELETE data?", "Confirm to Delete?",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

        // Confirm Delete = Yes
        if (choice == 0) {
            cancel = false;

            JOptionPane.showMessageDialog(null, "Record deleted successfully.", "Delete successfully!",
                    JOptionPane.INFORMATION_MESSAGE);

        }

        dispose();
    }
}
