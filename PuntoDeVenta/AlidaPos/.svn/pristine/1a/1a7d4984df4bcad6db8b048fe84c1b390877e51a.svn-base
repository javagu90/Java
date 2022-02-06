package com.alidasoftware.pos.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.alidasoftware.pos.model.Modulo;

public class AccesoMenuHelper implements Serializable {

	private static final long serialVersionUID = -8417219727439354115L;
	
	private List<ScoreMenuHelper> scoreList = new ArrayList<ScoreMenuHelper>();
	private String scoreString = "";
		
	public String getScoreString() {
		return scoreString;
	}

	public void setScoreString(String scoreString) {
		this.scoreString = scoreString;
	}

	public List<ScoreMenuHelper> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<ScoreMenuHelper> scoreList) {
		this.scoreList = scoreList;
	}
	public AccesoMenuHelper() {
		
	}
	
	public AccesoMenuHelper(List<Modulo> moduleList, String moduleScoreString) {
		if (moduleScoreString == null) {
			moduleScoreString = "";
		}
		createScore(moduleList, moduleScoreString);
		printValues();
	}
	
	private void printValues() {
		/*
		for (int i = 0; i < scoreList.size(); i++) {
			System.out.println("Score: " + scoreList.get(i).getScore() + 
					           " - Id   : " + scoreList.get(i).getIdModule()); 
		}
		System.out.println("------------------------------------");
		System.out.println(scoreString);
		System.out.println("------------------------------------");
		*/
	}
	
	private void updateScoreString() {
		String scoreStr = "";
		this.scoreString = scoreStr;
		for (int i = 0; i < scoreList.size(); i++) {
			if (scoreList.get(i).getScore() > 0) {
				scoreStr += scoreList.get(i).getIdModule() + ":" +
			                   scoreList.get(i).getScore() + "|";
			}
		}
		if (scoreStr.length() > 0) {
			this.scoreString = scoreStr.substring(0, scoreStr.length() - 1);		
		}
	}
	
	public void updateScore(int idModule) {
		double score = -1;
		for (int i = 0; i < scoreList.size(); i++) {
			if (scoreList.get(i).getIdModule() == idModule) {
				score = scoreList.get(i).getScore() + 1;
				scoreList.get(i).setScore(score);
				break;
			}
		}
		sortList();
		updateScoreString();
		printValues();
	}

	private List<ScoreMenuHelper> getAuxScoreList(String scoreString) {
		List<ScoreMenuHelper> aux = new ArrayList<ScoreMenuHelper>();
		String[] modules = scoreString.split("\\|");
		String[] values = null;
		if (modules != null) {
			for (int i = 0; i < modules.length; i++) {
				if (!modules[i].equals("")) {
					values = modules[i].split("\\:");
					if (values != null) {
						aux.add(new ScoreMenuHelper(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
					}
				}
			}		
		}
		return aux;
	}
	
	private void createScore(List<Modulo> moduleList, String moduleScoreString) {
		scoreList = new ArrayList<ScoreMenuHelper>();
		boolean exists = false;
		double score = -1;
		List<ScoreMenuHelper> aux = getAuxScoreList(moduleScoreString);
		for (int i = 0; i < moduleList.size(); i++) {
			exists = false;
			for (int j = 0; j < aux.size(); j++) {
				if (aux.get(j).getIdModule() == moduleList.get(i).getIdModulo()) {
					exists = true;
					score = aux.get(j).getScore();
					break;
				}
			}
			if (exists) {
				scoreList.add(new ScoreMenuHelper(moduleList.get(i).getIdModulo(), score));
			} else {
				scoreList.add(new ScoreMenuHelper(moduleList.get(i).getIdModulo(), 0));
			}
		}
		aux = null;
		sortList();
		updateScoreString();
	}
	
	private void sortList() {
		Collections.sort(scoreList, new Comparator<ScoreMenuHelper>() {
            @Override
            public int compare(final ScoreMenuHelper object1, final ScoreMenuHelper object2) {
                return Double.compare(object2.getScore(), object1.getScore()); //object2.getScore().compareTo(object1.getScore());
            }
        });
	}
	
	public double normalize(double min, double max, double value) throws Exception {
		return normalize(min, max, value, 0, 1);
	}
	
	public double normalize(double min, double max, double value,
			double start, double end) throws Exception {
		if (max <= min) {
			throw new Exception("Min cannot be higher than max. Values entered are not valid.");
			}
		if (end <= start) {
			throw new Exception("End cannot be higher than start. Values entered are not valid.");
		}
		if (value >= max) {
			return end;
		}
		if (value <= min) {
			return start;
		}

		double i1 = max - min;
		double i2 = end - start;
		double y = (value - min) * i2 / i1;
		return y + start;
	}

}

	