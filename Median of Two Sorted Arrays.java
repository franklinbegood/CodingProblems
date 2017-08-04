/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
*/

/*
Uses knowledge that the median is the sum of the greatest of the left side and minimum of the right side if the sequence of numbers was perfectly divided in half and in order
divided by two. This algorithm divides each of the arrays at some point such that the combination of L1 and L2 has the same number of values as R1 and R2, then moves those indicies
until L1 < R1, L1 < R2, L2 < R2, L2 < R1 is all met.
*/
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int N1 = nums1.length;
        int N2 = nums2.length;
        
        // nums1 is always the longer array
        if (N2 > N1) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        // return median of nums1 if nums2 is empty
        if (N2 == 0) {
            return (nums1[N1 / 2] + nums1[(N1 - 1) / 2]) / 2.0;
        }
        
        int low = 0;
        int high = N2 * 2;
        
        while(low <= high) {
            int mid2 = (low + high) / 2;
            int mid1 = N1 + N2 - mid2;
            double L1, L2, R1, R2;
            if (mid1 == 0) {
                L1 = Integer.MIN_VALUE;
            } else {
                L1 = nums1[(mid1 - 1) / 2];
            }
            if (mid1 == N1 * 2) {
                R1 = Integer.MAX_VALUE;
            } else {
                R1 = nums1[mid1 / 2];
            }
            if (mid2 == 0) {
                L2 = Integer.MIN_VALUE;
            } else {
                L2 = nums2[(mid2 - 1) / 2];
            }
            if (mid2 == N2 * 2) {
                R2 = Integer.MAX_VALUE;
            } else {
                R2 = nums2[mid2 / 2];
            }
            
            if (L1 > R2) {
                low = mid2 + 1;
            } else if (L2 > R1) {
                high = mid2 - 1;
            } else {
                return ((double)(Math.max(L1, L2) + Math.min(R1, R2))) / 2.0;
            }
        }
        return -1;
    }
}