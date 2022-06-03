import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;

public class MainPagePanel extends JPanel {

    Records records = new Records();
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDateTime startTime;
    LocalDateTime endTime;

    JLabel titleMain = new JLabel("RECORDS FROM 13/01/2022 TO 23/03/2022");
    JLabel bmiMain = new JLabel("BMI 22.5");
    JLabel bmiStatus = new JLabel();

    GraphPanel graphPanel = new GraphPanel(records.GetTimeList(), records.GetWeightList());

    ArrayList<LocalDateTime> timeList;
    ArrayList<Float> valueList;

    MainPagePanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);

        titleMain.setFont(new Font("Serif", Font.BOLD, 15));
        bmiMain.setFont(new Font("Serif", Font.BOLD, 30));
        bmiStatus.setFont(new Font("Serif", Font.BOLD, 25));

        titleMain.setAlignmentX(Component.CENTER_ALIGNMENT);
        bmiMain.setAlignmentX(Component.CENTER_ALIGNMENT);
        bmiStatus.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(titleMain);
        add(bmiMain);
        add(bmiStatus);
        // JSpinner monthsSpinner = new JSpinner();
        // add(monthsSpinner);
        add(graphPanel);

        bmiStatus.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        setThisMonth();
        graphPanel = new GraphPanel(records.GetTimeList(), records.GetHeightList());
    }

    public void setThisMonth() {
        endTime = LocalDateTime.now();
        startTime = endTime.minusDays(30);
        UpdateDateRange();
    }

    public void setMonth(int month, int year) {
        LocalDateTime initial = LocalDateTime.now();
        startTime = initial.withMonth(month);
        startTime = startTime.withYear(year);
        endTime = initial.withMonth(month);
        endTime = endTime.withYear(year);
        UpdateDateRange();
    }

    private void UpdateDateRange() {
        records.SetByDateRange(startTime, endTime);
        titleMain.setText("RECORDS FROM " + dateTimeFormat.format(startTime) + " TO " + dateTimeFormat.format(endTime));

        graphPanel.SetXData(records.GetTimeList());
        graphPanel.SetYData(records.GetWeightList());
        SetStatus();
    }

    private void SetStatus() {

        float sum = 0f;
        for (var bmi : records.GetBMIList()) {
            sum += bmi.value;
        }
        BMI averageBmi = new BMI(sum / records.size());

        if (averageBmi.IsUnderWeight()) {
            bmiStatus.setText("Underweight");
        } else if (averageBmi.IsHealthy()) {
            bmiStatus.setText("Helthy");
        } else if (averageBmi.IsOverweight()) {
            bmiStatus.setText("Overweight");
        } else {
            bmiStatus.setText("Obese");
        }
        bmiMain.setText("BMI " + averageBmi.value);
    }
}
