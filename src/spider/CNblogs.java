package spider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.javatodo.config.C;
import com.javatodo.core.model.ElasticSearch;

import common.Common;
import common.MU;
import common.database.CNBLOGS_ARTICLES;

public class CNblogs {
	public static List<String> allListUrl = new ArrayList();
	public static Map<String, String> allDetailUrlMap = new HashMap<>();
	public static List<String> cnblogsIdList = new ArrayList<>();

	public static void getAllList() {
		for (Integer i = 0; i < 200; i = i + 1) {
			String url = "https://www.cnblogs.com/cate/all/" + (i + 1);
			allListUrl.add(url);
		}
	}

	public static void getALLDetailUrl(String listPageUrl) throws IOException {
		Document document = Jsoup.connect(listPageUrl).get();
		Elements elements = document.getElementsByClass("post-item");
		for (Integer i = 0; i < elements.size(); i = i + 1) {
			String cnblogs_id = elements.get(i).attr("data-post-id").toString();
			String url = elements.get(i).getElementsByClass("post-item-title").eq(0).attr("href");
			if (cnblogsIdList.contains(cnblogs_id)) {
				continue;
			} else {
				cnblogsIdList.add(cnblogs_id);
				allDetailUrlMap.put(cnblogs_id, url);
			}
		}
	}

	public static void getDetail(String cnblogs_id, String url) throws SQLException {
		try {
			Document document = Jsoup.connect(url).get();
			String title = document.getElementsByTag("title").eq(0).text();
			String[] title_arr = title.split("-");
			String biaoti = "";
			String zuozhe = "";
			if (title_arr.length > 2) {
				zuozhe = title_arr[title_arr.length - 2];
				for (Integer i = 0; i < title_arr.length - 2; i = i + 1) {
					if (biaoti == "") {
						biaoti = title_arr[i];
					} else {
						biaoti = biaoti + "-" + title_arr[i];
					}
				}
			}
			if (document.getElementById("cnblogs_post_body") != null) {
				String detail = document.getElementById("cnblogs_post_body").html();
				Map<String, Object> data = new HashMap<>();
				Integer mathCode = (int) (Math.random() * 89999) + 10000;
				String article_id = System.currentTimeMillis() + "" + mathCode;
				data.put(CNBLOGS_ARTICLES.article_id, article_id);
				data.put(CNBLOGS_ARTICLES.cnblogs_id, cnblogs_id);
				data.put(CNBLOGS_ARTICLES.title, biaoti);
				data.put(CNBLOGS_ARTICLES.author, zuozhe);
				data.put(CNBLOGS_ARTICLES.detail, detail);
				data.put(CNBLOGS_ARTICLES.addtime, System.currentTimeMillis());
				new MU(CNBLOGS_ARTICLES._table_name).data(data).add();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void saveInElasticSearch(ElasticSearch elasticSearch, Map<String, Object> map) {
		if (map.containsKey(CNBLOGS_ARTICLES.id)) {
			map.remove(CNBLOGS_ARTICLES.id);
		}
		String article_id = map.get(CNBLOGS_ARTICLES.article_id).toString();
		map.remove(CNBLOGS_ARTICLES.article_id);
		String detail = map.get(CNBLOGS_ARTICLES.detail).toString();
		detail = Jsoup.parse(detail).text();
		map.put(CNBLOGS_ARTICLES.detail, detail);
		boolean b = elasticSearch.save("articles", article_id, map);
		if (!b) {
			System.out.println(article_id);
		}
	}

	public static void _main() throws SQLException, IOException {
		// 获取所有的cnblogs_id
		List<Map<String, Object>> list = new MU(CNBLOGS_ARTICLES._table_name).field(CNBLOGS_ARTICLES.cnblogs_id).select();
		for (Integer i = 0; i < list.size(); i = i + 1) {
			String cnblogs_id = list.get(i).get(CNBLOGS_ARTICLES.cnblogs_id).toString();
			cnblogsIdList.add(cnblogs_id);
		}
		// 生成所有的列表页链接
		getAllList();
		// 获取所有的内容页链接
		for (Integer i = 0; i < allListUrl.size(); i = i + 1) {
			String url = allListUrl.get(i);
			getALLDetailUrl(url);
		}
		// 获取所有的详情内容
		for (String key : allDetailUrlMap.keySet()) {
			getDetail(key, allDetailUrlMap.get(key));
		}
	}

	public static void main(String[] args) throws IOException, SQLException {
		Common.Init("./WebContent/WEB-INF/config/db.property");
		C.log_file_path = "./WebContent/WEB-INF/Runtime/log/";
		_main();
	}
}
