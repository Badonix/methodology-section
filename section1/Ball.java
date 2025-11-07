import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import java.awt.event.MouseEvent;

public class Ball extends GraphicsProgram {
  private GOval ball = null;
  private static final int BALL_RADIUS = 10;
  private static final int DELAY = 10;
  private double vx, vy;
  private int clickCount = 0;
  private double distance = 0;
  private GLabel distanceLabel;

  RandomGenerator random = RandomGenerator.getInstance();

  public void run() {
    while (true) {
      gameInit();
      gameLoop();
      pause(DELAY);
    }
  }

  private void gameInit() {
    addMouseListeners();
    drawBall();
  }

  private void gameLoop() {
    if (clickCount % 2 == 0) {
      moveBall();
    }
    if (clickCount % 3 == 0) {
      if (distanceLabel != null) {
        remove(distanceLabel);
      }
      drawLabel();
    } else {
      if (distanceLabel != null) {
        remove(distanceLabel);
      }
    }
  }

  private void drawLabel() {
    distanceLabel = new GLabel("Distance Covered: " + distance);
    add(distanceLabel, 5, getHeight() - 2 * distanceLabel.getHeight());
  }

  public void mouseClicked(MouseEvent e) { clickCount++; }

  private void moveBall() {
    vx = random.nextDouble(0.0, 5.0);
    vy = random.nextDouble(0.0, 5.0);
    boolean isVxNegative = random.nextBoolean();
    boolean isVyNegative = random.nextBoolean();

    if (isVxNegative)
      vx *= -1;
    if (isVyNegative)
      vy *= -1;

    ball.move(vx, vy);
    distance += Math.sqrt(vx * vx + vy * vy);
  }

  private void drawBall() {
    ball = new GOval(BALL_RADIUS * 2, BALL_RADIUS * 2);
    ball.setFilled(true);
    double startX = getWidth() / 2.0 - BALL_RADIUS;
    double startY = getHeight() / 2.0 - BALL_RADIUS;
    add(ball, startX, startY);
  }
}
