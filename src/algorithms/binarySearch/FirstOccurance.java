package algorithms.binarySearch;
/**
 * @author Qihao He
 * @date 09/24/2019
 * https://app.laicode.io/app/problem/15
 */
public class FirstOccurance {
  public int firstOccur(int[] array, int target) {
    // Write your solution here
    if (array == null || array.length == 0) {
      return -1;
    }
    int left = 0;
    int right = array.length - 1;
    while (left < right - 1) {
      int mid = left + (right - left) / 2;
      if (array[mid] >= target) {
        right = mid;
      } else {
        left = mid;
      }
    }
    if (array[left] == target) {
      return left;
    } else if (array[right] == target) {
      return right;
    }
    return -1;
  }
}
