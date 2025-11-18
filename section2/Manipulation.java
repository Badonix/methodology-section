import acm.program.ConsoleProgram;

public class ManipulationTwo extends ConsoleProgram {
  public void run() {
    boolean firstTest =
        canManipulate("იყო არაბეთს როსტევან", "იყო რეთს როსტევან");
    boolean secondTest =
        canManipulate("იყasdო არაბეთს როსტევან", "იყო რეთს როსტევან");
    println("FIRST CASE: " + (firstTest ? "passed" : "failed"));
    println("SECOND CASE: " + (!secondTest ? "passed" : "failed"));
    //        canManipulate("Hello", "Hello");
  }

  // იყო არაბეთს როსტევან
  private boolean canManipulate(String a, String b) {
    for (int i = 0; i < a.length() - 1; i++) {
      String firstSubstring = a.substring(0, i + 1);
      String secondSubstring = a.substring(i + 1);
      for (int j = 0; j < firstSubstring.length(); j++) {
        String firstRemoved =
            removeChars(firstSubstring, firstSubstring.charAt(j));
        for (int k = 0; k < secondSubstring.length(); k++) {
          String secondRemoved =
              removeChars(secondSubstring, secondSubstring.charAt(k));
          String concatenated = firstRemoved + secondRemoved;
          if (concatenated.equals(b)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private String removeChars(String s, char a) {
    String result = "";
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != a) {
        result += s.charAt(i);
      }
    }
    return result;
  }
}
