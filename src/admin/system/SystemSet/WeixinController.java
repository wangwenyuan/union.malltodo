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
package admin.system.SystemSet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Upload.FileInfo;

import admin.home.Index.CommonController;
import common.Functions;
import common.MU;
import common.database.WEIXIN;
import freemarker.template.TemplateException;

public class WeixinController extends CommonController {
	public void indexPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, Object> info = new MU(WEIXIN._table_name).order(WEIXIN.id + " desc").find();
		if (IS_POST) {
			Map<String, Object> data = Functions.I_TO_MAP(I());
			if (data.containsKey("apiclient_cert")) {
				data.remove("apiclient_cert");
			}
			if (data.containsKey("apiclient_key")) {
				data.remove("apiclient_key");
			}
			if (info == null) {
				new MU(WEIXIN._table_name).data(data).add();
			} else {
				Map<String, W> where = new HashMap<>();
				where.put(WEIXIN.id, new W("eq", info.get(WEIXIN.id)));
				new MU(WEIXIN._table_name).where(where).save(data);
			}
			this.success("设置成功");
		} else {
			String apiclient_cert_path = servlet.getServletContext().getRealPath("/") + "WEB-INF" + "/config/apiclient_cert.p12";
			File apiclient_cert_file = new File(apiclient_cert_path);
			if (apiclient_cert_file.exists()) {
				info.put("apiclient_cert", "已上传");
			}
			this.assign("info", info);
			this.display();
		}
	}

	public void filePage() throws Exception {
		if (session("admin_id").toString().equals("")) {
			this.error("非法进入");
			return;
		}
		List<String> exts = new ArrayList<>();
		exts.add("p12");

		FileInfo fileInfo = null;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);
		factory.setRepository(new File(servlet.getServletContext().getRealPath("/") + "uploadFiles"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024 * 1024 * 5);
		List<FileItem> file_list = upload.parseRequest(request);
		if (file_list != null) {
			Iterator<FileItem> iterator = file_list.iterator();
			while (iterator.hasNext()) {
				FileItem file_item = null;
				String path = null;
				long size = 0;
				file_item = (FileItem) iterator.next();
				if (file_item == null || file_item.isFormField()) {
					continue;
				}
				if (!(file_item.getFieldName().equals("imgFile"))) {
					continue;
				}
				path = file_item.getName();
				size = file_item.getSize();
				String old_file_name = file_item.getName();
				if ("".equals(path) || size == 0) {
					// this.err_msg = "上传的文件为空";
					this.error("上传的文件为空");
					return;
				} else {
					String t_name = path.substring(path.lastIndexOf("\\") + 1);
					String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
					int allowFlag = 0;
					int allowExtCount = exts.size();
					for (; allowFlag < allowExtCount; allowFlag++) {
						if (exts.get(allowFlag).equals(t_ext)) {
							break;
						}
					}
					if (allowFlag == allowExtCount) {
						// this.err_msg = "该类型文件不允许上传";
						this.error("该类型文件不允许上传");
						return;
					} else {
						String saveDir = servlet.getServletContext().getRealPath("/") + "WEB-INF" + "/config/";
						File dir = new File(saveDir);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						file_item.write(new File(saveDir + old_file_name));
						this.assign("error", 0);
						this.assign("url", "上传成功");
						this.jsonDisplay();
					}
				}
			}
		}
	}

	public void managerPage() throws IOException, SQLException {
		JSONObject object = new JSONObject();
		object.put("moveup_dir_path", "");
		object.put("current_dir_path", "");
		object.put("current_url", "");
		object.put("total_count", 0);
		JSONArray array = new JSONArray();
		object.put("file_list", array);
		this.jsonDisplay(object.toJSONString());
	}
}
