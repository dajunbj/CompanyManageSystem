package com.controller.sample;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: wang
 * @Description: CSVツール
 */

public class CSVUtils {
	private static Logger logger = LoggerFactory.getLogger(CSVUtils.class);
	// アップロードファイルのパス
	private final static URL PATH = Thread.currentThread().getContextClassLoader().getResource("");

	/**
	 * @return File
	 * @Description CSVファイル作成
	 * @Param fileName ファイル名，出力ヘッダ内容，明細レコード
	 **/
	public static File makeTempCSV(String fileName, String[] head, List<String[]> values) throws IOException {
		logger.info("makeTempCSV 処理開始");
        //ファイル作成
		File file = File.createTempFile(fileName, ".csv", new File(PATH.getPath()));

		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		CSVPrinter printer = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT);

        //ヘッダ出力
		printer.printRecord(head);
        //CSV明細出力
		for (String[] value : values) {
			printer.printRecord(value);
		}

		printer.close();
		bufferedWriter.close();
		
		logger.info("makeTempCSV 処理終了");
		return file;
	}

	/**
	 * @Description ファイルダウンロード
	 * @Param response，file
	 * @return boolean
	 */
	public static boolean downloadFile(HttpServletResponse response, File file) {
		logger.info("downloadFile 処理開始");
		FileInputStream fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;
		OutputStream os = null;
		try {
			fileInputStream = new FileInputStream(file);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			os = response.getOutputStream();
			// MS产本头部需要插入BOM
			// 如果不写入这几个字节，会导致用Excel打开时，中文显示乱码
			os.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
			byte[] buffer = new byte[1024];
			int i = bufferedInputStream.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bufferedInputStream.read(buffer);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			// 关闭流
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bufferedInputStream != null) {
				try {
					bufferedInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			file.delete();
		}
		logger.info("downloadFile 処理終了");
		return false;
	}

	/**
	 * @Description ファイルアップロード
	 * @Param multipartFile
	 * @return File
	 **/
	public static File uploadFile(MultipartFile multipartFile) {
		logger.info("uploadFile 処理開始");
		String path = PATH.getPath() + multipartFile.getOriginalFilename();
		try {
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			multipartFile.transferTo(file);
			logger.info("ファイルアップロード成功===>" + multipartFile.getOriginalFilename() + ", パス===>" + file.getPath());
			logger.info("downloadFile 処理開始");
			return file;
		} catch (IOException e) {
			logger.error("アップロード失敗" + e.getMessage(), e);
			logger.info("uploadFile 処理終了");
			return null;
		}

	}

	/**
	 * @Description ファイル内容を取り込む
	 * 
	 * @Param filePath ファイルパス
	 * @param colNum 列
	 * @return List<List<String>> 
	 */
	public static List<List<String>> readCSV(String filePath, int colNum) {
		logger.info("readCSV 処理開始");
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		FileInputStream fileInputStream = null;

		try {
			fileInputStream = new FileInputStream(filePath);
			inputStreamReader = new InputStreamReader(fileInputStream);
			bufferedReader = new BufferedReader(inputStreamReader);

			CSVParser parser = CSVFormat.DEFAULT.parse(bufferedReader);
            //解析後のリスト
			List<List<String>> values = new ArrayList<>();
			int rowIndex = 0;

			for (CSVRecord record : parser.getRecords()) {
                //ヘッダをスキップする
				if (rowIndex == 0) {
					rowIndex++;
					continue;
				}
				//行内容を取込む
				List<String> value = new ArrayList<>(colNum + 1);
				for (int i = 0; i < colNum; i++) {
					value.add(record.get(i));
				}
				values.add(value);
				rowIndex++;
			}
			return values;
		} catch (IOException e) {
			logger.error("CSV取込失敗" + e.getMessage(), e);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.info("readCSV 処理終了");
		return null;
	}
}