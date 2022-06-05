// Graph 
// Add, Delete, Edit Button
// User edit info

// Import statements.  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPanel extends JFrame {

    private int currCard = 1;
    private CardLayout cardLayout = new CardLayout();
    private GridBagLayout gridBagLayout = new GridBagLayout();

    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel cPanel = new JPanel();
    private JPanel btnPanel = new JPanel();

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public MainPanel() {
        setTitle("Health Care - Health Diary Application");
        setSize(720, 640);

        cPanel.setLayout(cardLayout);

        cPanel.add(new MainPagePanel(), "1");
        cPanel.add(new SummaryPanel_v2(), "2");
        cPanel.add(new EditRecordsPanel(), "3");
        cPanel.add(new UserDataPanel(), "4");

        // setting the layout
        btnPanel.setLayout(gridBagLayout);
        btnPanel.setBackground(Color.LIGHT_GRAY);

        if (shouldFill) {
            // natural height, maximum width
            gbc.fill = GridBagConstraints.HORIZONTAL;
        }

        addMainPageButton();
        addSummaryButton();
        addReportButton();
        addUserButton();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // using to get the content pane
        getContentPane().add(cPanel, BorderLayout.CENTER);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
    }

    private void addMainPageButton() {
        JButton mainPageBttn = new JButton("Main Page");
        if (shouldWeightX) {
            gbc.weightx = 2.0;
            gbc.weighty = 2.0;
        }
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.insets = new Insets(10, 10, 10, 0);
        gbc.ipady = 15;
        gbc.gridx = 0;
        gbc.gridy = 0;

        mainPageBttn.addActionListener(e -> {
            currCard = 1;
            cardLayout.show(cPanel, "" + (currCard));
        });

        btnPanel.add(mainPageBttn, gbc);
    }

    private void addSummaryButton() {
        JButton reportBttn = new JButton("Summary");

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 2.0;
        gbc.weighty = 2.0;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.ipady = 15;
        gbc.gridx = 1;
        gbc.gridy = 0;
        btnPanel.add(reportBttn, gbc);

        reportBttn.addActionListener(e -> {
            currCard = 2;
            cardLayout.show(cPanel, "" + (currCard));
        });
    }

    private void addReportButton() {
        JButton recordBttn = new JButton("Records");

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 15;
        gbc.weightx = 2.0;
        gbc.weighty = 2.0;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 2;
        gbc.gridy = 0;
        btnPanel.add(recordBttn, gbc);

        recordBttn.addActionListener(e -> {
            currCard = 3;
            cardLayout.show(cPanel, "" + (currCard));
        });
    }

    private void addUserButton() {

        JButton userBttn = new JButton("User");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 15;
        gbc.weightx = 2.0;
        gbc.weighty = 2.0;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.insets = new Insets(10, 0, 20, 10);
        gbc.gridx = 3;
        gbc.gridy = 0;
        btnPanel.add(userBttn, gbc);

        userBttn.addActionListener(e -> {
            currCard = 4;
            cardLayout.show(cPanel, "" + (currCard));
        });
        setVisible(true);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // using to get the content pane
        getContentPane().add(cPanel, BorderLayout.CENTER);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);

        // addWindowListener(new WindowAdapter(){
        //     public void windowClosed(WindowEvent e) {
        //         RecordDatastore.Save(Records.fullList);
        //     }

        //     public void windowClosing(WindowEvent e) {
        //         RecordDatastore.Save(Records.list);
        //     }
        // });
    } // End of constructor appLayout()
}