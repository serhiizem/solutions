package org.domains.solutions.utils;

import static java.util.Objects.isNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CollectionUtils {

	public static <T> boolean isEmpty(Collection<T> collection) {
		return isNull(collection) || collection.isEmpty();
	}

	public static <T> void dequeueOnLimitSizeOverflow(Queue<T> queue, int maxSize) {
		if (queue.size() > maxSize) {
			queue.poll();
		}
	}

	public static <T> List<T> transformQueueToList(Queue<T> queue) {
		List<T> resultList = new LinkedList<>();
		while (!queue.isEmpty()) {
			resultList.add(0, queue.poll());
		}
		return resultList;
	}

}
