package org.seat.domain.model;


import java.util.List;

public class Plateau {

    private Integer rightBoundary;
    private Integer topBoundary;
    private List<Mower> mowers;

    public Plateau() {
    }

    public Integer getRightBoundary() {
        return rightBoundary;
    }

    public Integer getTopBoundary() {
        return topBoundary;
    }

    public List<Mower> getMowers() {
        return mowers;
    }

    public void setRightBoundary(Integer rightBoundary) {
        this.rightBoundary = rightBoundary;
    }

    public void setTopBoundary(Integer topBoundary) {
        this.topBoundary = topBoundary;
    }

    public void setMowers(List<Mower> mowers) {
        this.mowers = mowers;
    }
}
