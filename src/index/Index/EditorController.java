package index.Index;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javatodo.core.controller.Controller;
import com.javatodo.core.tools.T;
import com.javatodo.core.tools.Upload;
import com.javatodo.core.tools.Upload.FileInfo;

import common.MU;
import common.database.FILE;

public class EditorController extends Controller {
	private String admin_id = "";
	private String store_id = "";
	private String uid = "";

	public Boolean init() {
		this.admin_id = session("admin_id").toString();
		this.store_id = session("store_id").toString();
		this.uid = session("uid").toString();
		if ((admin_id == "0" || admin_id.equals("") || admin_id == null) && (store_id == "0" || store_id.equals("") || store_id == null) && (uid == "0" || uid.equals("") || uid == null)) {
			return false;
		} else {
			return true;
		}
	}

	public void indexPage() throws Exception {
		String action = I("action");
		if (action.equals("config")) {
			String config_file = servlet.getServletContext().getRealPath("/") + "Public/editor/editor_config.json";
			String content = T.readFile(config_file, StandardCharsets.UTF_8.toString());
			content = content.replaceAll("/\\/\\*[\\s\\S]+?\\*\\//", "");
			this.jsonDisplay(content);
			return;
		} else if (action.equals("uploadimage")) {
			Upload upload = new Upload(servlet, request);
			List<String> exts = new ArrayList<>();
			exts.add("jpg");
			exts.add("gif");
			exts.add("png");
			exts.add("jpeg");
			exts.add("mp4");
			upload.extList = exts;
			FileInfo fileInfo = upload.uploadOne("upfile");
			if (upload.err_msg.equals("")) {
				Map<String, Object> add_data = new HashMap<>();
				add_data.put(FILE.admin_id, admin_id);
				add_data.put(FILE.store_id, store_id);
				add_data.put(FILE.uid, uid);
				add_data.put(FILE.url, fileInfo.savePath + fileInfo.savename);
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
				this.assign("state", "SUCCESS");
				this.assign("url", fileInfo.savePath + fileInfo.savename);
				this.assign("title", fileInfo.savename);
				this.assign("original", fileInfo.name);
				this.assign("type", fileInfo.type);
				this.assign("size", fileInfo.size);
				this.jsonDisplay();
			} else {
				this.error(upload.err_msg);
			}
		}
	}
}
