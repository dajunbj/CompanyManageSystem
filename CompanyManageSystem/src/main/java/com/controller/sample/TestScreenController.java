package com.controller.sample;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.common.UrlConst;
import com.controller.base.ControllerBase;
import com.service.sample.SampleService;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/sample/testscreen")
public class TestScreenController extends ControllerBase {

	@Autowired
	SampleService service;

	public TestScreenController() {
	}

	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model) {

		return UrlConst.GOTO_SAMPLE;
	}

	@RequestMapping(params = "downloadAllUserRoleCSV", method = RequestMethod.POST)
	public String downloadAllUserRoleCSV(HttpServletResponse response,Model model) throws IOException {
		String[] head = new String[] { "社員ID", "勤務年月", "勤務年月日", "開始時間", "終了時間", "勤務時間", "ステータス", "登録年月日", "更新年月日" };
		List<String[]> values = service.getValues();
		
//		values = null;
		if (values == null || values.size() == 0) {
			model.addAttribute("errorMessage", "検索結果がありません。");
			return UrlConst.GOTO_SAMPLE;
		}
		
		String fileName = "downloadfile";
		File file = CSVUtils.makeTempCSV(fileName, head, values);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ".csv");
		
		CSVUtils.downloadFile(response, file);
		return null;
	}

	@RequestMapping(params = "upload", method = RequestMethod.POST)
    public String upload(@RequestParam("upload_file") MultipartFile multipartFile ,Model model) {
        try {

            if (multipartFile.isEmpty()) {
                //ファイル選択チェック
    			model.addAttribute("errorMessage", "ファイルをご選んでください。");
    			return UrlConst.GOTO_SAMPLE;
            }
            
            File file = CSVUtils.uploadFile(multipartFile);
            List<List<String>> csvList = CSVUtils.readCSV(file.getPath(), 9);
            service.registerValues(csvList);
            
            //ファイル削除
            file.delete();
			model.addAttribute("errorMessage", "ファイルがアップロードしました。");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
			return UrlConst.GOTO_SAMPLE;
        }
    }
}