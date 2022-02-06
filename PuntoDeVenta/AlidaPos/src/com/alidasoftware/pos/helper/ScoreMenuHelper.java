package com.alidasoftware.pos.helper;

import java.io.Serializable;

public class ScoreMenuHelper implements Serializable {

	private static final long serialVersionUID = -4209819428962210968L;
	
	private double score = 0;
	private int idModule = -1;

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getIdModule() {
		return idModule;
	}

	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}

	public ScoreMenuHelper() {
		
	}
	
	public ScoreMenuHelper(int idPath, double score) {		
		this.idModule = idPath;
		this.score = score;
	}
}
