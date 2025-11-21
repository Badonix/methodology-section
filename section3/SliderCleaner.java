import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import java.awt.event.MouseEvent;

public class Slider extends GraphicsProgram {

  private static final double BAR_SIZE = 500;
  private static final double HANDLE_RADIUS = 10;
  private static final double TEXT_MARGIN = 11;

  private GOval handle;
  private GLabel text;
  private double previousClickX = -1;
  private boolean isPressed = false;

  public void run() { initSlider(); }

  private void initSlider() {
    addMouseListeners();
    drawBar();
    drawHandle();
    drawText("Volume: 0");
  }

  public void mousePressed(MouseEvent e) {
    GObject currObj = getElementAt(e.getX(), e.getY());
    isPressed = currObj != null && currObj.equals(handle);
  }

  public void mouseDragged(MouseEvent e) {
    if (!isPressed) {
      return;
    }
    if (previousClickX == -1) {
      previousClickX = e.getX();
    }
    double x = handle.getX() + e.getX() - previousClickX;
    double leftBorder = (getWidth() - BAR_SIZE) / 2.0;
    double rightBorder =
        BAR_SIZE - HANDLE_RADIUS * 2 + (getWidth() - BAR_SIZE) / 2.0;
    if (x < leftBorder)
      x = leftBorder;
    if (x > rightBorder)
      x = rightBorder;
    handle.setLocation(x, handle.getY());
    double percentage = (x - leftBorder) * 100 / (rightBorder - leftBorder);
    remove(text);
    drawText("Volume: " + (int)percentage);
    previousClickX = e.getX();
  }

  public void mouseReleased(MouseEvent e) {
    previousClickX = -1;
    isPressed = true;
  }

  private void drawText(String t) {
    text = new GLabel(t);
    double startX = (getWidth() - BAR_SIZE) / 2.0 + TEXT_MARGIN;
    double startY = (getHeight() / 2.0) - HANDLE_RADIUS - TEXT_MARGIN;
    add(text, startX, startY);
  }

  private void drawHandle() {
    handle = new GOval(HANDLE_RADIUS * 2, HANDLE_RADIUS * 2);
    double x = (getWidth() - BAR_SIZE) / 2.0;
    double y = (getHeight() / 2.0) - HANDLE_RADIUS;
    handle.setFilled(true);
    add(handle, x, y);
  }

  private void drawBar() {
    double y = getHeight() / 2.0;
    double startX = (getWidth() - BAR_SIZE) / 2.0;
    GLine bar = new GLine(startX, y, startX + BAR_SIZE, y);
    add(bar);
  }
}
