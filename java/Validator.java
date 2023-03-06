import java.util.Arrays;

public class Validator {

	public static boolean isUpperCase(char chr) {
		return chr >= 'A' && chr <= 'Z';
	}

	public static boolean isLowerCase(char chr) {
		return chr >= 'a' && chr <= 'z';
	}

	public static boolean isNum(char chr) {
		return chr >= '0' && chr <= '9';
	}

	public static boolean isAlphaNum(char chr) {
		// true if the character is a letter of the English alphabet (case insensitive)
		// or a number between 0 and 9, false otherwise
		return (isUpperCase(chr) || isLowerCase(chr) || isNum(chr));
	}

	public static boolean isSpecialChar(char chr, boolean allowUnder) {
		// Return true if chr is a dash (-), period (.)
		// if allowUnder is true, chr=='_', will return true, false otherwise.
		boolean isDashOrPeriod = chr == '-' || chr == '.';
		if (allowUnder)
			return isDashOrPeriod || chr == '_';
		return isDashOrPeriod;
	}

	public static boolean isPrefixChar(char chr) {
		// contain only alphanumeric characters, dashes, periods, or underscores.
		return isAlphaNum(chr) || isSpecialChar(chr, true);
	}

	public static boolean isDomainChar(char chr) {
		// a valid domain can contain only alphanumeric characters, dashes, or periods.
		return isAlphaNum(chr) || isSpecialChar(chr, false);
	}

	public static boolean singleAtSign(String str) {
		// if str contains a single at sign (@)
		if (str == null)
			return false;
		if (str.contains("@") && (str.indexOf('@') == str.lastIndexOf('@')))
			return true;
		return false;
	}

	public static String fetchBeforeAt(String str) {
		// Return a String containing the portion before the @ symbol.
		if (str == null)
			return "";
		int indexAt = str.indexOf('@');
		if (indexAt < 0)
			return "";
		else
			return str.substring(0, indexAt);
	}

	public static String fetchAfterAt(String str) {
		// Return a String containing the portion after the @ symbol.
		if (str == null)
			return "";
		int indexAt = str.indexOf('@');
		if (indexAt < 0)
			return "";
		else
			return str.substring(str.indexOf('@') + 1, str.length());
	}

	public static boolean isPrefix(String str) {
		// Contains at least one character.
		if (str == null)
			return false;
		if (str.length() < 1)
			return false;
		// First character is alphanumeric.
		if (!isAlphaNum(str.charAt(0)))
			return false;

		for (int i = 1; i < str.length() - 1; i++) {
			// Contains only alphanumeric characters, underscores (_), periods (.), and
			// dashes (-).
			if (!isPrefixChar(str.charAt(i)))
				return false;
			// An underscore, period, or dash must always be followed by at least one
			// alphanumeric characters.
			if (isSpecialChar(str.charAt(i), true) && isSpecialChar(str.charAt(i + 1), true))
				return false;
		}
		if (!isAlphaNum(str.charAt(str.length() - 1)))
			return false;

		return true;
	}

	public static boolean isDomain(String str) {

		// Made up of two portions separated by a period.
		if (str == null)
			return false;
		if (!str.contains("."))
			return false;
		String str1 = str.substring(0, str.lastIndexOf('.'));
		String str2 = str.substring(str.lastIndexOf('.') + 1, str.length());

		// First portion contains at least one character.
		if (str1.length() < 1)
			return false;

		// Second portion contains at least two characters.
		if (str2.length() < 2)
			return false;

		for (int i = 0; i < str1.length(); i++) {
			// First portion contains only alphanumeric characters, periods, and dashes.
			if (!isDomainChar(str1.charAt(i)))
				return false;
			// A period or dash, in the first portion must be followed by one or more
			// alphanumeric characters.
			if (isSpecialChar(str1.charAt(i), false)) {
				if (i == str1.length() - 1)
					return false;
				else if (!isAlphaNum(str1.charAt(i + 1)))
					return false;
			}
		}

//		» The second portion contains only letters of the alphabet.
		for (int i = 0; i < str2.length(); i++) {
			char chr = str2.charAt(i);
			if (!(isUpperCase(chr) || isLowerCase(chr)))
				return false;
		}

		return true;
	}

	public static boolean isEmail(String str) {
		// true if the String is a valid email address
		// First we should check that the string has only one @:
		if (singleAtSign(str)) {
			// In order to extract what is before @:
			String strBeforeAt = fetchBeforeAt(str);
			// In order to extract what is after @:
			String strAfterAt = fetchAfterAt(str);
			// Conditions of being a valid email address:
			if (isPrefix(strBeforeAt) && isDomain(strAfterAt)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public static String isUsername(String str) {
		// true if the String is a valid Username
		if (str == null) {
			return "";
		}
		if (str == "") {
			return "";
		}
		// Evaluating the first condition (containing at least one alphanumeric
		// character):
		boolean condition_1 = false;
		for (int i = 0; i < str.length(); i++) {
			if (isAlphaNum(str.charAt(i))) {
				condition_1 = true;
				break;
			}
		}
		/*
		 * Evaluating the third condition (Containing only alphanumeric characters,
		 * periods, dashes, or an exclamation point)
		 */
		boolean condition_3 = true;
		for (int i = 0; i < str.length(); i++) {
			if (isDomainChar(str.charAt(i)) == false && str.charAt(i) != '!') {
				condition_3 = false;
				break;
			}
		}

		/*
		 * Evaluating the fifth condition (A period, or dash must always be followed by
		 * at least one alphanumeric character)
		 */
		boolean condition_5 = true;
		for (int i = 0; i < str.length() - 1; i++) {
			if (isSpecialChar(str.charAt(i), false) && isAlphaNum(str.charAt(i + 1)) == false) {
				condition_5 = false;
				break;
			}
		}
		if (str.length() > 0 // Evaluating the obvious condition!
				&& str.length() <= 7 // Evaluating the second condition
				&& isSpecialChar(str.charAt(0), false) // Evaluating the fourth condition
				&& condition_1 // Evaluating the first condition
				&& condition_3 // Evaluating the third condition
				&& condition_5 // Evaluating the fifth condition
				&& isSpecialChar(str.charAt(str.length() - 1), false) == false) {
			return (str.toLowerCase());
		} else {
			return ("");
		}
	}

	public static boolean safePassword(String str) {
		// Contains at least one alphanumeric characters.--included by another condition
		// Contains a minimum 7 characters and maximum 15 characters.
		if (str == null)
			return false;
		if (str.length() < 7 || str.length() > 15)
			return false;
		// Contain at least one uppercase letter
		boolean uLetter = false;
		// one lowercase letter
		boolean lLetter = false;
		// one number,
		boolean number = false;
		// and one period, dash or underscore.
		boolean specialChar = false;

		char[] chrArray = str.toCharArray();
		Arrays.sort(chrArray);
		char chr;

		for (int i = 0; i < chrArray.length; i++) {
			chr = chrArray[i];
			if ((!uLetter) && isUpperCase(chr))
				uLetter = true;
			else if ((!lLetter) && isLowerCase(chr))
				lLetter = true;
			else if ((!number) && isNum(chr))
				number = true;
			else if ((!specialChar) && isSpecialChar(chr, true))
				specialChar = true;
			// The same character must never be repeated more than twice
			if (i > 0 && i < chrArray.length - 1) {
				if ((chr == chrArray[i - 1]) && (chr == chrArray[i + 1]))
					return false;
			}
		}
		if (!(uLetter && lLetter && number && specialChar))
			return false;

		return true;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(isUsername(".3de3."));

//		System.out.println(safePassword("32_#Fa!#Zz"));

	}

}
