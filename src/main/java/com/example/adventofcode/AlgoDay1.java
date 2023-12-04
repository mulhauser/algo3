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

    public static void main(String[]args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/input1-part2.txt"));
        int sum = 0;
        for (String line : lines) {
            //String[] results = line.split("/(?=(0|1|2|3|4|5|6|7|8|9|one|two|three|four|five|six|seven|eight|nine))/");
            int intemediate = getFirstAndLast(line);
            sum += intemediate;
        }
        //int sum = lines.stream().map(AlgoDay1::getFirstAndLast).reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }

    public static int getFirstAndLast(String line) {
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
        //Pattern pattern = Pattern.compile("(\\d{1}|one|two|three|four|five|six|seven|eight|nine)?");
        line = line.replace("oneight","oneeight");
        line = line.replace("threeight","threeeight");
        line = line.replace("fiveight","fiveeight");
        line = line.replace("nineight","nineeight");
        line = line.replace("twone","twoone");
        line = line.replace("sevenine","sevennine");
        line = line.replace("eightwo","eighttwo");
        line = line.replace("eighthree","eightthree");
        Pattern pattern = Pattern.compile("(\\d{1}|one|two|three|four|five|six|seven|eight|nine)");
        Matcher matcher = pattern.matcher(line);
        String first= null;
        String last = null;
        int count = 0;

        while(matcher.find())
        {
            last = matcher.group();
            if (first == null) {
                first = last;

            }
            count++;
        }
        /*if (count == 1) {
            return Integer.parseInt(maps.getOrDefault(first, first));
        } else {*/
            return Integer.parseInt((maps.getOrDefault(first, first))+""+(maps.getOrDefault(last, last)));
        //}
    }
}
