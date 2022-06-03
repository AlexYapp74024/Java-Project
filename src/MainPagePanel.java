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

    GraphPanel graphPanel;

    ArrayList<LocalDateTime> timeList;
    ArrayList<Float> valueList;

    JSpinner monthSpinner = new JSpinner();
    JSpinner yearSpinner = new JSpinner();
    boolean changingSpinner = false;

    public JPanel AddSpinnerPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        monthSpinner.setValue(1);
        yearSpinner.setValue(2022);

        monthSpinner.addChangeListener(e -> {
            if (changingSpinner)
                return;

            changingSpinner = true;

            if ((Integer) monthSpinner.getValue() < 1) {
                monthSpinner.setValue(12);
                yearSpinner.setValue(((Integer) yearSpinner.getValue()) - 1);
            } else if ((Integer) monthSpinner.getValue() > 12) {
                monthSpinner.setValue(1);
                yearSpinner.setValue(((Integer) yearSpinner.getValue()) + 1);
            }

            setMonth((Integer) monthSpinner.getValue(), (Integer) yearSpinner.getValue());
        });

        yearSpinner.addChangeListener(e -> {
            if (changingSpinner)
                return;

            changingSpinner = true;

            setMonth((Integer) monthSpinner.getValue(), (Integer) yearSpinner.getValue());
        });

        final int SizePadding = 50;
        panel.add(Box.createRigidArea(new Dimension(SizePadding, 0)));

        panel.add(new JLabel("Month : "));
        panel.add(monthSpinner);
        panel.add(new JLabel("Year : "));
        panel.add(yearSpinner);

        panel.add(Box.createRigidArea(new Dimension(SizePadding, 0)));

        panel.setMaximumSize(new Dimension(10000, 500));
        return panel;
    }

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
        add(AddSpinnerPanel());

        bmiStatus.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        graphPanel = new GraphPanel(records.GetTimeList(), records.GetHeightList());
        add(graphPanel);

        setThisMonth();
    }

    public void setThisMonth() {
        endTime = LocalDateTime.now();
        startTime = endTime.minusDays(30);
        UpdateDateRange();
    }

    public void setMonth(int month, int year) {

        startTime = LocalDateTime.of(year, month, 1, 0, 0, 0);
        endTime = startTime.withDayOfMonth(startTime.getMonth().length(startTime.getYear() % 4 == 0));

        // Revert if there is no data in the time span chosen
        if (endTime.isBefore(records.getMinDateTime())) {
            startTime = startTime.plusMonths(1);
            endTime = endTime.plusMonths(1);

            monthSpinner.setValue(startTime.getMonthValue());
            yearSpinner.setValue(startTime.getYear());
        } else if (startTime.isAfter(records.getMaxDateTime())) {
            startTime = startTime.minusMonths(1);
            endTime = endTime.minusMonths(1);

            monthSpinner.setValue(startTime.getMonthValue());
            yearSpinner.setValue(startTime.getYear());
        }

        UpdateDateRange();

        changingSpinner = false;
    }

    private void UpdateDateRange() {
        records.SetByDateRange(startTime, endTime);
        titleMain.setText("RECORDS FROM " + dateTimeFormat.format(startTime) + " TO " + dateTimeFormat.format(endTime));

        remove(graphPanel);
        graphPanel = new GraphPanel(records.GetTimeList(), records.GetHeightList());
        add(graphPanel);

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
