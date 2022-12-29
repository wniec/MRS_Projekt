package mrs;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Plot {
    double [] accurate, result,t;
    public Plot(double[] accurate,double[] result,double[] t){
        this.accurate=accurate;
        this.result =result;
        this.t=t;
    }
     public void plot(){
        int n = t.length;
        XYSeries series1= new XYSeries("Wynik Dokładny");
         XYSeries series2= new XYSeries("Wynik Przybliżony");
        for(int i=0;i<n;i++){
            series1.add(t[i],accurate[i]);
            series2.add(t[i], result[i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
         dataset.addSeries(series2);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Wykres u(x)",
                "x",
                "u",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        ChartFrame frame1 = new ChartFrame("wykres u(x)",chart);
        frame1.setVisible(true);
        frame1.setSize(1000,800);
     }
}
