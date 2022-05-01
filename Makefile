data/raw/chicago-filtered.osm.pbf: data/raw/chicago.osm
	osmium cat $< -o $@ -f osm.pbf,add_metadata=false

data/raw/chicago.osm:
	wget --no-use-server-timestamps -O $@ https://overpass-api.de/api/map?bbox=-87.8558,41.6229,-87.5085,42.0488