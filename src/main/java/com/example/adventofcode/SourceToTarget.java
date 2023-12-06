package com.example.adventofcode;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SourceToTarget {
    private long destinationLocation;
    private long sourceLocation;
    private long length;

    public SourceToTarget(long destinationLocation) {
        this.destinationLocation = destinationLocation;
    }
}
