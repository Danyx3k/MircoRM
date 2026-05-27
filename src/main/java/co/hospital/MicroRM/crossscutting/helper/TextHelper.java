package co.hospital.MicroRM.crossscutting.helper;

import co.hospital.MicroRM.crossscutting.constants.DefaultValues;

public final class TextHelper {

	private static final String EMPTY = DefaultValues.EMPTY_TEXT;

	private TextHelper() {
	}

	public static String getDefault() {
		return EMPTY;
	}

	public static String getDefault(final String value) {
		return ObjectHelper.getDefault(value, getDefault());
	}

	public static String getDefaultWithTrim(final String value) {
		return getDefault(value).trim();
	}

	public static boolean isNullOrWhiteSpace(final String value) {
		return ObjectHelper.isNull(value) || value.trim().isEmpty();
	}

	public static boolean lengthIsValid(final String value, final int minLength, final int maxLength,
			final boolean mustApplyTrim) {
		var valueToValidate = mustApplyTrim ? getDefaultWithTrim(value) : getDefault(value);
		var length = valueToValidate.length();
		return length >= minLength && length <= maxLength;
	}
}
