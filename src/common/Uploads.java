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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.controller.Controller;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;
import com.javatodo.core.tools.Upload;
import com.javatodo.core.tools.Upload.FileInfo;

import common.database.FILE;

public class Uploads extends Controller {
	public void index() throws java.lang.Exception {
		String admin_id = session("admin_id").toString().trim();
		String store_id = session("store_id").toString().trim();
		String uid = session("uid").toString().trim();
		String agent_id = session("agent_id").toString().trim();
		String merchant_id = session("merchant_id").toString().trim();
		if (admin_id.equals("") && store_id.equals("") && uid.equals("") && agent_id.equals("") && merchant_id.equals("")) {
			this.error("无权上传");
			return;
		}
		Upload upload = new Upload(servlet, request);
		List<String> exts = new ArrayList<>();
		exts.add("jpg");
		exts.add("gif");
		exts.add("png");
		exts.add("jpeg");
		exts.add("mp4");
		exts.add("p12");
		upload.extList = exts;
		FileInfo fileInfo = upload.uploadOne("imgFile");

		String pic_url = request.getScheme() + "://" + request.getServerName();
		if (request.getServerPort() != 80 && request.getServerPort() != 443) {
			pic_url = pic_url + ":" + request.getServerPort();
		}
		if (!request.getContextPath().equals("")) {
			pic_url = pic_url + "/" + request.getContextPath();
		}
		pic_url = pic_url + "/";

		if (upload.err_msg.equals("")) {
			Map<String, Object> add_data = new HashMap<>();
			add_data.put(FILE.admin_id, admin_id);
			add_data.put(FILE.store_id, store_id);
			add_data.put(FILE.uid, uid);
			add_data.put(FILE.agent_id, agent_id);
			add_data.put(FILE.merchant_id, merchant_id);
			add_data.put(FILE.url, pic_url + fileInfo.savePath + fileInfo.savename);
			add_data.put(FILE.name, fileInfo.name);
			add_data.put(FILE.ext, fileInfo.ext);
			if (fileInfo.ext.equals("mp4")) {
				add_data.put(FILE.show_pic, "Public/admin/images/video_play.png");
			} else {
				add_data.put(FILE.show_pic, add_data.get(FILE.url));
			}
			add_data.put(FILE.addtime, System.currentTimeMillis());
			add_data.put(FILE.filesize, fileInfo.size);
			new MU(FILE._table_name).data(add_data).add();
			this.assign("error", 0);
			this.assign("url", pic_url + fileInfo.savePath + fileInfo.savename);
			this.jsonDisplay();
		} else {
			this.error(upload.err_msg);
		}
	}

	public void manager() throws IOException, SQLException {
		String admin_id = session("admin_id").toString().trim();
		String store_id = session("store_id").toString().trim();
		String uid = session("uid").toString().trim();
		String agent_id = session("agent_id").toString().trim();
		String merchant_id = session("merchant_id").toString().trim();
		if (admin_id.equals("") && store_id.equals("") && uid.equals("") && agent_id.equals("") && merchant_id.equals("")) {
			this.error("无权进入");
			return;
		}
		Map<String, W> where = new HashMap<>();
		if (!admin_id.equals("")) {
			where.put(FILE.admin_id, new W("eq", admin_id));
		}
		if (!store_id.equals("")) {
			where.put(FILE.store_id, new W("eq", store_id));
		}
		if (!uid.equals("")) {
			where.put(FILE.uid, new W("eq", uid));
		}
		if (!agent_id.equals("")) {
			where.put(FILE.agent_id, new W("eq", agent_id));
		}
		if (!merchant_id.equals("")) {
			where.put(FILE.merchant_id, new W("eq", merchant_id));
		}
		List<Map<String, Object>> list = new MU(FILE._table_name).where(where).order(FILE.id + " desc").limit("0, 100").select();
		JSONObject object = new JSONObject();
		object.put("moveup_dir_path", "");
		object.put("current_dir_path", "");
		object.put("current_url", "");
		object.put("total_count", list.size());
		JSONArray array = new JSONArray();
		for (Integer i = 0; i < list.size(); i = i + 1) {
			JSONObject obj = new JSONObject();
			obj.put("is_dir", false);
			obj.put("has_file", false);
			obj.put("filesize", list.get(i).get(FILE.filesize).toString());
			obj.put("dir_path", "");
			obj.put("is_photo", true);
			obj.put("filetype", list.get(i).get(FILE.ext).toString());
			obj.put("filename", list.get(i).get(FILE.url));
			obj.put("datetime", T.date("yyyy-MM-dd HH:mm:ss", Long.valueOf(list.get(i).get(FILE.addtime).toString())));
			array.add(obj);
		}
		object.put("file_list", array);
		this.jsonDisplay(object.toJSONString());
	}

