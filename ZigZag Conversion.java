/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

/*
O(n) solution, has a moving index which oscilates between the changing frequency of the rows and prints the solution left to right.
*/

public class Solution {
    public String convert(String s, int numRows) {
        int row = 1, i = 0;
        char[] ans = new char[s.length()];
        
        if(numRows <= 1)
            return s;
        
        while (row <= numRows) {
            int idx = row - 1;
            int k = row;
            
            while(idx < s.length()) {
                if((numRows - k) * 2 > 0)
                    ans[i++] = s.charAt(idx);
                
                idx += (numRows - k) * 2;
                
                if(k == row) {
                    k = numRows - (row - 1);
                } else {
                    k = row;
                }
            }
            row++;
        }
        return new String(ans);
    }
}