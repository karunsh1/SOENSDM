/*
 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockAnalysis.BuySell;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class StockSupport extends JFrame {
 public JButton button;
 static JButton btnShowChart;
 static Date date1, date2, date3, date4;

 public StockSupport() {
 getPanel();
 }

 public static JPanel getPanel() {
 JPanel panel1 = new JPanel();
 JComboBox combo_ST;
 JComboBox combo_LT;
 JLabel LongTerm, ShortTerm;
 final JDateChooser calendar1;
final JDateChooser calendar2;
 combo_ST = new JComboBox();
 combo_LT = new JComboBox();
 btnShowChart = new JButton("Generate Chart");
 ShortTerm = new JLabel("Short Term");
 LongTerm = new JLabel("Long Term");
 calendar1 = new JDateChooser();
 calendar1.setLocale(Locale.US);
 calendar2 = new JDateChooser();
 calendar2.setLocale(Locale.US);
 combo_ST.addItem("50");
 combo_ST.addItem("100");
 combo_ST.setBounds(10, 30, 60, 30);
 combo_LT.setBounds(10, 30, 60, 30);
 combo_LT.addItem("100");
 combo_LT.addItem("200");
 panel1.setLayout(new FlowLayout());
 panel1.setBackground(Color.white);
 panel1.add(ShortTerm);
 panel1.add(combo_ST);
 panel1.add(LongTerm);
 panel1.add(combo_LT);
 panel1.add(new JLabel("Start Date"));
 panel1.add(calendar1);
 panel1.add(new JLabel("End Date"));
 panel1.add(calendar2);
 btnShowChart.addActionListener(new ActionListener() {
 public void actionPerformed(ActionEvent e) {
 date1 = calendar1.getDate();
 date2 = calendar2.getDate();
 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 String start = df.format(date1);
 String end = df.format(date2);
 DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
 try {


 date3 = df1.parse(start);

 date4 = df1.parse(end);

 } catch (Exception ex) {
 ex.printStackTrace();
 }
 }
 });
 btnShowChart.setBounds(120, 100, 120, 100);
 btnShowChart.setSize(30, 20);
 panel1.add(btnShowChart);
 return panel1;
 }
}