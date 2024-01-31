package org.domains.solutions.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Domain {

	private static final String EMAIL_SEPARATOR = "@";

	String name;

	public static Domain fromEmail(String email) {
		String[] emailParts = email.split(EMAIL_SEPARATOR);
		String domainName = emailParts[1];

		return Domain.of(domainName);
	}
}
