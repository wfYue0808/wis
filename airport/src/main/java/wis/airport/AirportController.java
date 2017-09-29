package wis.airport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airport")
public class AirportController {

	@Autowired
	private AirportService airportService;
	
    @RequestMapping(value="/{shortName}",method=RequestMethod.GET)
	public Map<String, Airport> readByAirportName(@PathVariable("shortName") String shortName) {
    /*	Map<String, Airport> map = new HashMap<String, Airport>();
    	map.put(shortName, null);
    	return map;*/
		return airportService.findByShortName(shortName);
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
