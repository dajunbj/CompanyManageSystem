package com.service.sample;

import java.util.List;

public interface SampleService {

	List<String[]>  getValues();
	int registerValues(List<List<String>> records);
	
}
