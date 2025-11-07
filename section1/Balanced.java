import acm.program.ConsoleProgram;

public class Balanced extends ConsoleProgram {
  public void run() {
    println(isBalanced("123321", 2) + " <- should be true");
    println(isBalanced("123123", 3) + " <- should be false");
    println(isBalanced("32112300", 3) + " <- should be true");
    println(isBalanced("111", 5) + " <- should be true");
  }

  private boolean isBalanced(String s, int n) {
    int sum = 0;
    for (int i = 0; (i < n && i < s.length()); i++) {
      int currSum = 0;
      for (int j = i; j < s.length(); j += n) {
        currSum += s.charAt(j) - '0';
      }
      if (i == 0) {
        sum = currSum;
      } else {
        if (currSum != sum) {
          return false;
        }
      }
    }
    return true;
  }
}
