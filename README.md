## Best Region Search

#### Overview

This repository contains an implementation for the top-k best region search algorithm presented in this paper:
[D. Skoutas, D. Sacharidis, K. Patroumpas. Efficient Progressive and Diversified Top-k Best Region Search. In SIGSPATIAL 2018, pp. 299-308.](https://doi.org/10.1145/3274895.3274965)

#### Installation

Get the source code: `git clone https://github.com/SLIPO-EU/best_region_search.git`
Build with Maven: `mvn install`

#### Execution

Rename the file `config.properties.example` to `config.properties` and set the values of the parameters accordingly.
Execute with: `java -jar target/brs.jar`

#### File format

Input format: `poi_id;poi_name;poi_lon;poi_lat;poi_kwd1,poi_kwd2,...`
Output format: `region_id;region_geometry;region_score`

#### Documentation

Javadoc is available [here](https://slipo-eu.github.io/best_region_search/).

#### License

The contents of this project are licensed under the [Apache License 2.0](https://github.com/SLIPO-EU/loci/blob/master/LICENSE).
