package eu.slipo.athenarc.brs.alg;

import eu.slipo.athenarc.brs.POI;

public class Rectangle implements Comparable<Rectangle> {
	public double idx;
	public boolean isMin;
	public POI p;

	public Rectangle(double idx, boolean isMin, POI p) {
		this.idx = idx;
		this.isMin = isMin;
		this.p = p;
	}

	public int compareTo(Rectangle r) {
		if (this.idx < r.idx)
			return -1;
		else if (this.idx == r.idx) {
			if (!this.isMin && r.isMin) {
				return -1;
			} else if (this.isMin && !r.isMin) {
				return 1;
			} else
				return 0;
		} else {
			return 1;
		}
	}
}
