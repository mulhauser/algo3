package com.example.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AlgoDay5 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/input5.txt"));


        System.out.println(part1(lines));

        System.out
    }


    public static long part1(List<String> lines) {
        List<String> seeds = extractSeeds(lines);
        List<SourceToTarget> seedToSoilMap = new ArrayList<>();
        List<SourceToTarget> soitToFertiMap = new ArrayList<>();
        List<SourceToTarget> fertiToWaterMap = new ArrayList<>();
        List<SourceToTarget> waterToLightMap = new ArrayList<>();
        List<SourceToTarget> lightToTempMap = new ArrayList<>();
        List<SourceToTarget> tempToHumMap = new ArrayList<>();
        List<SourceToTarget> humTolocMap = new ArrayList<>();


        boolean seedToSoil, soilToFerti, fertiToWater, waterToLight, lightToTemp, tempToHum, humToloc;
        seedToSoil = soilToFerti = fertiToWater = waterToLight = lightToTemp = tempToHum = humToloc = false;
        for (String line : lines) {
            if (line.isEmpty()) {
                seedToSoil = soilToFerti = fertiToWater = waterToLight = lightToTemp = tempToHum = humToloc = false;
                continue;
            }
            switch (line) {
                case "seed-to-soil map:":
                    seedToSoil = true;
                    soilToFerti = fertiToWater = waterToLight = lightToTemp = tempToHum = humToloc = false;
                    continue;
                case "soil-to-fertilizer map:":
                    soilToFerti = true;
                    seedToSoil = fertiToWater = waterToLight = lightToTemp = tempToHum = humToloc = false;
                    continue;
                case "fertilizer-to-water map:":
                    fertiToWater = true;
                    seedToSoil = soilToFerti = waterToLight = lightToTemp = tempToHum = humToloc = false;
                    continue;
                case "water-to-light map:":
                    waterToLight = true;
                    seedToSoil = soilToFerti = fertiToWater = lightToTemp = tempToHum = humToloc = false;
                    continue;
                case "light-to-temperature map:":
                    lightToTemp = true;
                    seedToSoil = soilToFerti = fertiToWater = waterToLight = tempToHum = humToloc = false;
                    continue;
                case "temperature-to-humidity map:":
                    tempToHum = true;
                    seedToSoil = soilToFerti = fertiToWater = waterToLight = lightToTemp = humToloc = false;
                    continue;
                case "humidity-to-location map:":
                    humToloc = true;
                    seedToSoil = soilToFerti = fertiToWater = waterToLight = lightToTemp = tempToHum = false;
                    continue;
            }
            if (seedToSoil) {
                extractSourceDestinationMap(seedToSoilMap, line);
            }
            if (soilToFerti) {
                extractSourceDestinationMap(soitToFertiMap, line);
            }
            if (fertiToWater) {
                extractSourceDestinationMap(fertiToWaterMap, line);
            }
            if (waterToLight) {
                extractSourceDestinationMap(waterToLightMap, line);
            }
            if (lightToTemp) {
                extractSourceDestinationMap(lightToTempMap, line);
            }
            if (tempToHum) {
                extractSourceDestinationMap(tempToHumMap, line);
            }
            if (humToloc) {
                extractSourceDestinationMap(humTolocMap, line);
            }
        }
        long minLocation = Integer.MAX_VALUE;
        for (String seed : seeds) {
            long seedPosition = Long.parseLong(seed);
            SourceToTarget defaultTarget = new SourceToTarget(seedPosition);
            long nextSourcePosition = extractNextPosition(seedToSoilMap, seedPosition);
            nextSourcePosition = extractNextPosition(soitToFertiMap, nextSourcePosition);
            nextSourcePosition = extractNextPosition(fertiToWaterMap, nextSourcePosition);
            nextSourcePosition = extractNextPosition(waterToLightMap, nextSourcePosition);
            nextSourcePosition = extractNextPosition(lightToTempMap, nextSourcePosition);
            nextSourcePosition = extractNextPosition(tempToHumMap, nextSourcePosition);
            nextSourcePosition = extractNextPosition(humTolocMap, nextSourcePosition);
            if (nextSourcePosition < minLocation) {
                minLocation = nextSourcePosition;
            }
        }
        return minLocation;
    }

    public static long extractNextPosition(List<SourceToTarget> list, long sourcePosition) {
        Optional<SourceToTarget> sourceToTarget = list.stream().filter(stt -> sourcePosition > stt.getSourceLocation() && sourcePosition <= stt.getSourceLocation()+ stt.getLength()).findFirst();
        if (sourceToTarget.isPresent()) {
            SourceToTarget toTarget = sourceToTarget.get();
            return toTarget.getDestinationLocation() + sourcePosition - toTarget.getSourceLocation();
        }
        return sourcePosition;
    }
    public static void extractSourceDestinationMap(List<SourceToTarget> map, String line) {
        String[] seedToSoilArray = line.split(" ");
        SourceToTarget srcTar = new SourceToTarget();
        srcTar.setDestinationLocation(Long.parseLong(seedToSoilArray[0]));
        srcTar.setSourceLocation(Long.parseLong(seedToSoilArray[1]));
        srcTar.setLength(Long.parseLong(seedToSoilArray[2]));
        map.add(srcTar);
    }

    public static List<String> extractSeeds(List<String> lines) {
        return Arrays.asList(lines.get(0).replace("seeds: ", "").split(" "));
    }
}
