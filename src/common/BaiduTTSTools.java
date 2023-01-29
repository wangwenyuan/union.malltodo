package common;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;

public class BaiduTTSTools {

	public static final String Baidu_APP_ID = "16923905";
	public static final String Baidu_API_KEY = "7CuGPtDbADawnRa4WWdl4Xal";
	public static final String Baidu_SECRET_KEY = "A2IVz6XOGzdzP6aHf98aGdbdbhpq5uWq";

	public static String get_call_voice_url(String seat_name, HttpServlet servlet) {
		String dir = "uploads/TTS/";
		String file_path = servlet.getServletContext().getRealPath("/") + dir + seat_name + ".mp3";
		// System.out.println(file_path);
		File file = new File(file_path);
		if (!file.exists()) {
			BaiduTTSTools.create_voice("掌勺者提醒您，" + seat_name + "号桌台呼叫", file_path);
		}
		return "/" + dir + seat_name + ".mp3";
	}

	// servlet.getServletContext().getRealPath("/") + "WEB-INF/log/";
	public static void create_voice(String content, String voice_file) {
		// 初始化一个AipSpeech
		AipSpeech client = new AipSpeech(Baidu_APP_ID, Baidu_API_KEY, Baidu_SECRET_KEY);
		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		// 可选：设置log4j日志输出格式，若不设置，则使用默认配置
		// 也可以直接通过jvm启动参数设置此环境变量
		// System.setProperty("aip.log4j.conf",
		// "path/to/your/log4j.properties");
		// 调用接口
		// 设置可选参数
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("spd", "5");// 语速，取值0-9，默认为5中语速
		options.put("pit", "5");// 音调，取值0-9，默认为5中语调
		options.put("vol", "5");// 音量，取值0-15，默认为5中音量
		options.put("per", "0");// 发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
		TtsResponse res = client.synthesis(content, "zh", 1, options);
		byte[] data = res.getData();
		if (data != null) {
			try {
				Util.writeBytesToFileSystem(data, voice_file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
