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

  
public class appLayout extends JFrame   
{  
  
    // Initializing the value of currCard to 1 .  
    private int currCard = 1;  
    
    // Declaring of objects of the CardLayout class.  
    private CardLayout cObjl;  

    // Declaring of objects of the GridBagLayout class.  
    private GridBagLayout pane; 

    GridBagConstraints c = new GridBagConstraints();

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;



// constructor of the class  
public appLayout ()  
{  
  
    // Method to set the Title of the JFrame  
    setTitle("Health Care - Health Diary Application");  
    
    // Method to set the visibility of the JFrame  
    setSize(720, 640);  
    
    // Creating an Object of the "Jpanel" class  
    JPanel cPanel = new JPanel();  
  
    // Initializing of the object "cObjl" of the CardLayout class.  
    cObjl = new CardLayout();  
    
    // setting the layout  
    cPanel.setLayout(cObjl);  
    
    // Initializing the object "jPanel1" of the JPanel class.  
    JPanel infoMainPanel = new JPanel(); 
    infoMainPanel.setLayout(new GridLayout(4,1));
    infoMainPanel.setBackground(Color.LIGHT_GRAY);
    infoMainPanel.setOpaque(true);

    // Initializing the object "jl1" of the JLabel class.  
    JLabel jLabel1 = new JLabel("HEALTH CARE",SwingConstants.CENTER); 
    jLabel1.setFont(new Font("Serif", Font.BOLD, 35));


    JPanel jPanel1 = new JPanel();
    jPanel1.setBackground(Color.LIGHT_GRAY);
    jPanel1.setLayout(new GridLayout(3,1));

    JLabel titleMain = new JLabel("AVERAGE OF ALL RECORDS (13/01/2022 - 23/03/2022)",SwingConstants.CENTER);
    titleMain.setFont(new Font("Serif", Font.BOLD, 15));
    JLabel bmiMain = new JLabel("BMI             22.5",SwingConstants.CENTER);
    bmiMain.setFont(new Font("Serif", Font.BOLD, 30));
    JLabel bmiStatus = new JLabel("HEALTHY",SwingConstants.CENTER);
    bmiStatus.setFont(new Font("Serif", Font.BOLD, 25));

    jPanel1.add(titleMain);
    jPanel1.add(bmiMain);
    jPanel1.add(bmiStatus);
    bmiStatus.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    jPanel1.setBorder(BorderFactory.createEmptyBorder(10,70,20,70));

    JPanel bmiGraph = new JPanel();
    JLabel bmi = new JLabel("BMI Graph here");

    // bmiGraph.add(new GraphPanel());
    bmiGraph.add(bmi);
    bmiGraph.setBorder(BorderFactory.createEmptyBorder(30,150,10,150));


    // Adding JLabel "jLabel1" to the JPanel "jPanel1".  
    infoMainPanel.add(jLabel1); 
    infoMainPanel.add(jPanel1);
    infoMainPanel.add(bmiGraph);
    infoMainPanel.setBorder(BorderFactory.createEmptyBorder(0,90,10,90));



    JPanel jPaneReport = new JPanel();
    jPaneReport.setLayout(new GridLayout(3,1));
    jPaneReport.setBackground(Color.LIGHT_GRAY);
    jPaneReport.setOpaque(true);

    JLabel jLabel2 = new JLabel("SUMMARY REPORT",SwingConstants.CENTER);
    jLabel2.setFont(new Font("Serif", Font.BOLD, 30)); 

    jPaneReport.add(jLabel2,BorderLayout.NORTH); 


    String[] sortMethod = {"Date", "Weight", "BMI"};
    String[] sortOrder = {"Ascending", "Descending"};

    JLabel jLabelView = new JLabel("View Record By : ");

    JComboBox<String> jComboBox1 = new JComboBox<>(sortMethod);
    JComboBox<String> jComboBox2 = new JComboBox<>(sortOrder);

    JButton generatedBttn = new JButton("Generated");

    JLabel jLabelResult = new JLabel();
    jLabelResult.setLayout(new GridBagLayout());
   

generatedBttn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String sortResult = "View  " + jComboBox1.getItemAt(jComboBox1.getSelectedIndex()) + " in " + jComboBox2.getItemAt(jComboBox2.getSelectedIndex());
        jLabelResult.setText(sortResult);
        
        generatedBttn.addActionListener(this);
    }
});

    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 30;      //make this component tall
    c.weightx = 1.0;
    c.anchor = GridBagConstraints.CENTER; //bottom of space
    c.insets = new Insets(10,80,10,30);  //top padding
    jLabelResult.add(jLabelView, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 30;      //make this component tall
    c.weightx = 1.0;
    c.anchor = GridBagConstraints.CENTER; //bottom of space
    c.insets = new Insets(10,20,10,20);  //top padding
    jLabelResult.add(jComboBox1, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 30;      //make this component tall
    c.weightx = 1.0;
    c.weighty=0.0;
    c.anchor = GridBagConstraints.CENTER; //bottom of space
    c.insets = new Insets(10,20,10,20);  //top padding
    jLabelResult.add(jComboBox2, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 30;      //make this component tall
    c.weightx = 1.0;
    c.anchor = GridBagConstraints.CENTER; //bottom of space
    c.insets = new Insets(10,20,10,80);  //top padding
    jLabelResult.add(generatedBttn, c);


    String data[][]={ {"18/2/2022","56","160","22.8","36.5"},
                    {"27/2/2022","78","168","24.3","36.6"},    
                    {"14/3/2022","42","153","20.2","36.4"}};    
    String column1[]={"DATE","WEIGHT (KG)","HEIGHT (CM)", "BMI VALUE", "BODY TEMPERATURE (CELCIUS) "};
    String column2[]={"WEIGHT (KG)","HEIGHT (CM)", "BMI VALUE", "BODY TEMPERATURE (CELCIUS)","DATE"};
    String column3[]={"BMI VALUE","WEIGHT (KG)","HEIGHT (CM)",  "BODY TEMPERATURE (CELCIUS)","DATE"};

    JTable jt1=new JTable(data,column1);
    jt1.setRowHeight(15);  
    JTable jt2=new JTable(data,column2); 
    jt2.setRowHeight(15); 
    JTable jt3=new JTable(data,column3); 
    jt3.setRowHeight(15); 

    JScrollPane reportD =new JScrollPane(jt1);
    JScrollPane reportW =new JScrollPane(jt2);
    JScrollPane reportB =new JScrollPane(jt3);

    JPanel reportPanel = new JPanel();
    // reportPanel.setLayout(new FlowLayout(10));
    reportPanel.setBackground(Color.LIGHT_GRAY);
    reportPanel.setOpaque(true);
    reportPanel.setLayout(new GridBagLayout());

    String sort1 = jComboBox1.getItemAt(jComboBox1.getSelectedIndex());

    if(sort1=="Date"){
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 80;      //make this component tall
        c.weightx = 2.0;
        c.anchor = GridBagConstraints.CENTER; //bottom of space
        c.insets = new Insets(10,80,10,80);  //top padding
        reportPanel.add(reportD, c);
        }

    else if(sort1=="Weight"){
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 80;      //make this component tall
        c.weightx = 2.0;
        c.anchor = GridBagConstraints.CENTER; //bottom of space
        c.insets = new Insets(10,80,10,80);  //top padding
        reportPanel.add(reportW, c);
    }

    else if (sort1=="BMI") {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 80;      //make this component tall
        c.weightx = 2.0;
        c.anchor = GridBagConstraints.CENTER; //bottom of space
        c.insets = new Insets(10,80,10,80);  //top padding
        reportPanel.add(reportB, c);
    }
    
    jPaneReport.add(jLabelResult);
    jPaneReport.add(reportPanel);
    jPaneReport.setBorder(BorderFactory.createEmptyBorder(40,20,60,20));

  
    // Initializing the object "jPanel3" of the CardLayout class.  
    JPanel JpanelRecord = new JPanel(); 
    JpanelRecord.setLayout(new GridLayout(3,1));
    JpanelRecord.setBackground(Color.LIGHT_GRAY);
    JpanelRecord.setOpaque(true); 

    JLabel jLabel3 = new JLabel("RECORDS",SwingConstants.CENTER);
    jLabel3.setFont(new Font("Serif", Font.BOLD, 35));

    JPanel editJPanel = new JPanel();
    editJPanel.setBackground(Color.LIGHT_GRAY);
    editJPanel.setLayout(new GridBagLayout());

    JButton addRecord = new JButton("Add a record");
    c.ipady = 15;      //make this component tall
    c.weightx = 1.0;
    c.anchor = GridBagConstraints.CENTER; //bottom of space
    c.insets = new Insets(10,80,0,20);  //top padding
    editJPanel.add(addRecord, c);

    addRecord.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new addRecord(); 
        }
    });

    JButton editRecord = new JButton("Edit a record");
    c.ipady = 15;      //make this component tall
    c.weightx = 1.0;
    c.anchor = GridBagConstraints.CENTER; //bottom of space
    c.insets = new Insets(10,20,0,20);  //top padding
    editJPanel.add(editRecord, c);

    editRecord.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new updateRecord(); 
        }
    });

    JButton deleteRecord = new JButton("Delete a record");
    c.ipady = 15;      //make this component tall
    c.weightx = 1.0;
    c.anchor = GridBagConstraints.CENTER; //bottom of space
    c.insets = new Insets(10,20,0,80);  //top padding
    editJPanel.add(deleteRecord, c);

    deleteRecord.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new deleteRecord(); 
        }
    });
   
    JTable jt4=new JTable(data,column1); 
    jt4.setRowHeight(15);    
    JScrollPane record =new JScrollPane(jt4);          

    JPanel jPanel3 = new JPanel();
    jPanel3.setBackground(Color.LIGHT_GRAY);
    jPanel3.setLayout(new GridBagLayout());
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 80;      //make this component tall
    c.weightx = 2.0;
    c.anchor = GridBagConstraints.CENTER; //bottom of space
    c.insets = new Insets(10,80,10,80);  //top padding
    jPanel3.add(record, c);
    

    JpanelRecord.add(jLabel3,BorderLayout.PAGE_START);  
    JpanelRecord.add(editJPanel);
    JpanelRecord.add(jPanel3);
    JpanelRecord.setBorder(BorderFactory.createEmptyBorder(40,20,60,20));


    // Initializing the object "jPanel4" of the CardLayout class.  
    JPanel userPanel = new JPanel();  
    userPanel.setLayout(new BorderLayout());
    userPanel.setBackground(Color.LIGHT_GRAY);
    userPanel.setOpaque(true);

    JLabel jLabel4 = new JLabel("USER PROFILE",SwingConstants.CENTER);
    jLabel4.setFont(new Font("Serif", Font.BOLD, 35)); 

    JLabel userName = new JLabel("User Name : ");
    userName.setFont(new Font("Serif", Font.BOLD, 15));
    JLabel bloodType = new JLabel("Blood Type : ");
    bloodType.setFont(new Font("Serif", Font.BOLD, 15));
    JLabel height = new JLabel("Height : ");
    height.setFont(new Font("Serif", Font.BOLD, 15));
    JLabel healthHistory = new JLabel("Health History : ");
    healthHistory.setFont(new Font("Serif", Font.BOLD, 15));

    JTextField text1 = new JTextField(25);
    JTextField text2 = new JTextField(25);
    JTextField text3 = new JTextField(25);
    JTextField text4 = new JTextField(25);
    JTextField text5 = new JTextField(25);

    JButton editUser = new JButton("Edit Profile");

    userPanel.add(jLabel4,BorderLayout.PAGE_START);
    userPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
   


    JPanel jPanel4 = new JPanel();  
    jPanel4.setLayout(new GridBagLayout());
    jPanel4.setBackground(Color.LIGHT_GRAY);
    jPanel4.setOpaque(true);
    jPanel4.setBorder(BorderFactory.createEmptyBorder(40,200,90,200));
    

    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.CENTER; //center of space
    c.insets = new Insets(40,0,20,50);  //top padding
    jPanel4.add(userName, c);

    c.gridx = 1;
    c.gridy = 0;
    c.anchor = GridBagConstraints.CENTER; //center of space
    c.insets = new Insets(60,10,20,0);  //top padding
    jPanel4.add(text1,c);

    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 1;
    c.anchor = GridBagConstraints.CENTER; //center of space
    c.insets = new Insets(10,0,20,50);  //top padding
    jPanel4.add(bloodType,c);

    c.gridx = 1;
    c.gridy = 1;
    c.anchor = GridBagConstraints.CENTER; //center of space
    c.insets = new Insets(10,10,20,0);  //top padding
    jPanel4.add(text2,c);

    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 2;
    c.anchor = GridBagConstraints.CENTER; //center of space
    c.insets = new Insets(10,10,20,40);  //top padding
    jPanel4.add(height,c);

    c.gridx = 1;
    c.gridy = 2;
    c.anchor = GridBagConstraints.CENTER; //center of space
    c.insets = new Insets(10,10,20,0);  //top padding
    jPanel4.add(text3,c);

    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 3;
    c.anchor = GridBagConstraints.CENTER; //center of space
    c.insets = new Insets(10,0,10,40);  //top padding
    jPanel4.add(healthHistory,c);

    c.gridx = 1;
    c.gridy = 3;
    c.anchor = GridBagConstraints.CENTER; //center of space
    c.insets = new Insets(10,10,20,0);  //top padding
    jPanel4.add(text4,c);

    c.gridx = 1;
    c.gridy = 4;
    c.anchor = GridBagConstraints.CENTER; //center of space
    c.insets = new Insets(0,10,10,0);  //top padding
    jPanel4.add(text5,c);

    c.gridx = 1;
    c.gridy = 5;
    c.anchor = GridBagConstraints.CENTER; //center of space
    c.insets = new Insets(50,0,70,80);  //top padding
    jPanel4.add(editUser,c);

    userPanel.add(jPanel4,BorderLayout.CENTER);

  
    // Add the "jPanel1" on cPanel  
    cPanel.add(infoMainPanel, "1");  
    
    // Add the "jPanel2" on cPanel  
    cPanel.add(jPaneReport,"2");  
    // cPanel.add(jPaneReport,"2");
    
    // Add the "jPanel3" on cPanel  
    cPanel.add(JpanelRecord, "3");  
    
    // Add the "jPanel4" on cPanel  
    cPanel.add(userPanel, "4"); 


  
    // Creating an Object of the "JPanel" class  
    JPanel btnPanel = new JPanel();  

    // Initializing of the object "pane" of the GridBagLayout class. 
    pane = new GridBagLayout();  

    // setting the layout  
    btnPanel.setLayout(pane); 
    btnPanel.setBackground(Color.LIGHT_GRAY);


    if (shouldFill) {
        //natural height, maximum width
        c.fill = GridBagConstraints.HORIZONTAL;
    } 

    // Initializing the object "mainPageBttn" of the JButton class. 
    JButton mainPageBttn = new JButton("Main Page");  
        if (shouldWeightX) {
            c.weightx = 2.0;
            c.weighty = 2.0;
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,10,10,0);  //top padding
        c.ipady = 15;      //make this component tall
        c.gridx = 0;
        c.gridy = 0;
        btnPanel.add(mainPageBttn, c);
  
    // Initializing the object "reportBttn" of the JButton class.  
    JButton reportBttn = new JButton("Summary");  
    c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2.0;
        c.weighty = 2.0;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,10,0);  //top padding
        c.ipady = 15;      //make this component tall
        c.gridx = 1;
        c.gridy = 0;
        btnPanel.add(reportBttn, c);
    
  
    // Initializing the object "recordBttn" of JButton class.  
    JButton recordBttn = new JButton("Records"); 
    c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 15;      //make this component tall
        c.weightx = 2.0;
        c.weighty = 2.0;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,10,0);  //top padding
        c.gridx = 2;
        c.gridy = 0;
        btnPanel.add(recordBttn, c);
  
    // Initializing the object "userBttn" of the JButton class.  
    JButton userBttn = new JButton("User"); 
    c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 15;      //make this component tall
        c.weightx = 2.0;
        c.weighty = 2.0;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,10,10);  //top padding
        c.gridx = 3;
        c.gridy = 0;
        btnPanel.add(userBttn, c);
  
    // adding mainPageBttn in the ActionListener  
    mainPageBttn.addActionListener(new ActionListener()  
    {  
        @Override
        public void actionPerformed(ActionEvent ae)  
        {  
            // value of currCard is 1  
            currCard = 1;  
        
            cObjl.show(cPanel, "" + (currCard));  
            mainPageBttn.addActionListener(this);
        }  
    });  

    // adding reportBttn in the ActionListener  
    reportBttn.addActionListener(new ActionListener()  
    {  
        @Override
        public void actionPerformed(ActionEvent ae)  
        {  
            // value of currCard is 2 
            currCard = 2;  
        
            cObjl.show(cPanel, "" + (currCard));
            reportBttn.addActionListener(this);
        }   
    });
  
    // add recordBttn in ActionListener  
    recordBttn.addActionListener(new ActionListener()  
    {  
        @Override
        public void actionPerformed(ActionEvent ae)  
        {   
            // value of currCard is 3 
            currCard = 3;  
            // if(currCard==3)
            // {
            //     getContentPane().add(JpanelRecord, BorderLayout.CENTER);  
            // }
            
            cObjl.show(cPanel, "" + (currCard));  
            recordBttn.addActionListener(this);
        }  
    });  
  
    // add userBttn in ActionListener  
    userBttn.addActionListener(new ActionListener()  
    {  
        @Override
        public void actionPerformed(ActionEvent ae)  
        {  
             // value of currCard is 4  
            currCard = 4;  
            cObjl.show(cPanel, "" + (currCard));  
            userBttn.addActionListener(this);
        }  
    });  
  
    // using to get the content pane  
    getContentPane().add(cPanel, BorderLayout.CENTER); 
    getContentPane().add(btnPanel, BorderLayout.SOUTH);  
}  // End of constructor appLayout()


    // main method  
    public static void main(String argvs[])  
    {  
        // Creating an object of the appLayout class.  
        appLayout cll = new appLayout();  

        // method to set the default operation of the JFrame.  
        cll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        // method to set the visibility of the JFrame.  
        cll.setVisible(true);  
    }  
}  