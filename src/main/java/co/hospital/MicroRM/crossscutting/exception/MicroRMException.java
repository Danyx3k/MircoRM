package co.hospital.MicroRM.crossscutting.exception;

import co.hospital.MicroRM.crossscutting.helper.ObjectHelper;
import co.hospital.MicroRM.crossscutting.helper.TextHelper;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;

import java.util.Arrays;
import java.util.Objects;

public class MicroRMException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final MessagesEnum messageCode;
	private final Object[] messageArgs;
	private Throwable rootException;
	private String userMessage;
	private String technicalMessage;

	private MicroRMException(
			final MessagesEnum messageCode,
			final Object[] messageArgs,
			final Throwable rootException,
			final String userMessage,
			final String technicalMessage) {
		super(rootException);
		this.messageCode = messageCode;
		this.messageArgs = copyArgs(messageArgs);
		setRootException(rootException);
		setUserMessage(userMessage);
		setTechnicalMessage(technicalMessage);
	}

	public static MicroRMException of(final MessagesEnum messageCode, final Object... messageArgs) {
		return new MicroRMException(messageCode, messageArgs, null, null, null);
	}

	public static MicroRMException of(final MessagesEnum messageCode, final Throwable cause, final Object... messageArgs) {
		return new MicroRMException(messageCode, messageArgs, cause, null, null);
	}

	public static MicroRMException create(final String userMessage, final String technicalMessage) {
		return new MicroRMException(null, new Object[0], new Exception(), userMessage, technicalMessage);
	}

	public MessagesEnum getMessageCode() {
		return messageCode;
	}

	public Object[] getMessageArgs() {
		return copyArgs(messageArgs);
	}

	public Throwable getRootException() {
		return rootException;
	}

	private void setRootException(final Throwable rootException) {
		this.rootException = ObjectHelper.getDefault(rootException, new Exception());
	}

	public String getUserMessage() {
		return userMessage;
	}

	private void setUserMessage(final String userMessage) {
		this.userMessage = TextHelper.isNullOrWhiteSpace(userMessage) ? null : TextHelper.getDefaultWithTrim(userMessage);
	}

	public String getTechnicalMessage() {
		return technicalMessage;
	}

	private void setTechnicalMessage(final String technicalMessage) {
		this.technicalMessage = TextHelper.isNullOrWhiteSpace(technicalMessage) ? null : TextHelper.getDefaultWithTrim(technicalMessage);
	}

	public boolean hasCatalogMessageCode() {
		return !Objects.isNull(messageCode);
	}

	private static Object[] copyArgs(final Object[] args) {
		return args == null ? new Object[0] : Arrays.copyOf(args, args.length);
	}
}
