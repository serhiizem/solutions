package org.domains.solutions.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.domains.solutions.domain.Domain;
import org.domains.solutions.domain.DomainStatistics;
import org.junit.jupiter.api.Test;

class TestDomainStatisticsService {

	private final DomainStatisticsService domainStatisticsService = new DomainStatisticsService();

	@Test
	void shouldRetrieveFirstUniqueDomains() {
		int limitNumber = 3;
		List<DomainStatistics> domainStatistics = domainStatisticsService.findTopFrequentDomains(
				TestData.SAMPLE_EMAILS, limitNumber);

		assertThat(domainStatistics).hasSize(limitNumber);

		assertThat(domainStatistics.get(0)).satisfies(stats -> {
			assertThat(stats.getDomain()).isEqualTo(Domain.of("vqqfv.com"));
			assertThat(stats.getTotalCount()).isEqualTo(4);
		});
		assertThat(domainStatistics.get(1)).satisfies(stats -> {
			assertThat(stats.getDomain()).isEqualTo(Domain.of("ielyx.com"));
			assertThat(stats.getTotalCount()).isEqualTo(2);
		});
		assertThat(domainStatistics.get(2)).satisfies(stats -> {
			assertThat(stats.getDomain()).isEqualTo(Domain.of("ltfzv.com"));
			assertThat(stats.getTotalCount()).isEqualTo(2);
		});
	}

	@Test
	void shouldRetrieveAllExistingUniqueDomainsWhenLimitIsGreaterThanAmountOfUniqueDomains() {
		int limitNumber = 100;
		List<DomainStatistics> domainStatistics = domainStatisticsService.findTopFrequentDomains(
				TestData.SAMPLE_EMAILS, limitNumber);

		// 5 - total number of unique domains
		assertThat(domainStatistics).hasSize(5);

		assertThat(domainStatistics.get(0)).satisfies(stats -> {
			assertThat(stats.getDomain()).isEqualTo(Domain.of("vqqfv.com"));
			assertThat(stats.getTotalCount()).isEqualTo(4);
		});
		assertThat(domainStatistics.get(1)).satisfies(stats -> {
			assertThat(stats.getDomain()).isEqualTo(Domain.of("ielyx.com"));
			assertThat(stats.getTotalCount()).isEqualTo(2);
		});
		assertThat(domainStatistics.get(2)).satisfies(stats -> {
			assertThat(stats.getDomain()).isEqualTo(Domain.of("ltfzv.com"));
			assertThat(stats.getTotalCount()).isEqualTo(2);
		});
		assertThat(domainStatistics.get(3)).satisfies(stats -> {
			assertThat(stats.getDomain()).isEqualTo(Domain.of("fnao.com"));
			assertThat(stats.getTotalCount()).isEqualTo(1);
		});
		assertThat(domainStatistics.get(4)).satisfies(stats -> {
			assertThat(stats.getDomain()).isEqualTo(Domain.of("ihdlb.com"));
			assertThat(stats.getTotalCount()).isEqualTo(1);
		});
	}
}