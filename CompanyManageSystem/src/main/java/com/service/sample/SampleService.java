package com.service.sample;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface SampleService {

	List<String[]>  getValues();
	int registerValues(List<List<String>> records);
	
}
