package co.hospital.MicroRM.crossscutting.helper;

public final class EmailHelper {

	private EmailHelper() {
	}

	public static boolean isValidFormat(final String email) {
		return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
	}
}
