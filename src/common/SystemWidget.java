/**
 * malltodo
 * ============================================================================
 * * 版权所有 2021-2071 郑州掌勺信息技术有限公司，并保留所有权利。
 * 网站地址: http://www.malltodo.com
 * ----------------------------------------------------------------------------
 * 这不是一个自由软件！您只能在不用于商业目的的前提下对程序代码进行修改和使用 .
 * 不允许对程序代码以任何形式任何目的的再发布。
 * 如果商业用途务必到官方购买正版授权, 以免引起不必要的法律纠纷.
 * ============================================================================
 * 郑州掌勺信息技术有限公司 2021-09-01
 * 业务电话：13598851835（微信同号） 
 */
package common;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.tools.T;

public class SystemWidget {

	public static String buildSystemWidgetPage(String root, JSONObject object) throws IOException {
		String html = "";
		for (String key : object.keySet()) {
			if (key.trim().equals("category")) {
				continue;
			}
			if (key.trim().equals("name")) {
				continue;
			}
			if (key.trim().equals("sign")) {
				continue;
			}
			if (key.trim().equals("javatodo-bind-param")) {
				continue;
			}
			if (!key.trim().equals("javatodo-bind-loop")) {
				JSONObject obj = object.getJSONObject(key);
				if (obj.getString("type").equals("javatodo-style")) {
					String style = obj.getString("javatodo-style");
					String[] styles = style.split("\\|");
					for (Integer i = 0; i < styles.length; i = i + 1) {
						html = html + getSystemWidget(root, styles[i], key);
					}
				}
				if (obj.getString("type").equals("javatodo-text")) {
					html = html + getSystemWidget(root, "content", key);
				}
				if (obj.getString("type").equals("javatodo-loop")) {
					html = html + getSystemWidget(root, "loop", key);
				}
				if (obj.getString("type").equals("javatodo-attr")) {
					html = html + getSystemWidget(root, obj.getString("javatodo-attr"), key);
				}
			} else {
				JSONObject bind_loop_json = object.getJSONObject(key);
				for (String k : bind_loop_json.keySet()) {
					html = html + getSystemWidget(root, "bind_loop", k);
				}
			}
		}
		return html;
	}

	public static String getSystemWidget(String root, String widget_name, String widget_id) throws IOException {
		String html = "";
		try {
			Class<?> widget = Class.forName(Common.template.getClassName());
			if (null == widget) {
				return null;
			}
			Object widget_object = widget.newInstance();
			Class<?>[] widget_args = { String.class, String.class, String.class };
			Object[] args = { root, widget_name, widget_id };
			Method get_widget = widget.getMethod("getSystemWidget", widget_args);
			html = (String) get_widget.invoke(widget_object, args);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO: handle exception
			e.printStackTrace();
			T.javatodo_error_log(e);
		}
		return html;
	}
}
