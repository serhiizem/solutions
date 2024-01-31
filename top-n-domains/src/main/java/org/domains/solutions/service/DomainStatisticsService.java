package org.domains.solutions.service;

import static java.util.Comparator.comparingInt;
import static org.domains.solutions.utils.CollectionUtils.dequeueOnLimitSizeOverflow;
import static org.domains.solutions.utils.CollectionUtils.isEmpty;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.domains.solutions.domain.Domain;
import org.domains.solutions.domain.DomainStatistics;
import org.domains.solutions.utils.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainStatisticsService {

	private static final Comparator<DomainStatistics> MOST_FREQUENT_DOMAINS_COMPARATOR =
			comparingInt(DomainStatistics::getTotalCount);

	public List<DomainStatistics> findTopFrequentDomains(List<String> inputEmails, int limitNumber) {
		if (isEmpty(inputEmails)) {
			log.warn("Received empty list of emails when attempting to compute top frequent domains. " +
					"Returning empty list of results");
			return List.of();
		}

		log.info("Received request to find {} most frequent domains among {} input emails",
				limitNumber, inputEmails.size());
		Map<Domain, DomainStatistics> statisticsPerDomain = collectStatsPerUniqueDomain(inputEmails);
		log.info("Computed statistics for {} unique domains", statisticsPerDomain.keySet().size());

		Queue<DomainStatistics> statisticsPriorityQueue = new PriorityQueue<>(MOST_FREQUENT_DOMAINS_COMPARATOR);
		statisticsPerDomain.values().forEach(domainStatistics -> {
			statisticsPriorityQueue.offer(domainStatistics);
			dequeueOnLimitSizeOverflow(statisticsPriorityQueue, limitNumber);
		});

		List<DomainStatistics> results = CollectionUtils.transformQueueToList(statisticsPriorityQueue);
		log.info("Found {} most frequent emails: {}", results.size(), results);
		return results;
	}

	private Map<Domain, DomainStatistics> collectStatsPerUniqueDomain(List<String> inputEmails) {
		Map<Domain, DomainStatistics> statisticsPerDomain = new HashMap<>();
		inputEmails.forEach(email -> {
			Domain emailDomain = Domain.fromEmail(email);
			DomainStatistics domainStatistics = statisticsPerDomain.getOrDefault(
					emailDomain, DomainStatistics.of(emailDomain));
			statisticsPerDomain.put(emailDomain, domainStatistics.incrementTotalCount());
		});
		return statisticsPerDomain;
	}

}
