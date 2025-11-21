import acm.program.ConsoleProgram;

public class SimilarTwo extends ConsoleProgram {
  public void run() {
    boolean firstResult = similar("bla", "lam");
    boolean secondResult = similar("bla", "alm");
    if (firstResult) {
      println("FIRST CASE: PASSED");
    } else {
      println("FIRST CASE: FAILED");
    }
    if (!secondResult) {
      println("SECOND CASE: PASSED");
    } else {
      println("SECOND CASE: FAILED");
    }
  }

  private boolean similar(String a, String b) {
    for (int i = 0; i < a.length(); i++) {
      for (char j = 'a'; j <= 'z'; j++) {
        String firstPart = a.substring(0, i);
        String secondPart = a.substring(i + 1);
        String newString = firstPart + j + secondPart;
        if ((newString + newString).contains(b))
          return true;
      }
    }
    return false;
  }
}
