import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import java.awt.event.MouseEvent;

public class SliderTwo extends GraphicsProgram {
  private static final double BAR_SIZE = 100;
  private static final double HANDLE_RADIUS = 10;
  private static final double TEXT_MARGIN = 11;
  private GOval ball;
  private GLabel text;
  private double previousX = -1;
  private boolean isPressed = false;

  public void mouseDragged(MouseEvent e) {
    GObject curr = getElementAt(e.getX(), e.getY());
    if (isPressed) {
      if (previousX == -1) {
        previousX = e.getX();
      }
      double startX = (getWidth() - BAR_SIZE) / 2.0;
      double destination = ball.getX() + e.getX() - previousX;
      if (destination < startX - HANDLE_RADIUS) {
        destination = startX - HANDLE_RADIUS;
        ball.setLocation(destination, ball.getY());
      } else if (destination > startX + BAR_SIZE - HANDLE_RADIUS) {
        destination = startX + BAR_SIZE - HANDLE_RADIUS;
        ball.setLocation(destination, ball.getY());
      } else {
        ball.move(e.getX() - previousX, 0);
      }
      double distance = ball.getX() + HANDLE_RADIUS - startX;
      double percentage = distance * 100 / BAR_SIZE;
      remove(text);
      drawText("Volume: " + percentage);
      previousX = e.getX();
    }
  }

  public void mousePressed(MouseEvent e) {
    GObject curr = getElementAt(e.getX(), e.getY());
    if (curr != null && curr.equals(ball)) {
      isPressed = true;
    }
  }

  public void mouseReleased(MouseEvent e) {
    isPressed = false;
    previousX = -1;
  }

  public void run() {
    addMouseListeners();
    draw();
  }

  private void draw() {
    drawBar();
    drawBall();
    drawText("Volume: 0");
  }

  private void drawBar() {
    double startX = (getWidth() - BAR_SIZE) / 2.0;
    double startY = getHeight() / 2.0;
    GLine bar = new GLine(startX, startY, startX + BAR_SIZE, startY);
    add(bar);
  }

  private void drawBall() {
    double startX = (getWidth() - BAR_SIZE) / 2.0 - HANDLE_RADIUS;
    double startY = getHeight() / 2.0 - HANDLE_RADIUS;
    ball = new GOval(HANDLE_RADIUS * 2, HANDLE_RADIUS * 2);
    ball.setFilled(true);
    add(ball, startX, startY);
  }

  private void drawText(String str) {
    double startX = (getWidth() - BAR_SIZE) / 2.0 + TEXT_MARGIN;
    double startY = getHeight() / 2.0 - 3 * TEXT_MARGIN;
    text = new GLabel(str);
    add(text, startX, startY);
  }
}
