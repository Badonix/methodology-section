class Solution {
  public String bestHand(int[] ranks, char[] suits) {
    HashMap<Integer, Integer> ranksMap = new HashMap<>();
    HashMap<Character, Integer> suitsMap = new HashMap<>();
    for (int i = 0; i < ranks.length; i++) {
      ranksMap.put(ranks[i], ranksMap.getOrDefault(ranks[i], 0) + 1);
    }
    for (int i = 0; i < suits.length; i++) {
      suitsMap.put(suits[i], suitsMap.getOrDefault(suits[i], 0) + 1);
    }
    if (suitsMap.size() == 1) {
      return "Flush";
    }

    for (Integer key : ranksMap.keySet()) {
      if (ranksMap.get(key) >= 3) {
        return "Three of a Kind";
      }
    }
    for (Integer key : ranksMap.keySet()) {
      if (ranksMap.get(key) == 2) {
        return "Pair";
      }
    }

    return "High Card";
  }
}
