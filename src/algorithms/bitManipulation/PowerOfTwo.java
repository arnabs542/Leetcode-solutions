package algorithms.bitManipulation;
/**
 * @author Qihao He
 * @date 09/24/2019
 * https://app.laicode.io/app/problem/74
 */
public class PowerOfTwo {
    public boolean isPowerOfTwo(int number) {
        // Write your solution here
        return number > 0 && (number & (number - 1)) == 0;
    }
}
