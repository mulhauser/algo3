package com.example.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlgoDay4 {

    public static void main(String[]args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/input4-part1-correct.txt"));

        int sum = countMatchingCard(lines, lines,1);

        System.out.println(sum);
    }

    public static int countMatchingCard(List<String> lines, List<String> basedLines, int firstIndex) {
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
            int i = firstIndex;
            List<String> newLines = new ArrayList<>();
            for (Integer matching : matchingNumber) {
                if (i < basedLines.size()) {
                    newLines.add(basedLines.get(i));
                    i++;
                }
            }
            firstIndex++;
            System.out.println(card[0]+": matching number "+matchingNumber.size());
            int subCountMatch = countMatchingCard(newLines, basedLines, firstIndex);
            sum = sum + matchingNumber.size() + subCountMatch;

        }
        return sum;
    }


}
