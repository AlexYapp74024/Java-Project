import javax.swing.*;
import java.awt.*;

public class MainPagePanel extends JLabel {
    MainPagePanel() {

        setLayout(new GridLayout(4, 1));
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);

        // Initializing the object "jl1" of the JLabel class.
        JLabel jLabel1 = new JLabel("HEALTH CARE", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Serif", Font.BOLD, 35));

        JPanel jPanel1 = new JPanel();
        jPanel1.setBackground(Color.LIGHT_GRAY);
        jPanel1.setLayout(new GridLayout(3, 1));

        JLabel titleMain = new JLabel("AVERAGE OF ALL RECORDS (13/01/2022 - 23/03/2022)", SwingConstants.CENTER);
        titleMain.setFont(new Font("Serif", Font.BOLD, 15));
        JLabel bmiMain = new JLabel("BMI             22.5", SwingConstants.CENTER);
        bmiMain.setFont(new Font("Serif", Font.BOLD, 30));
        JLabel bmiStatus = new JLabel("HEALTHY", SwingConstants.CENTER);
        bmiStatus.setFont(new Font("Serif", Font.BOLD, 25));

        jPanel1.add(titleMain);
        jPanel1.add(bmiMain);
        jPanel1.add(bmiStatus);
        bmiStatus.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setBorder(BorderFactory.createEmptyBorder(10, 70, 20, 70));

        JPanel bmiGraph = new JPanel();
        JLabel bmi = new JLabel("BMI Graph here");

        bmiGraph.add(bmi);
        bmiGraph.setBorder(BorderFactory.createEmptyBorder(30, 150, 10, 150));

        // Adding JLabel "jLabel1" to the JPanel "jPanel1".
        add(jLabel1);
        add(jPanel1);
        add(bmiGraph);
        setBorder(BorderFactory.createEmptyBorder(0, 90, 10, 90));
    }
}
