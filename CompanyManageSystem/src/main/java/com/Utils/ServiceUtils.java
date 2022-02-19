package com.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.common.GeneralMstBean;
import com.mapper.common.GeneralMstMapper;

@Service
public class ServiceUtils {

	@Autowired
	GeneralMstMapper generalMstMapper;

	/**
	 * 選択リストの設定
	 * 
	 * @param key 汎用マスタのキー
	 */
	public Map<String, String> setSelectList(String key) {

		Map<String, String> mp = new HashMap<String, String>();

		for (GeneralMstBean bean : getGeneralMastInfo(key)) {
			mp.put(bean.getGeneralId(), bean.getGeneralName());
		}

		return mp;
	}

	/**
	 * 選択リストの設定
	 * 
	 * @param generalList 汎用マスタのリスト
	 * @param defaultVal  デフォルト値
	 */
	public Map<String, String> setSelectList(String key, String defaultVal) {

		Map<String, String> mp = new HashMap<String, String>();

		// デフォルト値
		mp.put("", defaultVal);
		for (GeneralMstBean bean : getGeneralMastInfo(key)) {
			mp.put(bean.getGeneralId(), bean.getGeneralName());
		}

		return mp;
	}

	/**
	 * 汎用マスタ情報の取得
	 * 
	 * @param key 汎用マスタのキー
	 */
	private List<GeneralMstBean> getGeneralMastInfo(String key) {

		GeneralMstBean inBean = new GeneralMstBean();
		inBean.setGeneralKey(key);

		return generalMstMapper.select(inBean);
	}
}
