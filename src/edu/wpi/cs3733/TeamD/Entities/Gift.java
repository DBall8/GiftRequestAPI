package edu.wpi.cs3733.TeamD.Entities;

public class Gift {

    private String giftID;
    private String name;
    private float cost;
    private boolean isFood;

    public Gift(String name, float cost, boolean isFood) {
        this.giftID = "GIFT-" + System.currentTimeMillis();
        this.name = name;
        this.cost = cost;
        this.isFood = isFood;
    }

    public String getGiftID() {
        return giftID;
    }

    public String getName() {
        return name;
    }

    public float getCost() {
        return cost;
    }

    public boolean isFood() {
        return isFood;
    }
}
