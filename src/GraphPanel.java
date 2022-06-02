import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class GraphPanel extends JPanel implements MouseMotionListener {

    private static final int padding = 10;
    private static final int labelPadding = 25;
    private static final int hatchmarkLength = 4;

    private static final Color lineColor = new Color(44, 102, 230, 180);
    private static final Color pointColor = new Color(100, 100, 100, 180);
    private static final Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);

    private static final int minX = padding + labelPadding;
    private static final int minY = padding;

    private static final int maxXDivisions = 10;
    private static final int maxYDivisions = 10;

    private final int XDivisions;
    private final int YDivisions;

    private ArrayList<Point> graphPoints = new ArrayList<>();
    private HoverPanel hoverPanel = new HoverPanel();

    private final int maxX() {
        return minX + xAxisLength();
    };

    private final int maxY() {
        return minY + yAxisLength();
    };

    private final int xAxisLength() {
        return getWidth() - 2 * padding - labelPadding;
    };

    private final int yAxisLength() {
        return getHeight() - 2 * padding - labelPadding;
    };

    private double Y_value_division() {
        return (getMin_Y_Value() - getMax_Y_Value()) / YDivisions;
    }

    private long X_value_division() {
        return TimeDifference(getMin_X_Value(), getMax_X_Value()) / XDivisions;
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

    private double getMin_Y_Value() {
        return Collections.min(y_value);
    }

    private double getMax_Y_Value() {
        return Collections.max(y_value);
    }

    private List<LocalDateTime> x_value;
    private List<Float> y_value;
    private Graphics2D g2;

    public GraphPanel(List<LocalDateTime> X_value, List<Float> Y_value) {
        this.x_value = X_value;
        this.y_value = Y_value;

        this.XDivisions = X_value.size() < maxXDivisions ? X_value.size() : maxXDivisions;
        this.YDivisions = Y_value.size() < maxYDivisions ? Y_value.size() : maxYDivisions;

        addMouseMotionListener(this);
        add(hoverPanel);
    }

    private void draw_white_background() {
        g2.setColor(Color.WHITE);
        g2.fillRect(minX, minY, xAxisLength(), yAxisLength());
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

        final int xSpacing = xAxisLength() / XDivisions;
        final var dateFormat = DateTimeFormatter.ofPattern("dd/MM");
        for (int i = 0; i <= XDivisions; i++) {

            int x = i * xSpacing + minX;

            // draw vertical grid lines
            g2.setColor(gridColor);
            g2.drawLine(x, minY, x, maxY());
            g2.setColor(Color.BLACK);

            // draw grid label
            final String xLabel = getMin_X_Value().plusSeconds(X_value_division() * i).format(dateFormat);
            final FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(xLabel);
            g2.drawString(xLabel, x - labelWidth / 2, maxY() + metrics.getHeight() + 3);

            // hatch marks
            g2.drawLine(x, maxY(), x, maxY() - hatchmarkLength);
        }

        // Draw X Axis Line
        g2.drawLine(minX, maxY(), maxX(), maxY());
    }

    private void Plot_Y_Axis() {
        g2.setColor(Color.BLACK);

        final int ySpacing = yAxisLength() / YDivisions;
        final DecimalFormat df = new DecimalFormat("0.00");

        for (int i = 0; i < YDivisions + 1; i++) {
            int y = maxY() - i * ySpacing;

            // draw grid lines
            if (y_value.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(minX, y, maxX(), y);
                g2.setColor(Color.BLACK);

                String yLabel = df.format(getMin_Y_Value() + Y_value_division() * i / YDivisions).toString();
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, minX - labelWidth - 5, y + (metrics.getHeight() / 2) - 3);
            }

            // Draw hatch marks
            g2.drawLine(minX, y, hatchmarkLength + minX, y);
        }

        // Draw Y Axis line
        g2.drawLine(minX, maxY(), minX, minY);
    }

    private void CalculateGraphPoints() {
        final double yScale = yAxisLength() / (getMax_Y_Value() - getMin_Y_Value());

        graphPoints = new ArrayList<>();

        for (int i = 0; i < y_value.size(); i++) {
            var t = TimeDifference(x_value.get(i), getMin_X_Value());
            var tspan = TimeDifference(getMax_X_Value(), getMin_X_Value());
            final var xDistance = (double) t / tspan;

            final int x = (int) (xDistance * xAxisLength() + minX);
            final int y = (int) ((getMax_Y_Value() - y_value.get(i)) * yScale + minY);
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

        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
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
            hoverPanel.setYLabel(new DecimalFormat("0.00").format(y_value.get(pointIndex)));
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