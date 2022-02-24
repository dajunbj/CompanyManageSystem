package com.sample;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.entity.sample.T_workHourBean;
import com.mapper.sample.SampleMapper;
import com.service.sample.SampleServiceImpl;
import com.utils.CmsUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScreenServiceTest {

	@InjectMocks
	private SampleServiceImpl service;

	@Mock
	private SampleMapper mapper;


	@Before
	public void setup(){
		MockitoAnnotations.openMocks(this);

	}

	@Test
	public void doServiceTest() {
		List<T_workHourBean> list = new ArrayList<T_workHourBean>();
		Timestamp timeStame = CmsUtils.FormatToTimestame("2022-02-01 12:12:12.000");
		list.add(createBean("1001", "202202", timeStame, timeStame, timeStame, 8, "f", timeStame, timeStame));
		when(mapper.selectList(new T_workHourBean())).thenReturn(list);

		List<String[]> retList = service.getValues();
		assertEquals(1, retList.size());
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
