package edu.wpi.cs3733.TeamD.Managers;

import edu.wpi.cs3733.TeamD.Database;
import edu.wpi.cs3733.TeamD.Entities.Gift;

import java.util.HashMap;

public class GiftDirectory {

    private HashMap<String, Gift> gifts;

    public GiftDirectory(){
        gifts = Database.getInstance().loadGiftDirectory();
    }

    public Gift getGift(String name){
        return gifts.get(name);
    }

    public void addGift(String name, float cost, boolean isFood){
        Gift g = new Gift(name, cost, isFood);
        if(Database.getInstance().insertGift(g)){
            gifts.put(name, g);
        }
    }

}
