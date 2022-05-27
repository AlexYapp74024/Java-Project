// Graph 
//SortingTable L193

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

public class appLayout extends JFrame {

    private int currCard = 1;
    private CardLayout cObjl;
    private GridBagLayout pane;

    GridBagConstraints c = new GridBagConstraints();

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    // constructor of the class
    public appLayout() {

        setTitle("Health Care - Health Diary Application");
        setSize(720, 640);

        JPanel cPanel = new JPanel();
        cObjl = new CardLayout();
        cPanel.setLayout(cObjl);

        MainPagePanel infoMainPanel = new MainPagePanel();
        SummaryPanel jPaneReport = new SummaryPanel();
        EditRecordsPanel reportPanel = new EditRecordsPanel();
        UserDataPanel userPanel = new UserDataPanel();

        cPanel.add(infoMainPanel, "1");
        cPanel.add(jPaneReport, "2");
        cPanel.add(reportPanel, "3");
        cPanel.add(userPanel, "4");

        // Creating an Object of the "JPanel" class
        JPanel btnPanel = new JPanel();

        // Initializing of the object "pane" of the GridBagLayout class.
        pane = new GridBagLayout();

        // setting the layout
        btnPanel.setLayout(pane);
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

        // adding mainPageBttn in the ActionListener
        mainPageBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // value of currCard is 1
                currCard = 1;

                cObjl.show(cPanel, "" + (currCard));
                mainPageBttn.addActionListener(this);
            }
        });

        // adding reportBttn in the ActionListener
        reportBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // value of currCard is 2
                currCard = 2;

                cObjl.show(cPanel, "" + (currCard));
                reportBttn.addActionListener(this);
            }
        });

        // add recordBttn in ActionListener
        recordBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // value of currCard is 3
                currCard = 3;
                // if(currCard==3)
                // {
                // getContentPane().add(JpanelRecord, BorderLayout.CENTER);
                // }

                cObjl.show(cPanel, "" + (currCard));
                recordBttn.addActionListener(this);
            }
        });

        // add userBttn in ActionListener
        userBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // value of currCard is 4
                currCard = 4;
                cObjl.show(cPanel, "" + (currCard));
                userBttn.addActionListener(this);
            }
        });

        // using to get the content pane
        getContentPane().add(cPanel, BorderLayout.CENTER);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
    } // End of constructor appLayout()

}