/*
 * class: Finder
 *
 * version: 1.0 05 Aug 2018
 * 
 * author: Maxim Burishinets
 */

package by.epam.training.service.palindrome;

/**
 * Class Finder implements Manacher's algorithm for finding palindromes of
 * even and odd length
 * 
 * @version    1.0 04 Aug 2018
 * @author     Maxim Busrishinets
 */
public class Finder {
	
	public String findLongestPalindrome(String str) {
        String even = findEvenPalindrome(str);
        String odd = findOddPalindrome(str);
        return even.length() > odd.length() ? even : odd;
	}
	
	public String findOddPalindrome(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		int n = str.length();
		int[] d = new int[n];
		int left = 0;
		int right = -1;
		int index = 0;
		for (int i = 0; i < n; i++) {
			int k = 1;
			if (i <= right) {
				int j = (right - i) + left;
				k = Math.min(d[j], right - i);
			}
			while (i - k >= 0 && i + k < n && str.charAt(i + k) == str.charAt(i - k)) {
				k++;
			}
			d[i] = k--;
			index = d[i] > d[index] ? i : index;
			if (i + k > right) {
				left = i - k;
				right = i + k;
			}
		}
		int beginIndex = index - d[index] + 1;
		int endIndex = index + d[index];
		return str.substring(beginIndex, endIndex);
	}
	
	public String findEvenPalindrome(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		int n = str.length();
		int[] d = new int[n];
		int left = 0;
		int right = -1;
		int index = 0;
		for (int i = 0; i < n; ++i) {
			int k = 1;
			if (i <= right) {
				k = Math.min((right - i) + left, right - i + 1);
			}
			while (i + k - 1 < n && i - k >= 0 && str.charAt(i + k - 1) == str.charAt(i - k)) {
				++k;
			}
			d[i] = --k;
			index = d[i] > d[index] ? i : index;
			if (i + k - 1 > right) {
				left = i - k;
			    right = i + k - 1;
		    }
		}
		int beginIndex = index - d[index];
		int endIndex = index + d[index];
		return str.substring(beginIndex, endIndex);
	}
}
