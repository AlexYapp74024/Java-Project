import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class GraphPanel<M extends Measurement> extends JPanel implements MouseMotionListener {

    public GraphPanel(List<LocalDateTime> X_value, List<M> Y_value) {
        this.x_value = X_value;
        this.y_value = Y_value;

        if (x_value.size() == 1) {
            this.x_value.add(x_value.get(0));
        }
        if (y_value.size() == 1) {
            this.y_value.add(y_value.get(0));
        }

        this.X_DIVISIONS = this.x_value.size() < MAX_X_DIVISIONS ? this.x_value.size() : MAX_X_DIVISIONS;
        this.Y_DIVISIONS = this.y_value.size() < MAX_Y_DIVISIONS ? this.y_value.size() : MAX_Y_DIVISIONS;

        addMouseMotionListener(this);
        add(hoverPanel);
    }

    private static final int PADDING = 14;
    private static final int LABEL_PADDING = 25;
    private static final int HATCH_MARK_LENGTH = 4;

    private static final Color LINE_COLOR = new Color(44, 102, 230, 180);
    private static final Color POINT_COLOR = new Color(100, 100, 100, 180);
    private static final Color GRID_COLOR = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);

    private static final int MIN_X = PADDING + LABEL_PADDING;
    private static final int MIN_Y = PADDING;

    private static final int MAX_X_DIVISIONS = 10;
    private static final int MAX_Y_DIVISIONS = 10;

    private final int X_DIVISIONS;
    private final int Y_DIVISIONS;

    private ArrayList<Point> graphPoints = new ArrayList<>();
    private HoverPanel hoverPanel = new HoverPanel();

    private List<LocalDateTime> x_value;
    private List<M> y_value;
    private Graphics2D g2;

    private final int MAX_X() {
        return MIN_X + X_AXIS_LENGTH();
    };

    private final int MAX_Y() {
        return MIN_Y + Y_AXIS_LENGTH();
    };

    private final int X_AXIS_LENGTH() {
        return getWidth() - 2 * PADDING - LABEL_PADDING;
    };

    private final int Y_AXIS_LENGTH() {
        return getHeight() - 2 * PADDING - LABEL_PADDING;
    };

    private double Y_value_division() {
        return (getMax_Y_Value() - getMin_Y_Value()) / Y_DIVISIONS;
    }

    private long X_value_division() {
        return TimeDifference(getMax_X_Value(), getMin_X_Value()) / X_DIVISIONS;
    }

    private long TimeDifference(Temporal t1, Temporal t2) {
        return ChronoUnit.SECONDS.between(t2, t1);
    }

    private LocalDateTime getMin_X_Value() {
        return Collections.min(x_value);
    }

    private LocalDateTime getMax_X_Value() {
        return Collections.max(x_value);
    }

    private float getMin_Y_Value() {
        return Collections.min(y_value).value;
    }

    private float getMax_Y_Value() {
        return Collections.max(y_value).value;
    }

    private void draw_white_background() {
        g2.setColor(Color.WHITE);
        g2.fillRect(MIN_X, MIN_Y, X_AXIS_LENGTH(), Y_AXIS_LENGTH());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        draw_white_background();

        Plot_X_Axis();
        Plot_Y_Axis();

        CalculateGraphPoints();
        PlotPoints();
    }

    private void Plot_X_Axis() {
        g2.setColor(Color.BLACK);

        final int xSpacing = X_AXIS_LENGTH() / X_DIVISIONS;
        final var dateFormat = DateTimeFormatter.ofPattern("dd/MM");
        for (int i = 0; i <= X_DIVISIONS; i++) {

            int x = i * xSpacing + MIN_X;

            // draw vertical grid lines
            g2.setColor(GRID_COLOR);
            g2.drawLine(x, MIN_Y, x, MAX_Y());
            g2.setColor(Color.BLACK);

            // draw grid label
            final String xLabel = getMin_X_Value().plusSeconds(X_value_division() * i).format(dateFormat);
            final FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(xLabel);
            g2.drawString(xLabel, x - labelWidth / 2, MAX_Y() + metrics.getHeight() + 3);

            // hatch marks
            g2.drawLine(x, MAX_Y(), x, MAX_Y() - HATCH_MARK_LENGTH);
        }

        // Draw X Axis Line
        g2.drawLine(MIN_X, MAX_Y(), MAX_X(), MAX_Y());
    }

    private void Plot_Y_Axis() {
        g2.setColor(Color.BLACK);

        final int ySpacing = Y_AXIS_LENGTH() / Y_DIVISIONS;
        final DecimalFormat df = new DecimalFormat("0.00");

        for (int i = 0; i < Y_DIVISIONS + 1; i++) {
            int y = MAX_Y() - i * ySpacing;

            // draw grid lines
            if (y_value.size() > 0) {
                g2.setColor(GRID_COLOR);
                g2.drawLine(MIN_X, y, MAX_X(), y);
                g2.setColor(Color.BLACK);

                String yLabel = df.format(getMin_Y_Value() + Y_value_division() * i).toString();
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, MIN_X - labelWidth - 5, y + (metrics.getHeight() / 2) - 3);
            }

            // Draw hatch marks
            g2.drawLine(MIN_X, y, HATCH_MARK_LENGTH + MIN_X, y);
        }

        // Draw Y Axis line
        g2.drawLine(MIN_X, MAX_Y(), MIN_X, MIN_Y);
    }

    private void CalculateGraphPoints() {
        final double yScale = Y_AXIS_LENGTH() / (getMax_Y_Value() - getMin_Y_Value());

        graphPoints = new ArrayList<>();

        for (int i = 0; i < y_value.size(); i++) {
            var t = TimeDifference(x_value.get(i), getMin_X_Value());
            var tspan = TimeDifference(getMax_X_Value(), getMin_X_Value());
            final var xDistance = (double) t / tspan;

            final int x = (int) (xDistance * X_AXIS_LENGTH() + MIN_X);
            final int y = (int) ((getMax_Y_Value() - y_value.get(i).value) * yScale + MIN_Y);
            graphPoints.add(new Point(x, y));
        }

        Collections.sort(graphPoints, (Point p1, Point p2) -> {
            if (p1.x < p2.x)
                return -1;
            if (p1.x == p2.x)
                return -1;
            return 1;
        });
    }

    private void PlotPoints() {
        // cache old stroke to switch back later
        final Stroke oldStroke = g2.getStroke();

        g2.setColor(LINE_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(POINT_COLOR);
        final int pointWidth = 4;
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        DrawHoverPanel(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        DrawHoverPanel(e.getPoint());
    }

    private void DrawHoverPanel(Point point) {
        int pointIndex = IndexOfGraphPointNearCursur(point);
        if (pointIndex >= 0) {
            hoverPanel.setPos(point);
            hoverPanel.setXLabel(x_value.get(pointIndex).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            hoverPanel.setYLabel(y_value.get(pointIndex).withUnit());
            hoverPanel.setVisible(true);
            return;
        }
        hoverPanel.setVisible(false);
    }

    // return index of point that is near the cursur
    private int IndexOfGraphPointNearCursur(Point curPoint) {
        final float maxDistance = 5f;
        for (int i = 0; i < graphPoints.size(); i++) {
            var point = graphPoints.get(i);
            if (Math.abs(curPoint.x - point.x) <= maxDistance && Math.abs(curPoint.y - point.y) <= maxDistance) {
                return i;
            }
        }
        return -1;
    }

    private class HoverPanel extends JPanel {
        private static int width = 100;
        private static int height = 30;

        private Rectangle rect = new Rectangle(0, 0, width, height);
        private JLabel y_label = new JLabel();
        private JLabel x_label = new JLabel();

        HoverPanel() {
            setLayout(new GridLayout(2, 1));
            setBackground(new Color(0.1f, 0.1f, 0.1f));

            final var fontColor = new Color(1f, 1f, 1f);
            y_label.setForeground(fontColor);
            x_label.setForeground(fontColor);

            add(x_label);
            add(y_label);
        }

        public void setPos(Point pos) {
            this.rect.x = pos.x < width ? pos.x : pos.x - width;
            this.rect.y = pos.y < height ? pos.y : pos.y - height;
            setBounds(rect);
        }

        public void setXLabel(String s) {
            x_label.setText(s);
        }

        public void setYLabel(String s) {
            y_label.setText(s);
        }
    }
}