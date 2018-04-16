package edu.wpi.cs3733.TeamD.Managers;

import edu.wpi.cs3733.TeamD.Database;
import edu.wpi.cs3733.TeamD.Entities.Gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GiftDirectory {

    private HashMap<String, Gift> gifts;

    public GiftDirectory(){
        gifts = Database.getInstance().loadGiftDirectory();
    }

    public Gift getGift(String name){
        return gifts.get(name);
    }

    public ArrayList<Gift> getGifts(){

        ArrayList<Gift> giftList = new ArrayList<>();
        Iterator it = gifts.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            giftList.add((Gift)pair.getValue());
        }

        return giftList;
    }

    public Gift addGift(String name, float cost, boolean isFood){
        Gift g = new Gift(name, cost, isFood);
        if(Database.getInstance().insertGift(g)){
            gifts.put(name, g);
            return g;
        }

        return g;
    }

}
