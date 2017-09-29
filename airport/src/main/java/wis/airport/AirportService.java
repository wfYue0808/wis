package wis.airport;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

	@Autowired
	private AirportMapper airportMapper;
	
	
	
	@SuppressWarnings("static-access")
	public Map<String, Airport> findByShortName(String shortName){
		    return airportMapper.map.get(shortName);
	}
	
	
}
