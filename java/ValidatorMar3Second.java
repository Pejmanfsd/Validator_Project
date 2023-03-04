import java.util.Arrays;

public class Validator {

	public static boolean isAlphaNum(char chr) {
		// true if the character is a letter of the English alphabet (case insensitive)
		// or a number between 0 and 9, false otherwise
		if ((chr >= 'A' && chr <= 'Z') || (chr >= 'a' && chr <= 'z') || (chr >= '0' && chr <= '9'))
			return true;
		return false;

	}

	public static boolean isSpecialChar(char chr, boolean allowUnder) {
		// Return true if chr is a dash (-), period (.)
		// if allowUnder is true also allow for the underscore (_) to return true, false
		// otherwise.
		if (allowUnder == true) {
			return chr == '-' || chr == '.' || chr == '_';
		}
		return chr == '-' || chr == '.';

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
		if (str.contains("@") && (str.indexOf('@') == str.lastIndexOf('@')))
			return true;
		return false;
	}

	public static String fetchBeforeAt(String str) {
		// Return a String containing the portion before the @ symbol.
		return str.substring(0, str.indexOf('@'));
	}

	public static String fetchAfterAt(String str) {
		// Return a String containing the portion after the @ symbol.
		return str.substring(str.indexOf('@') + 1, str.length());
	}

	public static boolean isPrefix(String str) {
		// Contains at least one character.
		if (str.length() <= 1)
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
//		if (str.lastIndexOf('.') < 0 || str.lastIndexOf('.') >= str.length() - 1)
//			return false;
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
			if (!((str2.charAt(i) >= 'A' && str2.charAt(i) <= 'Z') || (str2.charAt(i) >= 'a' && str2.charAt(i) <= 'z')))
				return false;
		}

		return true;
	}

	public static boolean safePassword(String str) {
		// Contains at least one alphanumeric characters.--included by another condition
		// Contains a minimum 7 characters and maximum 15 characters.
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
//		int counter = 0; // count repeated character
		for (int i = 0; i < chrArray.length; i++) {
			chr = chrArray[i];
			if ((!uLetter) && chr >= 'A' && chr <= 'Z')
				uLetter = true;
			else if ((!lLetter) && chr >= 'a' && chr <= 'z')
				lLetter = true;
			else if ((!number) && chr >= '0' && chr <= '9')
				number = true;
			else if ((!specialChar) && isSpecialChar(chr, true))
				specialChar = true;
			// The same character must never be repeated more than twice
			if (i > 0 && i < chrArray.length - 1) {
				if ((chr == chrArray[i - 1]) && (chr == chrArray[i + 1]))
					return false;
//				if (chr == chrArray[i + 1]) {
//					counter++;
//					if (counter > 1)
//						return false;
//				} else
//					counter = 0;
			}
		}
		if (!(uLetter && lLetter && number && specialChar))
			return false;

		return true;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(isAlphaNum('A') && isAlphaNum('Z') && isAlphaNum('a') && isAlphaNum('z') && isAlphaNum('0')
//				&& isAlphaNum('9'));
//		System.out.println(isPrefixChar('-') + " " + isPrefixChar('F') + " " + isPrefixChar('8') + " "
//				+ isPrefixChar('&') + " " + isPrefixChar(' '));
//		System.out.println(
//				singleAtSign("username@domain.com") + " " + singleAtSign("@gmail.com") + " " + singleAtSign("A@b@c@d"));
//		System.out.println(fetchBeforeAt("username@domain.com") + " " + fetchBeforeAt("you@regreat") + " "
//				+ fetchBeforeAt("@gmail.com"));
//		System.out.println(fetchAfterAt("username@domain.com") + " " + fetchAfterAt("you@regreat") + " "
//				+ fetchAfterAt("@gmail.com"));
//		System.out.println(
//				isPrefix("you_me") + " " + isPrefix("to.bcc") + " " + isPrefix("and..so") + " " + isPrefix("thebest!"));
//
		System.out.println(isDomain(".ac-133.yul"));

		System.out.println(safePassword("Aa2-!@#$%^&*"));
	}

}
