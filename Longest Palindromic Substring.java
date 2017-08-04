/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"
*/

/*
O(n) solution which uses manacher's algorithm. The input string is converted into a easier to process version with dividers
represented by '_'. The input is processed left to right character by character. If the left and right of a letter or divider
are the same ASCII character the loop continues to check upcomming characters until the chain is broken. Manacher's algorithm uses
the property that palindromic sequences where the center character is towards the right must have mirroring sub palidromic sequences
that are equal to or greater than the left side's sub palidromic sequences. This allows the string's characers only need to be visited 
two times so the speed is O(2*n), or O(n).
*/
public class Solution {
    public String longestPalindrome(String s) {
        
        // Edge case
        if(s.length() == 0 || s == null)
            return "";
        
        // Pre-processing: turn "abababa" to "(_a_b_a_b_a_b_a_)"
        char[] T = new char[s.length() * 2 + 3];    // Proxy
        int[] P = new int[T.length];                // Palindromic count for T

        T[0] = '(';
        T[T.length - 1] = ')';
        int j = 0;
        for(int i = 1; i < T.length - 1; i++) {
            if(i%2==0)
                T[i] = s.charAt(j++);
            else
                T[i] = '_';
        }

        // Processing: Use Manacher's Algorithm
        int C = 0, R = 0, L = 0, l = 0;
        for(int i = 1; i < T.length - 1; i++) {
            int m = 2 * C - i;

            if (i < R)
                P[i] = Math.min(R - i, P[m]);

            while (T[i + (1 + P[i])] == T[i - (1 + P[i])])
                P[i]++;

            if(i + P[i] > R) {
                C = i;
                R = i + P[i];
                if(P[i] > L) {
                    L = P[i];
                    l = i;
                }
            }
        }

        String temp = new String(T);
        temp = temp.substring(l-P[l], l+P[l]).replaceAll("_", "");
        return temp;
    
    }
}