package edu.wpi.cs3733.TeamD.Reports;

import edu.wpi.cs3733.TeamD.Entities.GiftRequest;
import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GiftsOverTimeReport {

    private LineChart chart;
    private HashMap<String, Integer> days = new HashMap<>();

    public GiftsOverTimeReport(LineChart chart){
        this.chart = chart;
    }

    public void generateReport(int daysBack){

        chart.getData().clear();

        List<GiftRequest> grs = GiftServiceRequest.getGRM().getGiftRequestsFromDate(daysBack);

        days = new HashMap<>();
        for(GiftRequest gr: grs){
            Date date = gr.getDate();
            if(!days.containsKey(date.toString())){
                days.put(date.toString(), 1);
            }
            else{
                days.put(date.toString(), days.get(date.toString()) + 1);
            }
        }

        XYChart.Series series = new XYChart.Series();
        series.setName("Gift Orders");

        Iterator it = days.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            addCategory(series, (String)pair.getKey(), (int)pair.getValue());
        }

        chart.getData().add(series);
    }

    private void addCategory(XYChart.Series series, String name, int freq){
        series.getData().add(new XYChart.Data(name, freq));
    }

}
