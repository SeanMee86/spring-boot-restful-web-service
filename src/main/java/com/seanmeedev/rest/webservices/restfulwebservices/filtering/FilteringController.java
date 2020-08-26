package com.seanmeedev.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
//import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	
	//field1, field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		var mapping = new MappingJacksonValue(someBean);
		
		var filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field1", "field2");
		
		FilterProvider filters = 
				new SimpleFilterProvider()
					.addFilter("SomeBeanFilter", filter);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBeans(){
		var list = Arrays.asList(
				new SomeBean("value1", "value2", "value3"),
				new SomeBean("value1", "value22", "value23")
			);
		
		var filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field2", "field3");
		
		var filters = 
				new SimpleFilterProvider()
					.addFilter("SomeBeanFilter", filter);
		
		var mapping = new MappingJacksonValue(list);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
}
