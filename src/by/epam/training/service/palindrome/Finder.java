/*
 * class: Finder
 */

package by.epam.training.service.palindrome;

/**
 * Class Finder implements Manacher's algorithm for finding palindromes of
 * even and odd length
 * 
 * @version    1.0 04 Aug 2018
 * @author     Maxim Burishinets
 */
public class Finder {
	
	/**
	 * Searches the longest palindrome in a text line.
	 * 
	 * @param str a String of text where palindromes will be searched
	 * @return longest palindrome in the string
	 */
	public String findLongestPalindrome(String str) {
        String even = findEvenPalindrome(str);
        String odd = findOddPalindrome(str);
        return even.length() > odd.length() ? even : odd;
	}
	
	/**
	 * Searches the longest palindrome in a text line.
	 * 
	 * The method implements Manacher's algorithm for finding palindrome of
	 * odd length. 
	 * d - array of palindrome radiuses for current character in the text line.
	 * left, right - the border of current rightmost palindrome in the
	 * text line.
	 * index - index of character in the string that represents the center of
	 * longest palindrome
	 * k - the radius of longest palindrome with a center in current position
	 * j - index that represents reflection of current position (i) in the
	 * rightmost palindrome
	 * 
	 * @param str a String of text where palindromes will be searched
	 * @return longest palindrome of odd length in the string
	 */
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
			while (i - k >= 0 && i + k < n 
					&& str.charAt(i + k) == str.charAt(i - k)) {
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
	
	/**
	 * Searches the longest palindrome in a text line.
	 * 
	 * The method implements Manacher's algorithm modified for
	 * palindromes of even length
	 * 
	 * @param str a String of text where palindromes will be searched
	 * @return longest palindrome of even length in the string
	 */
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
			while (i + k - 1 < n && i - k >= 0 
					&& str.charAt(i + k - 1) == str.charAt(i - k)) {
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
