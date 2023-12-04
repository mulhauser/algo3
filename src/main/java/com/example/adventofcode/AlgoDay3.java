package com.example.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AlgoDay3 {

	public static void main(String[] args) {

		try {
			Set<NumberAndPositions> numbers = new HashSet<>();
			List<String> lines = Files.readAllLines(Paths.get("src/main/resources/input3.txt"));
			int sum = 0;
			int sumGear = 0;
			char[][] tab = new char[lines.size()][lines.get(0).length()];
			List<Position> symbols = new ArrayList<>();
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				for (int j = 0; j < line.length(); j++) {
					char str = line.charAt(j);
					tab[i][j] = str;
					if (!Character.isDigit(str) && !(str == '.')) {
						symbols.add(new Position(i, j));
					}
				}
			}
			int nbRows = tab.length;
			int nbCols = tab[0].length;

			for (Position symbol : symbols) {
				boolean isGear = false;
				int numberPartOnGear = 0;
				int subSumGear = 1;
				if (tab[symbol.x][symbol.y] == '*') {
					isGear = true;
				}
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if (i==0 && j==0){
							continue;
						}
						int posXCharToCheck = symbol.getX()+i;
						int posYCharToCheck = symbol.getY()+j;
						if (posXCharToCheck>=0&&posXCharToCheck<nbRows && posYCharToCheck>=0&&posYCharToCheck<nbCols){
							if (Character.isDigit(tab[posXCharToCheck][posYCharToCheck])) {
								NumberAndPositions numPos = getNumberAndPosition(posXCharToCheck, posYCharToCheck, tab);
								if (!numbers.contains(numPos)) {
									numbers.add(numPos);
									sum += numPos.number;
									numberPartOnGear++;
									subSumGear = subSumGear * numPos.number;
								}
							}
						}
					}
				}
				if (numberPartOnGear == 2) {
					sumGear+=subSumGear;
				}
			}

			System.out.println(sum);
			System.out.println(sumGear);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static NumberAndPositions getNumberAndPosition(int x, int y, char[][] tab){
		String number = Character.toString(tab[x][y]);
		int xBis = x;
		int yBis = y;
		while(yBis-1>=0&&Character.isDigit((tab[x][yBis-1]))) {

			number = Character.toString(tab[x][yBis-1]) + number;
			yBis--;
		}
		int startX = xBis;
		int startY = yBis;

		int xTer = x;
		int yTer = y;
		while(yTer+1<tab[x].length&&Character.isDigit((tab[x][yTer+1]))) {

			number = number + Character.toString(tab[x][yTer+1]);
			yTer++;
		}
		int endX = xTer;
		int endY = yTer;

		return new NumberAndPositions(new Position(startX, startY), new Position(endX, endY), Integer.parseInt(number));
		//return null;
	}

}
