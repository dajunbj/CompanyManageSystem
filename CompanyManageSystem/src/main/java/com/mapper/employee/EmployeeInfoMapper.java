package com.mapper.employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.entity.employee.EmployeeInfoBean;

/**
 * ユーザー情報 Mapper
 */
@Mapper
public interface EmployeeInfoMapper {
	/**
	 * ユーザー情報検索
	 * 
	 * @param user 検索用リクエストデータ
	 * @return ユーザー情報
	 */
	List<EmployeeInfoBean> select(EmployeeInfoBean bean);

	/**
	 * ユーザー情報登録
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int insert(EmployeeInfoBean bean);

	/**
	 * ユーザー情報更新
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int update(EmployeeInfoBean bean);

	/**
	 * ユーザー情報削除
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int delete(EmployeeInfoBean bean);
	/**
	 * ユーザー情報検索
	 * 
	 * @param user 検索用リクエストデータ
	 * @return ユーザー情報
	 */
	List<EmployeeInfoBean> selectList(EmployeeInfoBean bean);
}