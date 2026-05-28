package co.hospital.MicroRM.crossscutting.messagescatalog;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageCatalog {

	public String getUserMessage(MessagesEnum key, Locale locale) {
		return key.getDefaultMessage();
	}
}
