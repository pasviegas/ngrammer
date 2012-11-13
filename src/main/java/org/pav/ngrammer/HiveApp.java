package org.pav.ngrammer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hive.HiveTemplate;

public class HiveApp {

	private static final Log log = LogFactory.getLog(HiveApp.class);

	public static void main(String[] args) throws Exception {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/hive-context.xml", HiveApp.class);
		log.info("Hive Application Running");
		context.registerShutdownHook();	
		
		HiveTemplate template = context.getBean(HiveTemplate.class);
		template.query("show tables;");
							
		NgramRepository repository = context.getBean(HiveTemplateNgramRepository.class);
		repository.init();
		log.info("Trends " + repository.trends());		

	}
}
