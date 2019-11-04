package eu.slipo.athenarc.brs.score;

import java.util.List;

import eu.slipo.athenarc.brs.SpatialObject;

public class ScoreFunctionCount<T extends SpatialObject> extends ScoreFunction<T> {

	@Override
	public double computeScore(List<T> objects) {
		return objects.size();
	}
}