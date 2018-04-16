package edu.wpi.cs3733.TeamD.Managers;

import edu.wpi.cs3733.TeamD.Database;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import edu.wpi.cs3733.TeamD.ObserverPattern.ObservableSubject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class GiftDirectory extends ObservableSubject {

    private HashMap<String, Gift> gifts;

    public GiftDirectory(){
        gifts = Database.getInstance().loadGiftDirectory();
    }

    public Gift getGift(String giftID){
        return gifts.get(giftID);
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
        String giftID = "GIFT-" + System.currentTimeMillis();
        Gift g = new Gift(giftID, name, cost, isFood);
        if(Database.getInstance().insertGift(g)){
            gifts.put(giftID, g);

            notifyObservers();
            return g;
        }
        return null;
    }

    public void deleteGift(String giftID){
        if(Database.removeGift(giftID)){
            gifts.remove(giftID);
            notifyObservers();
        }

    }

}
