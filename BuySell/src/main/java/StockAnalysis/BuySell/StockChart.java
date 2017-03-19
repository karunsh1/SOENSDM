
package StockAnalysis.BuySell;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.HighLowRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;



public class StockChart extends ApplicationFrame{
	
final static String filename = "/Users/sunpreetsingh/NetBeansProjects/FinalStock/src/StockBuySell/data1_4.csv";
static TimeSeries t1 = new TimeSeries("moving average Short-Term");
static TimeSeries t2 = new TimeSeries("moving average Long-Term");
static Date date;
private JPanel panel = null;
public StockChart(String title)
{
 super(title);
 panel = createChartPanel();
}
public JPanel getChartPanel(){
return panel;
}
private static OHLCDataset createPriceDataset(String filename)
{
 //the following data is taken from http://finance.yahoo.com/
 StockSupport.getPanel();


 date=StockSupport.date3;
 OHLCSeries s1 = new OHLCSeries(filename);
if(StockSupport.date1==null&& StockSupport.date2==null){
 try {
 BufferedReader in = new BufferedReader(new FileReader(filename));
 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 String inputLine;
 in.readLine();
 while ((inputLine = in.readLine()) != null) {
 StringTokenizer st = new StringTokenizer(inputLine, ",");
 Date date = df.parse( st.nextToken() );
 double open = Double.parseDouble( st.nextToken() );
 double high = Double.parseDouble( st.nextToken() );
 double low = Double.parseDouble( st.nextToken() );
 double close = Double.parseDouble( st.nextToken() );
 s1.add(new Day(date), open, high, low, close);
 t1.add(new Day(date), close);
 t2.add(new Day(date), close);
 }
 in.close();
 }
 catch (Exception e) {
 e.printStackTrace();
 }
}
else{
 try {
 BufferedReader in = new BufferedReader(new FileReader(filename));
 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 String inputLine;
 in.readLine();
 // for(int i=date1;i<)
 while ((inputLine = in.readLine()) != null) {
 StringTokenizer st = new StringTokenizer(inputLine, ",");
 date = df.parse( st.nextToken() );
 double open = Double.parseDouble( st.nextToken() );
 double high = Double.parseDouble( st.nextToken() );
 double low = Double.parseDouble( st.nextToken() );
 double close = Double.parseDouble( st.nextToken() );
 s1.add(new Day(date), open, high, low, close);
 t1.add(new Day(date), close);
 t2.add(new Day(date), close);
 }
 in.close();
 }
 catch (Exception e) {
 e.printStackTrace();
 }
}
 OHLCSeriesCollection dataset = new OHLCSeriesCollection();
 dataset.addSeries(s1);
 return dataset;
}
private static JFreeChart createStockChart()
{
 OHLCDataset data1 = createPriceDataset(filename);
 XYItemRenderer renderer1 = new HighLowRenderer();
 renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
 StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
 new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
 renderer1.setSeriesPaint(0, Color.BLUE);
 DateAxis domainAxis = new DateAxis("Date");
 NumberAxis rangeAxis = new NumberAxis("Price");
 rangeAxis.setNumberFormatOverride(new DecimalFormat("$0.00"));
 rangeAxis.setAutoRange(true);
 rangeAxis.setAutoRangeIncludesZero(false);
 XYPlot plot1 = new XYPlot(data1, domainAxis, rangeAxis, renderer1);
 plot1.setBackgroundPaint(Color.lightGray);
 plot1.setDomainGridlinePaint(Color.white);
 plot1.setRangeGridlinePaint(Color.white);
 plot1.setRangePannable(true);
 //The Short--Term Trend Indicator
 TimeSeries dataset3 = MovingAverage.createMovingAverage(t1, "ST", 25,50);
 TimeSeriesCollection collection = new TimeSeriesCollection();
 collection.addSeries(dataset3);
 plot1.setDataset(1, collection);
 plot1.setRenderer(1, new StandardXYItemRenderer());

 //The Long--Term Trend Indicator
 TimeSeries dataset_Long = MovingAverage.createMovingAverage(t2, "LT", 50,100);
 TimeSeriesCollection collection_Long = new TimeSeriesCollection();
 collection.addSeries(dataset_Long);
 plot1.setDataset(2, collection_Long);
 plot1.setRenderer(2, new StandardXYItemRenderer());



 /*
 XYBarRenderer renderer2 = new XYBarRenderer();
 renderer2.setDrawBarOutline(false);
 renderer2.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
 StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
 new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
 renderer2.setSeriesPaint(1, Color.RED);

 XYBarRenderer renderer3 = new XYBarRenderer();
 renderer3.setDrawBarOutline(false);
 renderer3.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
 StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
 new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
 renderer3.setSeriesPaint(2, Color.green);
*/
 CombinedDomainXYPlot cplot = new CombinedDomainXYPlot(domainAxis);
 cplot.add(plot1, 3);
 cplot.setDomainGridlinePaint(Color.white);
 cplot.setDomainGridlinesVisible(true);
 cplot.setDomainPannable(true);
 //return the new combined chart
 JFreeChart chart = new JFreeChart("Stock Monitoring",
 JFreeChart.DEFAULT_TITLE_FONT, cplot, false);
 ChartUtilities.applyCurrentTheme(chart);
 //renderer2.setShadowVisible(false);
 //renderer2.setBarPainter(new StandardXYBarPainter());
 return chart;
}
//create a panel
public static ChartPanel createChartPanel()
{
 JFreeChart chart = createStockChart();
 return new ChartPanel(chart);
}
}