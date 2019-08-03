package com.andrejeru.excercises.stacks;

import java.util.Objects;

public class StackInfo {
    private int startingPosition;
    private int length;

    public StackInfo() {
    }

    public int getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(int startingPosition) {
        this.startingPosition = startingPosition;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StackInfo stackInfo = (StackInfo) o;
        return startingPosition == stackInfo.startingPosition &&
                length == stackInfo.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingPosition, length);
    }

    @Override
    public String toString() {
        return "StackInfo{" +
                "startingPosition=" + startingPosition +
                ", length=" + length +
                '}';
    }

    public void increaseLength() {
        this.length++;
    }
}
