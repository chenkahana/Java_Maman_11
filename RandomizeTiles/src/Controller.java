import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Controller {

    private final int HEIGHT_AND_WIDTH_OF_CANVAS = 300;
    private final int HEIGHT_AND_WIDTH_OF_SQUARE = 10;

    @FXML
    private Canvas myCanvas;

    public void initialize() {
        randomizeTiles();
    }


    public void randomizeTiles() {
        int NUM_OF_FILLED_SQUARE = 10;

        GraphicsContext gc = myCanvas.getGraphicsContext2D();

        gc.clearRect(0, 0, HEIGHT_AND_WIDTH_OF_CANVAS, HEIGHT_AND_WIDTH_OF_CANVAS);

        gc.setStroke(Color.BLACK);
        for (int i = 0; i <= HEIGHT_AND_WIDTH_OF_CANVAS; i += HEIGHT_AND_WIDTH_OF_SQUARE) {
            gc.strokeLine(i, 0, i, HEIGHT_AND_WIDTH_OF_CANVAS);
            gc.strokeLine(0, i, HEIGHT_AND_WIDTH_OF_CANVAS, i);
        }

        for (int i = 0; i < NUM_OF_FILLED_SQUARE; i++) {
            int x = getRandomMultiplicationOf10();
            int y = getRandomMultiplicationOf10();

            gc.fillRect(x, y, HEIGHT_AND_WIDTH_OF_SQUARE, HEIGHT_AND_WIDTH_OF_SQUARE);
        }
    }

    private int getRandomMultiplicationOf10() {
        return (int) (Math.random() * (HEIGHT_AND_WIDTH_OF_CANVAS / HEIGHT_AND_WIDTH_OF_SQUARE)) * HEIGHT_AND_WIDTH_OF_SQUARE;
    }
}
