import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import java.awt.event.MouseEvent;

public class Ball extends GraphicsProgram {
  private static final double BALL_WIDTH = 50;
  private static final double BALL_HEIGHT = 50;
  private static final int DELAY = 6;
  private static final RandomGenerator random = RandomGenerator.getInstance();
  private GOval ball;
  private GLabel label;
  private int clickCount = 0;
  private double distanceCovered = 0;
  private boolean shouldLabelBeVisible = true;

  @Override
  public void run() {
    addMouseListeners();
    drawBall();
    gameLoop();
  }

  private void gameLoop() {
    double vx = 0, vy = 0;
    while (true) {
      if (shouldLabelBeVisible) {
        drawLabel();
      }
      moveBall(vx, vy);
      pause(DELAY);
      deleteLabel();
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    clickCount++;
    shouldLabelBeVisible = clickCount % 3 == 0;
  }

  private void moveBall(double vx, double vy) {
    if (clickCount % 2 == 1) {
      return;
    }
    vx = random.nextDouble(0, 5);
    vy = random.nextDouble(0, 5);
    boolean isVxNegative = random.nextBoolean();
    boolean isVyNegative = random.nextBoolean();
    if (isVxNegative) {
      vx *= -1;
    }
    if (isVyNegative) {
      vy *= -1;
    }
    ball.move(vx, vy);
    distanceCovered += Math.sqrt(vx * vx + vy * vy);
  }

  private void drawBall() {
    ball = new GOval(BALL_WIDTH, BALL_HEIGHT);
    ball.setFilled(true);
    double startX = (getWidth() - BALL_WIDTH) / 2.0;
    double startY = (getHeight() - BALL_HEIGHT) / 2.0;
    add(ball, startX, startY);
  }

  private void drawLabel() {
    label = new GLabel("Distance covered: " + distanceCovered);
    add(label, 10, getHeight() - label.getAscent());
  }

  private void deleteLabel() {
    if (label != null) {
      remove(label);
    }
  }
}
