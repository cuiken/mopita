package com.tp.action.nav;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.nav.Board;
import com.tp.service.nav.NavigatorService;
import com.tp.utils.Struts2Utils;
import com.tpadsz.navigator.NavigatorProvider;
import com.tpadsz.navigator.entity.Bottom;
import com.tpadsz.navigator.entity.CenterLeft;
import com.tpadsz.navigator.entity.CenterRight;
import com.tpadsz.navigator.entity.Navigator;
import com.tpadsz.navigator.entity.Top;

@Namespace("/nav")
public class NavHomepageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Top tops;
	private Bottom bottom;
	private CenterLeft centerLeft;
	private CenterRight centerRight;
	private List<Board> boards;
	private NavigatorService navigatorService;

	@Override
	public String execute() throws Exception {

		String imei = Struts2Utils.getParameter("imei");
		String imsi = Struts2Utils.getParameter("imsi");

		Map<String, String> users = Maps.newHashMap();
		users.put("imei", imei);
		users.put("imsi", imsi);
		NavigatorProvider np = new NavigatorProvider();
		Navigator nav = np.getNavigator(users);

		tops = nav.getTop();
		bottom = nav.getBottom();
		centerLeft = nav.getLeft();
		centerRight = nav.getRight();
		return SUCCESS;
	}

	public String more() throws Exception {
		boards = navigatorService.getAllBoards();
		return "more";
	}

	public Top getTops() {
		return tops;
	}

	public Bottom getBottom() {
		return bottom;
	}

	public CenterLeft getCenterLeft() {
		return centerLeft;
	}

	public CenterRight getCenterRight() {
		return centerRight;
	}

	public List<Board> getBoards() {
		return boards;
	}

	@Autowired
	public void setNavigatorService(NavigatorService navigatorService) {
		this.navigatorService = navigatorService;
	}
}
