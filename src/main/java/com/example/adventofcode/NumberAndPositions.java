package com.example.adventofcode;

import lombok.Data;

import java.util.Objects;

@Data
public class NumberAndPositions {

    Position startPos;
    Position endPos;
    int number;

    public NumberAndPositions(Position startPos, Position endPos, int number) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberAndPositions that = (NumberAndPositions) o;
        return Integer.compare(number, that.number) == 0 && startPos.equals(that.startPos) && endPos.equals(that.endPos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPos, endPos, number);
    }
}
