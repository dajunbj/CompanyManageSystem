package com.Utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.common.Gender;

public class CmsUtils {

	/**
	 * ラジオボタンの作成
	 * 
	 * @return ラジオボタンの初期値
	 */
	public static List<Gender> createGenders() {
		// 社員区分（ラジオボタン）
		List<Gender> genders = new ArrayList<Gender>() {
			{
				add(Gender.of("radioKbn", "社員", "社員"));
				add(Gender.of("radioKbn", "BP", "BP"));
			}
		};
		return genders;
	}
	
	/**
	 * システム日付を取得する
	 * 
	 * @return ラジオボタンの初期値
	 */
	public static Timestamp getSystemDate() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 文字列を日付にフォーマットする
	 * 
	 * @return フォーマット後の日付
	 */
	public static Date FormatToDate(String dt) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);

		Date retDate = null;
		try {
			retDate = formatter.parse(dt);
		} catch (ParseException e) {
			return null;
		}
	    return retDate;
	}
	
	/**
	 * 文字列を日付にフォーマットする
	 * 
	 * @return フォーマット後の日付
	 */
	public static Timestamp FormatToTimestame(String dt) {

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	    Date parsedDate;
		try {
			parsedDate = dateFormat.parse(dt);
		} catch (ParseException e) {
			return null;
		}
	    return new java.sql.Timestamp(parsedDate.getTime());
	}
}
