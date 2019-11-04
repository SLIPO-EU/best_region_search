package eu.slipo.athenarc.brs.alg;

import java.util.List;

import eu.slipo.athenarc.brs.SpatialObject;
import eu.slipo.athenarc.brs.score.ScoreFunction;

public abstract class BRS<T extends SpatialObject> {
	public abstract List<SpatialObject> executeBRS(List<T> pois, double eps, int topk,
			ScoreFunction<T> scoreFunction);
}