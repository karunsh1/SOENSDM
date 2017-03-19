package StockAnalysis.BuySell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ShowWindow extends JFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new BorderLayout());

		JPanel panel_Upper = StockSupport.getPanel();
		panel_Upper.setPreferredSize(new Dimension(0, 50));
		panel_Upper.setBorder(BorderFactory.createRaisedBevelBorder());
		JPanel panel_Right = StockChart.createChartPanel();
		panel_Right.setBorder(BorderFactory.createRaisedBevelBorder());
		
        frame.getContentPane().add(panel_Upper, BorderLayout.PAGE_START);
        frame.getContentPane().add(panel_Right, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Stock Trading");
	}
}