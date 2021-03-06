package eu.slipo.athenarc.brs.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.locationtech.jts.io.WKTWriter;

import eu.slipo.athenarc.brs.SpatialObject;

public class ResultsWriter {

	public void write(List<SpatialObject> objects, String outputFile, String delimiter) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(outputFile));
		WKTWriter wktWriter = new WKTWriter();
		for (SpatialObject object : objects) {
			out.println(
					object.getId() + delimiter + wktWriter.write(object.getGeometry()) + delimiter + object.getScore());
		}
		out.close();
	}
}