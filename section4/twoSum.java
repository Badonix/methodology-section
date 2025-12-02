class Solution {
  public int[] twoSum(int[] nums, int target) {
    int[] answ = {0, 0};
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(target - nums[i])) {
        int secondIndex = map.get(target - nums[i]);
        answ[0] = secondIndex;
        answ[1] = i;
        return answ;
      }
      map.put(nums[i], i);
    }
    return answ;
  }
}
