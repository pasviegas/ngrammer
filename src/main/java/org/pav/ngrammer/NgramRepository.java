package org.pav.ngrammer;

import java.util.List;


public interface NgramRepository {

	List<String> trends();
	
	void init();

}