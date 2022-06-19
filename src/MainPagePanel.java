import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;

public class MainPagePanel extends JPanel {
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private JLabel title = new JLabel();
    private JLabel bmiMain = new JLabel();
    private JLabel bmiStatus = new JLabel();

    private GraphPanel<?> graphPanel;

    private String types[] = { "BMI", "Height", "Weight", "Body Temp" };

    private JSpinner monthSpinner = new JSpinner();
    private JSpinner yearSpinner = new JSpinner();
    private JComboBox<String> typeComboBox = new JComboBox<>(types);

    private boolean changing = false;

    public JPanel AddInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        monthSpinner.setValue(1);
        yearSpinner.setValue(2022);

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

    public void addlisteners() {
        monthSpinner.addChangeListener(e -> {
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
        var minTime = Records.GetMinDateTime();
        var maxTime = Records.GetMaxDateTime();
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
        title.setText("RECORDS FROM " + dateTimeFormat.format(startTime) + " TO " + dateTimeFormat.format(endTime));

        remove(graphPanel);
        graphPanel = new GraphPanel(Records.GetTimeList(), SelectData());
        add(graphPanel);

        SetStatus();
        changing = false;

        revalidate();
    }

    private ArrayList<? extends Measurement> SelectData() {
        switch ((String) typeComboBox.getSelectedItem()) {
        case "Weight":
            return Records.GetWeightList();
        case "Height":
            return Records.GetHeightList();
        case "Body Temp":
            return Records.GetBodyTempList();
        }
        return Records.GetBMIList();
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
            bmiStatus.setText("Healthy");
        } else if (averageBmi.IsOverweight()) {
            bmiStatus.setText("Overweight");
        } else {
            bmiStatus.setText("Obese");
        }
        bmiMain.setText("BMI " + averageBmi.value);
    }

    public MainPagePanel() {

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);

        title.setFont(new Font("Serif", Font.BOLD, 15));
        bmiMain.setFont(new Font("Serif", Font.BOLD, 30));
        bmiStatus.setFont(new Font("Serif", Font.BOLD, 25));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        bmiMain.setAlignmentX(Component.CENTER_ALIGNMENT);
        bmiStatus.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(title);
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(bmiMain);
        add(bmiStatus);
        add(AddInputPanel());

        bmiStatus.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        graphPanel = new GraphPanel(Records.GetTimeList(), Records.GetHeightList());
        add(graphPanel);

        setThisMonth();
        addlisteners();
    }

}
