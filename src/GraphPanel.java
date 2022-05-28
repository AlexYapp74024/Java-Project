import java.awt.*;
import java.util.List;
import java.util.*;
import javax.swing.*;

public class GraphPanel extends JPanel {

    private static final int padding = 25;
    private static final int labelPadding = 25;
    private static final Color lineColor = new Color(44, 102, 230, 180);
    private static final Color pointColor = new Color(100, 100, 100, 180);
    private static final Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private static final int hatchmarkLength = 4;

    private static final int maxXDivisions = 10;
    private static final int numberYDivisions = 10;

    private final int minX = padding + labelPadding;
    private final int minY = padding;

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

    private List<Double> scores;
    private Graphics2D g2;

    public GraphPanel(List<Double> scores) {
        this.scores = scores;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(minX, minY, xAxisLength(), yAxisLength());

        Plot_X_Axis();
        Plot_Y_Axis();
        PlotPoints();
    }

    private void Plot_X_Axis() {
        g2.setColor(Color.BLACK);

        final int xSpacing = xAxisLength() / (scores.size() - 1);

        for (int i = 0; i < scores.size(); i++) {

            int x = i * xSpacing + minX;

            if ((i % ((int) ((scores.size() / maxXDivisions)) + 1)) == 0) {
                // draw vertical grid lines
                g2.setColor(gridColor);
                g2.drawLine(x, minY, x, maxY());
                g2.setColor(Color.BLACK);

                // draw grid label
                final String xLabel = i + "";
                final FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(xLabel);
                g2.drawString(xLabel, x - labelWidth / 2, maxY() + metrics.getHeight() + 3);
            }

            // hatch marks
            g2.drawLine(x, maxY(), x, maxY() - hatchmarkLength);
        }

        // Draw X Axis Line
        g2.drawLine(minX, maxY(), maxX(), maxY());
    }

    private void Plot_Y_Axis() {
        g2.setColor(Color.BLACK);

        final int ySpacing = yAxisLength() / numberYDivisions;

        for (int i = 0; i < numberYDivisions + 1; i++) {
            int y = maxY() - i * ySpacing;

            // draw grid lines
            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(minX, y, maxX(), y);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinScore()
                        + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";

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

    private void PlotPoints() {

        final double xScale = xAxisLength() / (scores.size() - 1);
        final double yScale = yAxisLength() / (getMaxScore() - getMinScore());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            final int x = (int) (i * xScale + minX);
            final int y = (int) ((getMaxScore() - scores.get(i)) * yScale + minY);
            graphPoints.add(new Point(x, y));
        }

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

    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
        invalidate();
        this.repaint();
    }

    public List<Double> getScores() {
        return scores;
    }
}