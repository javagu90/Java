package com.alidasoftware.pos.chart;

public class ChartData {
	
	public Integer[] getArray(String chartId) {
		Integer[] array = new Integer[11];
		for (int i = 0; i < array.length; i++) {
			array[i] = (i * 10) * Integer.valueOf(chartId);
		}
		return array;
	}
	
	public String[] getLabels(String chartId) {
		String[] array = new String[11];
		for (int i = 0; i < array.length; i++) {
			array[i] = String.valueOf(Character.toChars(65 + i + Integer.valueOf(chartId)));
		}
		return array;
	}
}
