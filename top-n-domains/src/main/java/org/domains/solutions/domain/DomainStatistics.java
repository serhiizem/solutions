package org.domains.solutions.domain;

import static java.lang.String.format;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DomainStatistics {

	private static final int INITIAL_COUNT = 0;

	private Domain domain;
	private Integer totalCount;

	public DomainStatistics incrementTotalCount() {
		totalCount++;
		return this;
	}

	public static DomainStatistics of(Domain domain) {
		return new DomainStatistics(domain, INITIAL_COUNT);
	}

	@Override
	public String toString() {
		return format("%s %s", domain.getName(), totalCount);
	}
}
