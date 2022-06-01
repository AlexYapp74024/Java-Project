// Graph 
// Add, Delete, Edit Button
// User edit info

// Import statements.  
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JFrame {

    private int currCard = 1;
    private CardLayout cardLayout = new CardLayout();;
    private GridBagLayout gridBagLayout = new GridBagLayout();

    GridBagConstraints c = new GridBagConstraints();

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public MainPanel() {
        setTitle("Health Care - Health Diary Application");
        setSize(720, 640);

        JPanel cPanel = new JPanel();
        cPanel.setLayout(cardLayout);

        cPanel.add(new MainPagePanel(), "1");
        //Use 2nd Version of SummaryPanel
        cPanel.add(new SummaryPanel_v2(), "2");
        cPanel.add(new EditRecordsPanel(), "3");
        cPanel.add(new UserDataPanel(), "4");

        // Creating an Object of the "JPanel" class
        JPanel btnPanel = new JPanel();

        // Initializing of the object "pane" of the GridBagLayout class.
        gridBagLayout = new GridBagLayout();

        // setting the layout
        btnPanel.setLayout(gridBagLayout);
        btnPanel.setBackground(Color.LIGHT_GRAY);

        if (shouldFill) {
            // natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        // Initializing the object "mainPageBttn" of the JButton class.
        JButton mainPageBttn = new JButton("Main Page");
        if (shouldWeightX) {
            c.weightx = 2.0;
            c.weighty = 2.0;
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END; // bottom of space
        c.insets = new Insets(10, 10, 10, 0); // top padding
        c.ipady = 15; // make this component tall
        c.gridx = 0;
        c.gridy = 0;
        btnPanel.add(mainPageBttn, c);

        // Initializing the object "reportBttn" of the JButton class.
        JButton reportBttn = new JButton("Summary");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2.0;
        c.weighty = 2.0;
        c.anchor = GridBagConstraints.PAGE_END; // bottom of space
        c.insets = new Insets(10, 0, 10, 0); // top padding
        c.ipady = 15; // make this component tall
        c.gridx = 1;
        c.gridy = 0;
        btnPanel.add(reportBttn, c);

        // Initializing the object "recordBttn" of JButton class.
        JButton recordBttn = new JButton("Records");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 15; // make this component tall
        c.weightx = 2.0;
        c.weighty = 2.0;
        c.anchor = GridBagConstraints.PAGE_END; // bottom of space
        c.insets = new Insets(10, 0, 10, 0); // top padding
        c.gridx = 2;
        c.gridy = 0;
        btnPanel.add(recordBttn, c);

        // Initializing the object "userBttn" of the JButton class.
        JButton userBttn = new JButton("User");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 15; // make this component tall
        c.weightx = 2.0;
        c.weighty = 2.0;
        c.anchor = GridBagConstraints.PAGE_END; // bottom of space
        c.insets = new Insets(10, 0, 10, 10); // top padding
        c.gridx = 3;
        c.gridy = 0;
        btnPanel.add(userBttn, c);

        mainPageBttn.addActionListener(e -> {
            currCard = 1;
            cardLayout.show(cPanel, "" + (currCard));
        });

        reportBttn.addActionListener(e -> {
            currCard = 2;
            cardLayout.show(cPanel, "" + (currCard));
        });

        recordBttn.addActionListener(e -> {
            currCard = 3;
            cardLayout.show(cPanel, "" + (currCard));
        });

        userBttn.addActionListener(e -> {
            currCard = 4;
            cardLayout.show(cPanel, "" + (currCard));
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // using to get the content pane
        getContentPane().add(cPanel, BorderLayout.CENTER);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
    } // End of constructor appLayout()
}