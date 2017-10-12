package wis.airport;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class Task {

	  private Logger log = LoggerFactory.getLogger(Task.class);
	   @Autowired
	   private AirportMapper airportMapper;
	/**
	 * 定时一个小时查询一次最新的数据
	 */
    @SuppressWarnings("static-access")
	@Scheduled(cron="0/20 * * * * * ")
	   public void getDataSource() {
		   try {				  
			System.out.println("dingshi-------------------------");
			
			Map<String, Map<String,Airport>> temp = airportMapper.dataSouce();
		
		   } catch (Exception e) {

			e.printStackTrace();
		}
	   }
    /**
     * 每周一定时删除上上周的数据
     */
    @Scheduled(cron="0 0/10 * * * *")
    public void clearCache() {
    	new Cache().deleteCacheAndDocument();
    }
    
}
