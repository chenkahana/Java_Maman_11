import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Controller {

    private final int HEIGHT_AND_WIDTH_OF_SQUARE = 10;

    @FXML
    private Canvas canvas;

    /**
     * A method that paints the required matrix
     */
    public void initialize() {
        randomizeTiles();
    }

    /**
     * a method that create a matrix with 10% filled squares.
     * it first draws the lines that make the canvas a matrix.
     * then it calculates how many squares it needs to fill.
     * and finally it chooses a random point by coordinate and fills them.
     */
    public void randomizeTiles() {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        double canvasHeight = canvas.getHeight();
        double canvasWidth = canvas.getWidth();

        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        gc.setStroke(Color.BLACK);
        for (int i = 0; i <= canvasHeight; i += HEIGHT_AND_WIDTH_OF_SQUARE) {
            gc.strokeLine(i, 0, i, canvasWidth);
            gc.strokeLine(0, i, canvasHeight, i);
        }

        int tenPercentTotalNumOfSquares = (int) Math.pow(canvasHeight / HEIGHT_AND_WIDTH_OF_SQUARE, 2) / 100 * 10;
        for (int i = 0; i < tenPercentTotalNumOfSquares; i++) {
            int x = getRandomMultiplicationOf10(canvasWidth);
            int y = getRandomMultiplicationOf10(canvasHeight);

            gc.fillRect(x, y, HEIGHT_AND_WIDTH_OF_SQUARE, HEIGHT_AND_WIDTH_OF_SQUARE);
        }
    }

    /**
     *
     * @param sizeOfCanvas
     * @return an int that is a multiplication of 10 between 0 and the size of the canvas
     */
    private int getRandomMultiplicationOf10(double sizeOfCanvas) {
        return (int) (Math.random() * (sizeOfCanvas / HEIGHT_AND_WIDTH_OF_SQUARE)) * HEIGHT_AND_WIDTH_OF_SQUARE;
    }
}
