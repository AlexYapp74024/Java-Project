import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;

public class MainPagePanel extends JPanel {
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDateTime startTime;
    LocalDateTime endTime;

    JLabel titleMain = new JLabel();
    JLabel bmiMain = new JLabel();
    JLabel bmiStatus = new JLabel();

    GraphPanel graphPanel;

    ArrayList<LocalDateTime> timeList;
    ArrayList<Float> valueList;

    String types[] = { "BMI", "Height", "Weight", "Body Temp" };

    JSpinner monthSpinner = new JSpinner();
    JSpinner yearSpinner = new JSpinner();
    JComboBox<String> typeComboBox = new JComboBox<>(types);

    boolean changing = false;

    public JPanel AddInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        monthSpinner.setValue(1);
        yearSpinner.setValue(2022);

        monthSpinner.addChangeListener(e -> {
            // Setting spinners via functions also trigger actionlisteners
            // Which is undesireable
            if (changing)
                return;
            changing = true;

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
            if (changing)
                return;

            changing = true;

            setMonth((Integer) monthSpinner.getValue(), (Integer) yearSpinner.getValue());
        });

        typeComboBox.addActionListener(e -> {
            UpdateData();
        });

        final int sizePadding = 50;
        panel.add(Box.createRigidArea(new Dimension(sizePadding, 0)));

        final int sizeSpacing = 5;
        panel.add(new JLabel("Month : "));
        panel.add(monthSpinner);
        panel.add(Box.createRigidArea(new Dimension(sizeSpacing, 0)));
        panel.add(new JLabel("Year : "));
        panel.add(yearSpinner);
        panel.add(Box.createRigidArea(new Dimension(sizeSpacing, 0)));
        panel.add(typeComboBox);

        panel.add(Box.createRigidArea(new Dimension(sizePadding, 0)));

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
        add(AddInputPanel());

        bmiStatus.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        graphPanel = new GraphPanel(Records.GetTimeList(), Records.GetHeightList());
        add(graphPanel);

        setThisMonth();
    }

    public void setThisMonth() {
        endTime = LocalDateTime.now();
        startTime = endTime.minusDays(30);
        UpdateData();
    }

    public void setMonth(int month, int year) {

        startTime = LocalDateTime.of(year, month, 1, 0, 0, 0);
        endTime = startTime.withDayOfMonth(startTime.getMonth().length(startTime.getYear() % 4 == 0));

        // Revert if there is no data in the time span chosen
        var minTime = Records.getMinDateTime();
        var maxTime = Records.getMaxDateTime();
        if (endTime.isBefore(minTime)) {
            startTime = minTime.withDayOfMonth(1);
            endTime = minTime.withDayOfMonth(minTime.getMonth().length(minTime.getYear() % 4 == 0));

            monthSpinner.setValue(startTime.getMonthValue());
            yearSpinner.setValue(startTime.getYear());
        } else if (startTime.isAfter(maxTime)) {
            startTime = maxTime.withDayOfMonth(1);
            endTime = maxTime.withDayOfMonth(maxTime.getMonth().length(maxTime.getYear() % 4 == 0));

            monthSpinner.setValue(startTime.getMonthValue());
            yearSpinner.setValue(startTime.getYear());
        }

        UpdateData();
    }

    private void UpdateData() {
        Records.SetByDateRange(startTime, endTime);
        titleMain.setText("RECORDS FROM " + dateTimeFormat.format(startTime) + " TO " + dateTimeFormat.format(endTime));

        remove(graphPanel);
        graphPanel = new GraphPanel(Records.GetTimeList(), SelectData());
        add(graphPanel);

        SetStatus();
        changing = false;

        // repaint() doesn't work here
        revalidate();
    }

    private ArrayList<Float> SelectData() {
        switch ((String) typeComboBox.getSelectedItem()) {
        case "Weight":
            return Records.GetWeightList();
        case "Height":
            return Records.GetHeightList();
        case "Body Temp":
            return Records.GetBodyTempList();
        }
        return Records.GetBMIValueList();
    }

    private void SetStatus() {
        float sum = 0f;
        for (var bmi : Records.GetBMIList()) {
            sum += bmi.value;
        }
        BMI averageBmi = new BMI(sum / Records.size());

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
