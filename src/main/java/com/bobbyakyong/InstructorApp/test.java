package com.bobbyakyong.InstructorApp;

import java.util.*;

public class test {

    public static List<String> findSuspiciousUsers(String[] logs, int threshold) {
        Map<String, Integer> countMap = new HashMap<>();

        for (String log : logs) {
            String[] parts = log.split(" ");
            String sender = parts[0];
            String recipient = parts[1];

            if (sender.equals(recipient)) {
                countMap.put(sender, countMap.getOrDefault(sender, 0) + 1);
            } else {
                countMap.put(sender, countMap.getOrDefault(sender, 0) + 1);
                countMap.put(recipient, countMap.getOrDefault(recipient, 0) + 1);
            }
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() >= threshold) {
                result.add(entry.getKey());
            }
        }

        // sort numerically (not lexicographically)
        result.sort(Comparator.comparingInt(Integer::parseInt));

        return result;
    }

    // Example usage
    public static void main(String[] args) {
        String[] logs = {
                "88 99 200",
                "88 99 300",
                "99 32 100",
                "12 12 15"
        };
        int threshold = 2;

        System.out.println(findSuspiciousUsers(logs, threshold));
        // Output: [88, 99]
    }
}