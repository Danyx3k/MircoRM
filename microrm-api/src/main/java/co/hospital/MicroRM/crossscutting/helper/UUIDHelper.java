package co.hospital.MicroRM.crossscutting.helper;

import java.util.UUID;

public final class UUIDHelper {

	private static final UUIDHelper INSTANCE = new UUIDHelper();
	private static final String UUID_DEFAULT_AS_STRING = "00000000-0000-0000-0000-000000000000";

	private UUIDHelper() {
	}

	public static UUIDHelper getUUIDHelper() {
		return INSTANCE;
	}

	public UUID getDefault() {
		return UUID.fromString(UUID_DEFAULT_AS_STRING);
	}

	public UUID getDefault(final UUID value) {
		return ObjectHelper.getDefault(value, getDefault());
	}

	public boolean isDefaultUUID(final UUID value) {
		return getDefault().equals(getDefault(value));
	}
}
