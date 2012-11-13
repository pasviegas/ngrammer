package org.pav.ngrammer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hive.HiveOperations;
import org.springframework.stereotype.Repository;

@Repository
public class HiveTemplateNgramRepository implements NgramRepository {
	
	private HiveOperations hiveOperations;
	
	@Autowired
	public HiveTemplateNgramRepository(HiveOperations hiveOperations) {
		this.hiveOperations = hiveOperations;
	}
	
	@Override
	public List<String> trends() {
		return hiveOperations.query("SELECT a.gram as gram, a.decade as decade, a.ratio as ratio, a.ratio / b.ratio as increase FROM by_decade a JOIN by_decade b ON a.gram = b.gram and a.decade - 1 = b.decade WHERE a.ratio > 0.000001 and a.decade >= 190 DISTRIBUTE BY decade SORT BY decade ASC, increase DESC;");
	}

	@Override
	public void init() {
		hiveOperations.query("classpath:ngram-analysis.hql");		
	}

}
