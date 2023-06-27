import java.util.Arrays;

public class DrawingBoard {
    private char[][] canvas;

    public DrawingBoard(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Invalid canvas dimensions");
        }
        canvas = new char[height][width];
        clearCanvas();
    }

    public void clearCanvas() {
        for (char[] row : canvas) {
            Arrays.fill(row, ' ');
        }
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        validateCoordinates(x1, y1);
        validateCoordinates(x2, y2);

        if (x1 == x2) {
            // Vertical line
            for (int y = y1; y <= y2; y++) {
                canvas[y][x1] = 'x';
            }
        } else if (y1 == y2) {
            // Horizontal line
            for (int x = x1; x <= x2; x++) {
                canvas[y1][x] = 'x';
            }
        } else {
            // Diagonal line, draw a rectangle instead
            drawRectangle(x1, y1, x2, y2);
        }
    }

    public void drawRectangle(int x1, int y1, int x2, int y2) {
        validateCoordinates(x1, y1);
        validateCoordinates(x2, y2);

        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                if (y == y1 || y == y2 || x == x1 || x == x2) {
                    canvas[y][x] = 'x';
                }
            }
        }
    }

    public void fill(int x, int y, char color) {
        validateCoordinates(x, y);
        char targetColor = canvas[y][x];

        if (color == targetColor) {
            return; // No need to fill if the target area is already the same color
        }

        floodFill(x, y, color, targetColor);
    }

    private void floodFill(int x, int y, char color, char targetColor) {
        if (x < 0 || y < 0 || x >= canvas[0].length || y >= canvas.length) {
            return; // Out of bounds
        }

        if (canvas[y][x] != targetColor) {
            return; // Stop filling if the color is different
        }

        canvas[y][x] = color; // Fill the current pixel

        // Recursive calls for the adjacent pixels
        floodFill(x - 1, y, color, targetColor);
        floodFill(x + 1, y, color, targetColor);
        floodFill(x, y - 1, color, targetColor);
        floodFill(x, y + 1, color, targetColor);
    }

    public String drawCanvas() {
        StringBuilder sb = new StringBuilder();

        // Top border
        sb.append('-'.repeat(canvas[0].length + 2)).append('\n');

        // Rows
        for (char[] row : canvas) {
            sb.append('|').append(row).append('|').append('\n');
        }

        // Bottom border
        sb.append('-'.repeat(canvas[0].length + 2)).append('\n');

        return sb.toString();
    }

    private void validateCoordinates(int x, int y) {
        if (x < 0 || y < 0 || x >= canvas[

