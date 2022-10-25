package org.seat.domain.model;

import java.util.List;

public class Plateau {

    private Integer rightBoundary;
    private Integer topBoundary;
    private List<Mower> mowers;

    public Plateau() {
    }

    public Plateau(Integer rightBoundary, Integer topBoundary, List<Mower> mowers) {
        this.rightBoundary = rightBoundary;
        this.topBoundary = topBoundary;
        this.mowers = mowers;
    }

    public Integer getRightBoundary() {
        return rightBoundary;
    }

    public void setRightBoundary(Integer rightBoundary) {
        this.rightBoundary = rightBoundary;
    }

    public Integer getTopBoundary() {
        return topBoundary;
    }

    public void setTopBoundary(Integer topBoundary) {
        this.topBoundary = topBoundary;
    }

    public List<Mower> getMowers() {
        return mowers;
    }

    public void setMowers(List<Mower> mowers) {
        this.mowers = mowers;
    }
}
