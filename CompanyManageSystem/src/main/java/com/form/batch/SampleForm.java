package com.form.batch;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.entity.sample.T_workHourBean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * ユーザー情報 検索用リクエストデータ
 */
@Getter
@Setter
@Data
public class SampleForm {


	@NotEmpty
	private String batchId;
	
	/* 検索結果 */
	private List<T_workHourBean> results;

}