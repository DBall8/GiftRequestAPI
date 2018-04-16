package edu.wpi.cs3733.TeamD.Reports;

import edu.wpi.cs3733.TeamD.Entities.GiftRequest;
import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.*;

public class GiftFrequencyReport {

    private BarChart chart;
    private HashMap<String, Integer> frequencies = new HashMap<String, Integer>();

    public GiftFrequencyReport(BarChart chart){
        this.chart = chart;
    }

    public void generateReport(){

        List<GiftRequest> grs = GiftServiceRequest.getGRM().getAllGiftRequests();
        frequencies = new HashMap<>();
        for(GiftRequest gr: grs){
            String giftName = gr.getGift().getName();
            if(!frequencies.containsKey(giftName)){
                frequencies.put(giftName, 1);
            }
            else{
                frequencies.put(giftName, frequencies.get(giftName) + 1);
            }
        }

        Iterator it = frequencies.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            addColumn((String)pair.getKey(), (int)pair.getValue());
        }
    }

    private void addColumn(String name, double value){
        XYChart.Series series = new XYChart.Series();
        series.setName(name);
        series.getData().add(new XYChart.Data(name, value));

        chart.getData().add(series);
    }
}
