package wis.airport;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitCacheAndDocument implements CommandLineRunner{


     private Logger log = LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 private AirportMapper airportMapper;
	 @Autowired
	 private Cache cache;

	 
	 @Override
	public void run(String... arg0) throws Exception {
		   cache.deleteCacheAndDocument();
			new AirportMapper().init();
			    cache.readDocument();
			    log.info("初始化完毕......................................");
			    log.info("airportMapper.identifyMap.size()"+airportMapper.identifyMap.size());
			    log.info("airportMapper.map.size()"+airportMapper.map.size());
	 }
	 
	 
	 
	

}
