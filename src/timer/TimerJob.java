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
package timer;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.javatodo.config.C;
import com.javatodo.core.tools.T;

import common.Common;
import union.Jd;
import union.Pdd;

public class TimerJob implements ServletContextListener {
	public static Timer timer = null;

	public static Integer month = 0;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		// 服务销毁
		ServletContext sct = arg0.getServletContext();
		String path = sct.getRealPath("/");
		T.writeFile(path + "WEB-INF/Runtime/log/timer.log", "服务退出--------" + T.now() + "\n");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		// 服务初始化
		ServletContext sct = arg0.getServletContext();
		String path = sct.getRealPath("/");
		Common.Init(path + "WEB-INF/config/db.property");
		C.log_file_path = path + "WEB-INF/Runtime/log/";
		// 首先初始化一个TimerTask的匿名子类
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (C.db_host.length == 0) {
					return;
				} else {
					long time = System.currentTimeMillis();
					String start_time = T.date("yyyy-MM-dd HH:mm:ss", time - 15 * 60 * 1000);
					String end_time = T.date("yyyy-MM-dd HH:mm:ss", time + 15 * 60 * 1000);
					// 获取京东订单
					new Thread(new Runnable() {
						public void run() {
							Jd jd = new Jd();
							jd.getOrderList(start_time, end_time, 1, 3);
						}
					}).start();
					// 判断是否运行京东结算单，每月21日运行一次
					Integer M = T.toInt(T.date("M", time));
					Integer d = T.toInt(T.date("d", time));
					if (M != TimerJob.month && d > 24) {
						TimerJob.month = M;
						new Thread(new Runnable() {
							public void run() {
								Jd jd = new Jd();
								jd.getSettlementOrderList(1);
							}
						}).start();
					}
					// 获取拼多多订单
					new Thread(new Runnable() {
						public void run() {
							Pdd pdd = new Pdd();
							pdd.getOrderList(start_time, end_time, 1);
						}
					}).start();
				}
			}
		}; // 通过Timer调用
		TimerJob.timer = new Timer();
		timer.schedule(task, 60 * 1000, 60 * 1000);
	}

}
