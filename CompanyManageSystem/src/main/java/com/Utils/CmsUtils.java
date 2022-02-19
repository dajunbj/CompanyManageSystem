package com.Utils;

import java.util.ArrayList;
import java.util.List;

import com.common.Gender;

public class CmsUtils {

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
}
