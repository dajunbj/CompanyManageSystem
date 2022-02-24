package com.sample;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.controller.sample.SamplePageController;
import com.service.sample.SampleServiceImpl;

/**
 * Controllerのテストクラス
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ScreenControllerTest2 {

	@Mock
	private SampleServiceImpl service;

	@InjectMocks
	private SamplePageController ctrl;

	@Test
	public void testTestScreenController() throws Exception {
		
		List<String[]> list = new ArrayList<String[]>();
		
		Mockito.when(service.getValues()).thenReturn(list);
		
		String ret = ctrl.downloadAllUserRoleCSV(Mockito.any(), Mockito.any());
	
		System.out.println("XXXX:"+ret);
	}
}
