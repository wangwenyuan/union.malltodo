package common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.tools.Http;

public class MicroService {
	// private static String serviceHost = "http://127.0.0.1:9500/";

	private static String serviceHost = "http://api.malltodo.com/";

	private static String getServiceUrl(String serviceName, Map<String, String> getParam) throws UnsupportedEncodingException {
		String url = serviceHost + serviceName;
		String paramString = "";
		for (String key : getParam.keySet()) {
			if (paramString.equals("")) {
				paramString = paramString + "?" + key + "=" + URLEncoder.encode(getParam.get(key), StandardCharsets.UTF_8.toString());
			} else {
				paramString = paramString + "&" + key + "=" + URLEncoder.encode(getParam.get(key), StandardCharsets.UTF_8.toString());
			}
		}
		return url + paramString;
	}

	private static String postToService(String serviceUrl, Map<String, String> postData) throws UnsupportedEncodingException {
		for (String key : postData.keySet()) {
			String value = postData.get(key);
			postData.put(key, URLEncoder.encode(value, StandardCharsets.UTF_8.toString()));
		}
		Http http = new Http();
		return http.post(serviceUrl, postData);
	}

	public static Map<String, JSONObject> getWidgets(String rootUrl, String category) throws UnsupportedEncodingException {
		Map<String, JSONObject> retMap = new HashMap<>();
		Map<String, String> getParam = new HashMap<>();
		getParam.put("domain", rootUrl);
		getParam.put("widgetCategory", category);
		Http http = new Http();
		String jsonHtmlString = http.get(getServiceUrl("getWidgets", getParam));

		if (jsonHtmlString != "") {
			JSONObject allJSON = JSONObject.parseObject(jsonHtmlString);
			for (String key : allJSON.keySet()) {
				retMap.put(key, allJSON.getJSONObject(key));
			}
		}
		return retMap;
	}

	public static JSONObject getBaseWidget(String rootUrl, String widget_category, String widget_name, String shijian, String json) throws UnsupportedEncodingException {
		Map<String, String> getParam = new HashMap<>();
		getParam.put("domain", rootUrl);
		getParam.put("widgetCategory", widget_category);
		getParam.put("widgetName", widget_name);
		if (!shijian.equals("")) {
			getParam.put("timeId", shijian);
		}
		Http http = new Http();
		Map<String, String> postData = new HashMap<>();
		postData.put("jsonString", json);
		String jsonHtmlString = postToService(getServiceUrl("getBaseWidget", getParam), postData);

		if (jsonHtmlString != "") {
			JSONObject retObject = JSONObject.parseObject(jsonHtmlString);
			JSONObject _domObject = retObject.getJSONObject("dom");
			JSONObject dom_object = new JSONObject(new LinkedHashMap<>());
			JSONArray dom_object_index = _domObject.getJSONArray("object-index");
			for (Integer i = 0; i < dom_object_index.size(); i = i + 1) {
				dom_object.put(dom_object_index.getString(i), _domObject.get(dom_object_index.getString(i)));
			}
			JSONArray _javatodo_bind_loop_index = _domObject.getJSONArray("javatodo-bind-loop-index");
			JSONObject _javatodo_bind_loop = _domObject.getJSONObject("javatodo-bind-loop");
			JSONObject javatodo_bind_loop = new JSONObject(new LinkedHashMap<>());
			for (Integer i = 0; i < _javatodo_bind_loop_index.size(); i = i + 1) {
				javatodo_bind_loop.put(_javatodo_bind_loop_index.getString(i), _javatodo_bind_loop.get(_javatodo_bind_loop_index.getString(i)));
			}
			dom_object.put("javatodo-bind-loop", javatodo_bind_loop);
			retObject.put("dom", dom_object);
			return retObject;
		} else {
			return JSONObject.parseObject("{}");
		}
	}

	public static List<String> getBindLoopList(JSONObject dom) throws UnsupportedEncodingException {
		List<String> list = new ArrayList<>();
		Map<String, String> getParam = new HashMap<>();
		Http http = new Http();
		Map<String, String> postData = new HashMap<>();
		postData.put("domJSONObjectString", dom.toString());
		String jsonHtmlString = postToService(getServiceUrl("getBindLoopList", getParam), postData);
		if (jsonHtmlString != "") {
			JSONArray array = JSONArray.parseArray(jsonHtmlString);
			for (Integer i = 0; i < array.size(); i = i + 1) {
				list.add(array.getString(i));
			}
		}
		return list;
	}

	public static String getSystemWidget(String widget_name, String widget_id) throws UnsupportedEncodingException {
		Map<String, String> getParam = new HashMap<>();
		getParam.put("widget_name", widget_name);
		getParam.put("widget_id", widget_id);
		Http http = new Http();
		return http.get(getServiceUrl("getSystemWidget", getParam));
	}

	public static String buildOneWidgetHtmlCSS(String htmlString, String domJSONString, String domsSort, String bindDataMapString, String typeString) throws UnsupportedEncodingException {
		Map<String, String> getParam = new HashMap<>();
		Map<String, String> postData = new HashMap<>();
		postData.put("htmlString", htmlString);
		postData.put("domJSONString", domJSONString);
		postData.put("domsSort", domsSort);
		postData.put("bindDataMapString", bindDataMapString);
		postData.put("typeString", typeString);
		return postToService(getServiceUrl("buildOneWidgetHtmlCSS", getParam), postData);
	}

	public static String parseTemplateMenuDom(String htmlString, String domJSONString, String bindDataJSONString) throws UnsupportedEncodingException {
		Map<String, String> getParam = new HashMap<>();
		Map<String, String> postData = new HashMap<>();
		postData.put("htmlString", htmlString);
		postData.put("domJSONString", domJSONString);
		postData.put("bindDataJSONString", bindDataJSONString);
		return postToService(getServiceUrl("parseTemplateMenuDom", getParam), postData);
	}

	public static String buildHtmlCSSTemplate(String domain, String domsJSONString, String domsSortString) throws UnsupportedEncodingException {
		Map<String, String> getParam = new HashMap<>();
		Map<String, String> postData = new HashMap<>();
		postData.put("domain", domain);
		postData.put("domsJSONString", domsJSONString);
		postData.put("domsSortString", domsSortString);
		return postToService(getServiceUrl("buildHtmlCSSTemplate", getParam), postData);
	}
}
