package com.mapper.sample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.entity.sample.T_workHourBean;

/**
 * サンプル用Mapper
 */
@Mapper
public interface SampleMapper {

	/**
	 * ユーザー情報検索
	 * 
	 * @param user 検索用リクエストデータ
	 * @return ユーザー情報
	 */
	List<T_workHourBean> selectList(T_workHourBean bean);
	void insertRecords(List<T_workHourBean> records);
}