package eu.slipo.athenarc.brs.score;

import java.util.List;

import eu.slipo.athenarc.brs.SpatialObject;

public abstract class ScoreFunction<T extends SpatialObject> {
	public abstract double computeScore(List<T> objects);
}