	public void base64() throws IOException {
		String admin_id = session("admin_id").toString().trim();
		String store_id = session("store_id").toString().trim();
		String uid = session("uid").toString().trim();
		String agent_id = session("agent_id").toString().trim();
		String merchant_id = session("merchant_id").toString().trim();
		if (admin_id.equals("") && store_id.equals("") && uid.equals("") && agent_id.equals("") && merchant_id.equals("")) {
			this.error("无权上传");
			return;
		}
		if (IS_POST) {
			String base64 = I("base64");
			String[] base64_arr = base64.split(";base64,");
			if (base64_arr.length < 2) {
				return;
			}
			String tou = base64_arr[0];
			base64 = base64_arr[1];
			String[] tou_arr = tou.split("/");
			if (tou_arr.length < 2) {
				return;
			}
			String houzhui = tou_arr[1];
			if (!(houzhui.equals("jpg") || houzhui.equals("jpeg") || houzhui.equals("gif") || houzhui.equals("png") || houzhui.equals("mp4"))) {
				this.error("该文件不允许上传");
				return;
			}
			String saveDir = servlet.getServletContext().getRealPath("/") + new Upload(servlet, request).savePath + "/" + T.now("yyyy/MM/dd") + "/";
			File dir = new File(saveDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String file_name = System.currentTimeMillis() + "." + houzhui;
			String filePath = saveDir + file_name;
			String pic_url = request.getScheme() + "://" + request.getServerName();
			if (request.getServerPort() != 80 && request.getServerPort() != 443) {
				pic_url = pic_url + ":" + request.getServerPort();
			}
			if (!request.getContextPath().equals("")) {
				pic_url = pic_url + "/" + request.getContextPath();
			}
			pic_url = pic_url + "/" + new Upload(servlet, request).savePath + "/" + T.now("yyyy/MM/dd") + "/" + file_name;
			BufferedOutputStream bos = null;
			FileOutputStream fos = null;
			File file = null;
			try {
				byte[] bfile = new Base64().decode(base64);
				file = new File(filePath);
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos);
				bos.write(bfile);

				Map<String, Object> add_data = new HashMap<>();
				add_data.put(FILE.admin_id, admin_id);
				add_data.put(FILE.store_id, store_id);
				add_data.put(FILE.uid, uid);
				add_data.put(FILE.agent_id, agent_id);
				add_data.put(FILE.merchant_id, merchant_id);
				add_data.put(FILE.url, pic_url);
				add_data.put(FILE.name, file_name);
				add_data.put(FILE.ext, houzhui);
				if (houzhui.equals("mp4")) {
					add_data.put(FILE.show_pic, "Public/admin/images/video_play.png");
				} else {
					add_data.put(FILE.show_pic, add_data.get(FILE.url));
				}
				add_data.put(FILE.addtime, System.currentTimeMillis());
				add_data.put(FILE.filesize, file.length());
				new MU(FILE._table_name).data(add_data).add();

				this.assign("error", 0);
				this.assign("url", pic_url);
				this.jsonDisplay();
			} catch (Exception e) {
				e.printStackTrace();
				this.assign("error", 1);
				this.assign("msg", e.toString());
				this.jsonDisplay();
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}