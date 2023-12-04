package com.example.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AlgoDay2 {

    public static void main(String[]args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/input2-part1.txt"));

        System.out.println("Part 1: "+part1(lines));
        System.out.println("Part 2: "+part2(lines));
    }

    private static int part1(List<String> lines) {
        int red = 12;
        int green = 13;
        int blue = 14;

        int sumId = 0;
        for (String line : lines) {
            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue = 0;
            String[] game = line.split(":");
            String gameId = game[0].trim();
            int id = Integer.parseInt(game[0].trim().replace("Game", "").trim());
            String[] parties = game[1].split(";");
            for (String party : parties) {
                String[] colors = party.split(",");
                for (String color : colors) {
                    if (color.trim().matches("\\d+\\s(red)")) {
                        maxRed = Math.max(Integer.parseInt(color.trim().replace("red", "").trim()), maxRed);
                    } else if (color.trim().matches("\\d+\\s(green)")) {
                        maxGreen = Math.max(Integer.parseInt(color.trim().replace("green", "").trim()), maxGreen);
                    } else if (color.trim().matches("\\d+\\s(blue)")){
                        maxBlue = Math.max(Integer.parseInt(color.trim().replace("blue", "").trim()), maxBlue);
                    }
                }
            }

            if (maxRed <= red && maxGreen <= green && maxBlue <= blue) {
                sumId += Integer.parseInt(gameId.replace("Game ", ""));
            }

        }
        return sumId;
    }

    private static int part2(List<String> lines) {
        int sumId = 0;
        for (String line : lines) {
            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue = 0;
            String[] game = line.split(":");
            String gameId = game[0].trim();
            int id = Integer.parseInt(game[0].trim().replace("Game", "").trim());
            String[] parties = game[1].split(";");
            for (String party : parties) {
                String[] colors = party.split(",");
                for (String color : colors) {
                    if (color.trim().matches("\\d+\\s(red)")) {
                        maxRed = Math.max(Integer.parseInt(color.trim().replace("red", "").trim()), maxRed);
                    } else if (color.trim().matches("\\d+\\s(green)")) {
                        maxGreen = Math.max(Integer.parseInt(color.trim().replace("green", "").trim()), maxGreen);
                    } else if (color.trim().matches("\\d+\\s(blue)")){
                        maxBlue = Math.max(Integer.parseInt(color.trim().replace("blue", "").trim()), maxBlue);
                    }
                }
            }

            sumId += maxRed*maxGreen*maxBlue;


        }
        return sumId;
    }

}
