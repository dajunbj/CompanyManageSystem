package com.sample;

import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.entity.sample.T_workHourBean;
import com.service.sample.SampleServiceImpl;

/**
 * Controllerのテストクラス
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ScreenControllerTest1 {

	@Mock
	private SampleServiceImpl service;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	
	@Before
	public void before() throws Exception {
		// 获取mockmvc对象实例
		mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testTestScreenController() throws Exception {

		List<String[]> list = new ArrayList<String[]>();
		when(service.getValues()).thenReturn(list);
		
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/sample/samplepage").param("downloadAllUserRoleCSV",""))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
		
		System.out.println("testTestScreenController: " + mvcResult.getResponse().getContentAsString());
	}
	
	private T_workHourBean createBean(String employeeId, String workMonth, Timestamp workDay, Timestamp startTime,
		
		Timestamp endTime, int workHous, String status, Timestamp registrationDate, Timestamp updateDate) {
		T_workHourBean bean = new T_workHourBean();
		
		bean.setEmployeeId(employeeId);
		bean.setWorkMonth(workMonth);
		bean.setWorkDay(workDay);
		bean.setStartTime(startTime);
		bean.setEndTime(endTime);
		bean.setWorkHous(workHous);
		bean.setStatus(status);
		bean.setRegistrationDate(registrationDate);
		bean.setUpdateDate(updateDate);
		
		return bean;
	}
}
