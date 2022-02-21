package com.service.sample;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Utils.CmsUtils;
import com.entity.sample.T_workHourBean;
import com.mapper.sample.SampleMapper;
import com.temp.TestLog;

/**
 * サンプル情報 Service
 */
@Service
public class SampleServiceImpl implements SampleService {

	public static final Logger logger = LogManager.getLogger(TestLog.class);

	@Autowired
	SampleMapper mapper;

	@Override
	public List<String[]> getValues() {
		logger.info("getValues 処理開始");
		T_workHourBean param = new T_workHourBean();
		List<T_workHourBean> results = mapper.selectList(param);

		List<String[]> retList = new ArrayList<String[]>();
		String[] strArr;
		for (T_workHourBean bean : results) {
			strArr = new String[] { bean.getEmployeeId(), 
					bean.getWorkMonth(), 
					String.valueOf(bean.getWorkDay()),
					String.valueOf(bean.getStartTime()), 
					String.valueOf(bean.getEndTime()),
					String.valueOf(bean.getWorkHous()), 
					bean.getStatus(), 
					String.valueOf(bean.getRegistrationDate()),
					String.valueOf(bean.getUpdateDate()) };
			retList.add(strArr);
		}
		logger.info("getValues 処理終了");
		return retList;
	}

	@Override
	public int registerValues(List<List<String>> records) {
		logger.info("registerValues 処理開始");

		T_workHourBean insBean = new T_workHourBean();
		List<T_workHourBean> insList = new ArrayList<T_workHourBean>();
		for (List<String> rec : records) {
			insBean = new T_workHourBean();
			insBean.setEmployeeId(rec.get(0));
			insBean.setWorkMonth(rec.get(1));
			insBean.setWorkDay(CmsUtils.FormatToTimestame(rec.get(2)));
			insBean.setStartTime(CmsUtils.FormatToTimestame(rec.get(3)));
			insBean.setEndTime(CmsUtils.FormatToTimestame(rec.get(4)));
			insBean.setWorkHous(Integer.valueOf(rec.get(5)));
			insBean.setStatus(rec.get(6));
			insBean.setRegistrationDate(CmsUtils.FormatToTimestame(rec.get(7)));
			insBean.setUpdateDate(CmsUtils.FormatToTimestame(rec.get(8)));
			insList.add(insBean);
		}
		mapper.insertRecords(insList);
		logger.info("registerValues 処理終了");
		return 0;
	}
}