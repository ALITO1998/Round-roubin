/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafxapplication3.GanttChart.ExtraData;

/**
 *
 * @author alial
 */
public class processResult extends Application{
    
    static RRC r = new RRC();
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        int ts = Integer.parseInt(processInfo.time_slice);
        int num_chart_l = 0 ;
        for (int i = 0; i < r.com.length; i++) {
            num_chart_l += r.com[i];
        }
        int[] BT = r.burst;
        String [] SN = new String[num_chart_l];
        for (int i = 0; i < r.gantChart.length; i++) {
            int x = BT[i] -Integer.parseInt(processInfo.time_slice);
            if(x>0){
                for (int j = 0; j < Integer.parseInt(processInfo.time_slice); j++) {
                    SN[i+j]=r.gantChart[i];
                }
                BT[i] -= Integer.parseInt(processInfo.time_slice);
            }else{
                for (int j = 0; j < x+Integer.parseInt(processInfo.time_slice); j++) {
                    SN[i+j]=r.gantChart[i];
                }
                BT[i] = 0;
            }
        }
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(25,25,25,25));
        Label [] chart = new Label[num_chart_l];
        for (int i = 0; i < num_chart_l; i++) {
            Label L = new Label(Integer.toString(i+1));
            L.setMinHeight(10);
            L.setMinWidth(20);
            int q = getIndex(r.pName,SN[i]);
            L.setStyle("-fx-background-color:rgb("+q*10+","+q*10+","+q*10+");-fx-color:rgb(255,255,255)");
            chart[i] = L;
            grid1.add(L, i, 0);
        }
        root.setTop(grid1);
        /*final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final GanttChart<Number,String> chart = new GanttChart<Number,String>(xAxis,yAxis);
        xAxis.setLabel("");
        xAxis.setTickLabelFill(Color.CHOCOLATE);
        xAxis.setMinorTickCount(r.gantChart.length * n);

        yAxis.setLabel("");
        yAxis.setTickLabelFill(Color.CHOCOLATE);
        yAxis.setTickLabelGap(10);
        yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(r.pName)));

        chart.setTitle("");
        chart.setLegendVisible(false);
        chart.setBlockHeight( 10);
        String machine;
        XYChart.Series [] series1 = new XYChart.Series[r.gantChart.length];
        for (int i = 0; i < r.gantChart.length; i++) {
            machine = r.gantChart[i];
            int t = getIndex(r.pName ,r.gantChart[i] );
            XYChart.Series series2 = new XYChart.Series();
            switch(t){
                case 0:
                    series2.getData().add(new XYChart.Data(i, machine, new ExtraData( n, "status-green")));
                case 1:
                    series2.getData().add(new XYChart.Data(i, machine, new ExtraData( n, "status-red")));
                case 2:
                    series2.getData().add(new XYChart.Data(i, machine, new ExtraData( n, "status-green")));
            series1[i] = series2;
        }
        }
        for (int i = 0; i < r.gantChart.length; i++) {
            chart.getData().add(series1[i]);
        }
        chart.setAnimated(false);
        chart.getStylesheets().add(getClass().getResource("ganttchart.css").toExternalForm());
        root.setTop(chart);
       /* GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(25,25,25,25));
        Label [] chart = new Label[100];
        for (int i = 0; i < 100; i++) {
            Label L = new Label();
            L.setMinHeight(10);
            L.setMinWidth(8);
            L.setStyle("-fx-border-color:black;");
            chart[i] = L;
            grid1.add(L, i, 0);
        }
        root.setTop(grid1);*/
        
        Text footer = new Text("powred by @Alito");
        footer.setFont(Font.font("roman", 8));
        root.setBottom(footer);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));  
        
        
        
        Label L1 = new Label("Process Name");
        Label L2 = new Label("Process Time Arraival");
        Label L3 = new Label("Process Burst Time");
        Label L4 = new Label("Process Time Compilation");
        Label L5 = new Label("Process Wait Time");
        grid.add(L1, 0, 1);
        grid.add(L2, 1, 1);
        grid.add(L3, 2, 1);
        grid.add(L4, 3, 1);
        grid.add(L5, 4, 1);
        int x = Integer.parseInt(processInfo.num_of_proc);
        TextField text[][] = new TextField[x][5];
        for(int i = 0;i<x;i++){
            TextField textField1 = new TextField();
            text[i][0] = textField1;
            TextField textField2 = new TextField();
            text[i][1] = textField2;
            TextField textField3 = new TextField();
            text[i][2] = textField3;
            TextField textField4 = new TextField();
            text[i][3] = textField4;
            TextField textField5 = new TextField();
            text[i][4] = textField5;
            grid.add(text[i][0], 0, i+2);
            grid.add(text[i][1], 1, i+2);
            grid.add(text[i][2], 2, i+2);
            grid.add(text[i][3], 3, i+2);
            grid.add(text[i][4], 4, i+2);
        }
        for (int i = 0; i < x; i++) {
            text[i][0].setText(r.pName[i]);
            text[i][0].setDisable(true);
            text[i][1].setText(Integer.toString(r.aTime[i]));
            text[i][1].setDisable(true);
            text[i][2].setText(Integer.toString(r.burst[i]));
            text[i][2].setDisable(true);
            text[i][3].setText(Integer.toString(r.com[i]));
            text[i][3].setDisable(true);
            text[i][4].setText(Integer.toString(r.wTime[i]));
            text[i][4].setDisable(true);
        }
        Label L6 = new Label("Average waiting time is :");
        grid.add(L6, 0, x+2);
        TextField T6 = new TextField(Float.toString(r.avgwtime));
        grid.add(T6, 1, x+2);
        T6.setDisable(true);
        Label L7 = new Label("Average compilation  time is :");
        grid.add(L7, 0, x+3);
        TextField T7 = new TextField(Float.toString(r.avgctime));
        grid.add(T7, 1, x+3);
        T7.setDisable(true);
        Button btn = new Button("Finish");
        HBox hbtn = new HBox(btn);
        hbtn.setAlignment(Pos.CENTER);
        grid.add(hbtn, 0, x+4, 5, 1);
        btn.setOnAction((event) -> {
            primaryStage.close();
        });
        
        
        
       
        root.setCenter(grid);
        
        ScrollPane Sc = new ScrollPane(root);

        
        
        Scene scene = new Scene(Sc);
        
        primaryStage.setTitle("process Result");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public int getIndex (String [] arr ,String val){
        int x=-1;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == val){
                x = i;
                break;
            }
            
        }
 
        return x;
    }
    
}
