package be.bram.kac;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class App {

	private final int numberOfSites = 100;
	private final int numberOfObstacles = 10;
	private final int initialNumberOfWhiteParticles = 1;
	private final int numberOfTimeSteps = 200;
	
	
	
	public void run() {
		List<Integer> numberOfWhiteParticles = new ArrayList<>();
		numberOfWhiteParticles.add(initialNumberOfWhiteParticles);
		Ring ring = buildInitialRing();
		for (int i = 0; i < numberOfTimeSteps - 1; i++) {
			numberOfWhiteParticles.add(getNumberOfWhiteParticlesAfterTimeEvolution(ring));
		}
		show(numberOfWhiteParticles);
	}
	
	private void show(List<Integer> numberOfWhiteParticles) {
		XYSeriesCollection dataSeries = toDataSeries(numberOfWhiteParticles);
		JFreeChart chart = ChartFactory.createXYLineChart(null, null, null, dataSeries);
		ChartPanel chartPanel = new ChartPanel(chart);
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setContentPane(chartPanel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	private XYSeriesCollection toDataSeries(List<Integer> numberOfWhiteParticles) {
		XYSeries series = new XYSeries(Integer.valueOf(0));
		XYDataItem xyDataItem = new XYDataItem(0, initialNumberOfWhiteParticles);
		series.add(xyDataItem);
		for (int i = 1; i < numberOfTimeSteps - 1; i++) {
			int number = numberOfWhiteParticles.get(i);
			xyDataItem = new XYDataItem(i, number);
			series.add(xyDataItem);
		}
		return new XYSeriesCollection(series);
	}
	
	
	private int getNumberOfWhiteParticlesAfterTimeEvolution(Ring ring) {
		ring.timeEvolve1();
		return ring.getNumberOfWhiteParticles2();
	}
	
	
	
	private Ring buildInitialRing() {
		RingBuilder ringBuilder = new RingBuilder();
		ringBuilder.numberOfSites(numberOfSites);
		ringBuilder.numberOfObstacles(numberOfObstacles);
		ringBuilder.initialNumberOfWhiteParticles(initialNumberOfWhiteParticles);
		Ring ring = ringBuilder.buildRing();
		return ring;
	}
	
}
