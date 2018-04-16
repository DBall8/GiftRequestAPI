package edu.wpi.cs3733.TeamD.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public abstract class ObservableSubject {
    Observer o;
    List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer o){
        observers.add(o);
    }

    public void notifyObservers(){
        for(Observer o: observers){
            o.update();
        }
    }


}
