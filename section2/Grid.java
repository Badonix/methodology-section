import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import java.awt.*;
import java.awt.event.MouseEvent;

public class GridTwo extends GraphicsProgram {
  private static final double ROWS = 8;
  private static final double COLUMNS = 8;

  private static final double SIZE = 50;

  private GRect olderCube = null, newerCube = null;

  public void run() {
    addMouseListeners();
    drawGrid();
  }

  public void mouseClicked(MouseEvent e) {
    GRect clicked = (GRect)getElementAt(e.getX(), e.getY());
    if (clicked.getFillColor().equals(Color.WHITE)) {
      if (olderCube == null) {
        clicked.setFillColor(Color.BLACK);
        olderCube = newerCube;
        newerCube = clicked;
        return;
      }
      olderCube.setFillColor(Color.WHITE);
      olderCube = newerCube;
      newerCube = clicked;
      clicked.setFillColor(Color.BLACK);
    } else {
      clicked.setFillColor(Color.WHITE);
      if (clicked.equals(newerCube)) {
        newerCube = olderCube;
      } else {
        olderCube = null;
      }
    }
  }

  private void drawGrid() {
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLUMNS; j++) {
        GRect rect = new GRect(SIZE, SIZE);
        rect.setFillColor(Color.WHITE);
        rect.setFilled(true);
        add(rect, i * SIZE, j * SIZE);
      }
    }
  }
}
