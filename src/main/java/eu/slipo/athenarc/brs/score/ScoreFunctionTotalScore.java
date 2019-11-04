package eu.slipo.athenarc.brs.score;

import java.util.List;

import eu.slipo.athenarc.brs.SpatialObject;

public class ScoreFunctionTotalScore extends ScoreFunction<SpatialObject> {

	@Override
	public double computeScore(List<SpatialObject> objects) {
		double totalScore = 0;
		for (SpatialObject object : objects) {
			totalScore += object.getScore();
		}
		return totalScore;
	}
}