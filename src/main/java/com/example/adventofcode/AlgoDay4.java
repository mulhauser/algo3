package com.example.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlgoDay4 {

    public static void main(String[]args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/input4-part1.txt"));

        System.out.println("Part 1: "+part1(lines));

        Map<String, Integer> maps = new HashMap<>();
        part2(lines, lines,1, maps);
        int sum = maps.values().stream().reduce(0, (a,b) -> a+b);
        System.out.println("Part 2: "+sum);
    }

    public static int part1(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            int subSum = 0;
            String[] card = line.split(":");

            String[] part = card[1].split("\\|");
            List<Integer> winning = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcherW = pattern.matcher(part[0]);
            while (matcherW.find()) {
                String group = matcherW.group();
                winning.add(Integer.parseInt(group));
            }

            List<Integer> playingCard = new ArrayList<>();
            Matcher matcherPlay = pattern.matcher(part[1]);
            while (matcherPlay.find()) {
                String group = matcherPlay.group();
                playingCard.add(Integer.parseInt(group));
            }

            List<Integer> matchingNumber = new ArrayList<>();
            for (Integer playingNumber : playingCard) {
                if (winning.contains(playingNumber)) {
                    matchingNumber.add(playingNumber);
                }
            }
            int i = 0;
            for (Integer matching : matchingNumber) {
                if (i == 0) {
                    subSum = 1;
                } else {
                    subSum*=2;
                }
                i++;
            }
            sum += subSum;

        }
        return sum;
    }

    public static void part2(List<String> lines, List<String> basedLines, int firstIndex, Map<String, Integer> maps) {
        for (String line : lines) {
            String[] card = line.split(":");

            Integer value = maps.getOrDefault(card[0], 0);

            String[] part = card[1].split("\\|");
            List<Integer> winning = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcherW = pattern.matcher(part[0]);
            while (matcherW.find()) {
                String group = matcherW.group();
                winning.add(Integer.parseInt(group));
            }

            List<Integer> playingCard = new ArrayList<>();
            Matcher matcherPlay = pattern.matcher(part[1]);
            while (matcherPlay.find()) {
                String group = matcherPlay.group();
                playingCard.add(Integer.parseInt(group));
            }

            List<Integer> matchingNumber = new ArrayList<>();
            for (Integer playingNumber : playingCard) {
                if (winning.contains(playingNumber)) {
                    matchingNumber.add(playingNumber);
                }
            }
            int i = firstIndex;
            List<String> newLines = new ArrayList<>();
            for (Integer matching : matchingNumber) {
                if (i < basedLines.size()) {
                    newLines.add(basedLines.get(i));
                    i++;
                }
            }
                maps.put(card[0], value +1);
            firstIndex++;
            part2(newLines, basedLines, firstIndex, maps);
        }
    }


}
