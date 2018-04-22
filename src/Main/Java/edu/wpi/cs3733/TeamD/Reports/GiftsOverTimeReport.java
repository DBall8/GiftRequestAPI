package edu.wpi.cs3733.TeamD.Reports;

import edu.wpi.cs3733.TeamD.Entities.GiftRequest;
import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

        DateFormat df  = new SimpleDateFormat("dd/MM/yyyy");
        days = new HashMap<>();
        for(GiftRequest gr: grs){
            Date date = gr.getDate();
            String dateStr = df.format(date);
            if(!days.containsKey(dateStr)){
                days.put(dateStr, 1);
            }
            else{
                days.put(dateStr, days.get(dateStr) + 1);
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
