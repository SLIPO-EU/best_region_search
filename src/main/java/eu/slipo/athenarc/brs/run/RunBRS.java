package eu.slipo.athenarc.brs.run;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;

import eu.slipo.athenarc.brs.POI;
import eu.slipo.athenarc.brs.SpatialObject;
import eu.slipo.athenarc.brs.alg.BRS;
import eu.slipo.athenarc.brs.alg.BRSIndexProgressive;
import eu.slipo.athenarc.brs.alg.BRSIndexProgressiveDiv;
import eu.slipo.athenarc.brs.alg.BRSIndexProgressiveDivExhaustive;
import eu.slipo.athenarc.brs.alg.UtilityScoreFunction;
import eu.slipo.athenarc.brs.io.InputFileParser;
import eu.slipo.athenarc.brs.io.ResultsWriter;
import eu.slipo.athenarc.brs.score.ScoreFunction;
import eu.slipo.athenarc.brs.score.ScoreFunctionCount;

public class RunBRS {

	public static void main(String[] args)
			throws NumberFormatException, FileNotFoundException, IOException, ParseException {
		long startTime, endTime;

		/* load configuration file */
		Properties prop = new Properties();
		prop.load(new FileInputStream("config.properties"));

		double eps = Double.parseDouble(prop.getProperty("eps"));
		int topk = Integer.parseInt(prop.getProperty("topk"));
		boolean distinct = Boolean.parseBoolean(prop.getProperty("distinct"));
		boolean div = Boolean.parseBoolean(prop.getProperty("div"));
		boolean exhaustive = Boolean.parseBoolean(prop.getProperty("exhaustive"));
		double decayConstant = Double.parseDouble(prop.getProperty("decay-constant"));
		boolean printResults = true;

		/* parse input file */
		System.out.print("Reading POIs from file...");
		startTime = System.nanoTime();
		InputFileParser inputFileParser = new InputFileParser(prop);
		List<POI> pois = inputFileParser.readPOIsFromFile();
		endTime = (System.nanoTime() - startTime) / 1000000;
		System.out.println(" DONE: Number of POIs: " + pois.size() + " [" + endTime + " msec]");

		ScoreFunction<POI> scoreFunction = new ScoreFunctionCount<POI>();
		List<SpatialObject> bestRegions;
		BRS<POI> brs;
		UtilityScoreFunction utilityScoreFunction = new UtilityScoreFunction();

		/* run the algorithm */
		System.out.print("Computing best regions...\n");
		startTime = System.nanoTime();
		if (exhaustive) {
			brs = new BRSIndexProgressiveDivExhaustive(decayConstant, utilityScoreFunction);
		} else if (div) {
			brs = new BRSIndexProgressiveDiv(decayConstant, utilityScoreFunction);
		} else {
			brs = new BRSIndexProgressive(distinct);
		}
		bestRegions = brs.executeBRS(pois, eps, topk, scoreFunction);
		// endTime = (System.nanoTime() - startTime) / 1000000;
		// System.out.println(" DONE [ # " + endTime + " msec # ]");
		double endTimeSec = (System.nanoTime() - startTime) / 1000000000.0;
		System.out.println(" DONE [ # " + endTimeSec + " sec # ]");

		// compute utility score
		double[] marginalRelevance = utilityScoreFunction.computeDiscountedRelevance(bestRegions, decayConstant);
		System.out.println("\nMarginal relevance for decay constant: " + decayConstant);
		for (int i = 0; i < marginalRelevance.length; i++) {
			System.out.println(marginalRelevance[i]);
		}

		if (printResults) {
			// print results
			ResultsWriter resultsWriter = new ResultsWriter();
			resultsWriter.write(bestRegions, prop.getProperty("output_file"), prop.getProperty("csv_delimiter"));
			System.out.println("Finished writing results.\n");
		}
	}
}