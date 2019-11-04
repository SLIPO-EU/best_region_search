package eu.slipo.athenarc.brs.score;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eu.slipo.athenarc.brs.SpatialObject;

public class ScoreFunctionDistinctKeywords extends ScoreFunction<SpatialObject> {

	@Override
	public double computeScore(List<SpatialObject> objects) {
		Set<String> distinctKeywords = new HashSet<String>();
		for (SpatialObject object : objects) {
			distinctKeywords.addAll(object.getKeywords());
		}
		return distinctKeywords.size();
	}
}