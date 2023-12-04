package com.example.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlgoDay1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/input1.txt"));

        System.out.println("Part 1: " + part1(lines)+" - Should be 55386");
        System.out.println("Part 2: " + part2(lines)+ " - Should be 54824");
    }

    public static int part1(List<String> lines) {
        int sum = 0;
        for (String line : lines) {

            int intermediate = getFirstAndLast(line, "\\d{1}");
            sum += intermediate;
        }
        return sum;
    }

    public static int part2(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            int intermediate = getFirstAndLast(line, "(\\d{1}|one|two|three|four|five|six|seven|eight|nine)");
            sum += intermediate;
        }
        return sum;
    }

    public static int getFirstAndLast(String line, String regex) {
        Map<String, String> maps = new HashMap<>();
        maps.put("one", "1");
        maps.put("two", "2");
        maps.put("three", "3");
        maps.put("four", "4");
        maps.put("five", "5");
        maps.put("six", "6");
        maps.put("seven", "7");
        maps.put("eight", "8");
        maps.put("nine", "9");
        line = line.replace("oneight", "oneeight");
        line = line.replace("threeight", "threeeight");
        line = line.replace("fiveight", "fiveeight");
        line = line.replace("nineight", "nineeight");
        line = line.replace("twone", "twoone");
        line = line.replace("sevenine", "sevennine");
        line = line.replace("eightwo", "eighttwo");
        line = line.replace("eighthree", "eightthree");

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        String first = null;
        String last = null;

        while (matcher.find()) {
            last = matcher.group();
            if (first == null) {
                first = last;

            }
        }
        return Integer.parseInt((maps.getOrDefault(first, first)) + "" + (maps.getOrDefault(last, last)));
    }
}
