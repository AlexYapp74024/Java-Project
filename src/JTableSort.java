import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class JTableSort extends AbstractTableModel {

    public JTableSort(ArrayList<Record> list) {
        this.list = list;

    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (list.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Record record = list.get(rowIndex);
        Object returnValue = null;

        switch (columnIndex) {
        case COLUMN_DATE:
            returnValue = record.getDateTime();
            break;
        case COLUMN_WEIGHT:
            returnValue = DFZERO.format(record.getWeight());
            break;
        case COLUMN_HEIGHT:
            returnValue = DFZERO.format(record.getHeight());
            break;
        case COLUMN_BMI:
            returnValue = DFZERO.format(record.Bmi().value);
            break;
        case COLUMN_BODYTEMP:
            returnValue = DFZERO.format(record.getBodyTemp());
            break;
        default:
            throw new IllegalArgumentException("Invalid column index");
        }

        return returnValue;
    }

    private String[] columnNames = { "DATE", "WEIGHT (KG)", "HEIGHT (CM)", "BMI VALUE", "BODYTEMPERATURE (CELCIUS)" };
    private ArrayList<Record> list = Records.CloneFullList();

    private static final DecimalFormat DFZERO = new DecimalFormat("0.00");
    private static final int COLUMN_DATE = 0;
    private static final int COLUMN_WEIGHT = 1;
    private static final int COLUMN_HEIGHT = 2;
    private static final int COLUMN_BMI = 3;
    private static final int COLUMN_BODYTEMP = 4;
}
