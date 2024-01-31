package org.domains.solutions;

import lombok.extern.slf4j.Slf4j;
import org.domains.solutions.service.DomainStatisticsService;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class Main {

	public static final int DEFAULT_TOP_DOMAINS_LIMIT = 10;

	public static void main(String[] args) {
		var stdin = new Scanner(new BufferedInputStream(System.in));
		System.out.println("Waiting for emails input");

		List<String> inputEmails = new ArrayList<>();
		while (stdin.hasNextLine()) {
			String email = stdin.nextLine();
			inputEmails.add(email);
		}

		var domainStatisticsService = new DomainStatisticsService();
		var topFrequentDomains = domainStatisticsService.findTopFrequentDomains(inputEmails, DEFAULT_TOP_DOMAINS_LIMIT);
		System.out.println(topFrequentDomains);
	}
}
