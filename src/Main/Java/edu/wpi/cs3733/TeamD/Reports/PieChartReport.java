package edu.wpi.cs3733.TeamD.Reports;

import edu.wpi.cs3733.TeamD.Entities.GiftRequest;
import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.*;

public class PieChartReport {

    private PieChart chart;
    private HashMap<String, Integer> frequencies = new HashMap<>();

    public PieChartReport(PieChart chart){
        this.chart = chart;
    }

    public void generateReport(int days){
        chart.getData().clear();

        List<GiftRequest> grs = GiftServiceRequest.getGRM().getGiftRequestsFromDate(days);
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

        //ObservableList<PieChart.Data> data;
        List<PieChart.Data> dataPoints = new ArrayList<>();

        Iterator it = frequencies.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            dataPoints.add(new PieChart.Data((String)pair.getKey(), (int)pair.getValue()));
        }

        chart.setData(FXCollections.observableArrayList(dataPoints));
    }

}
