package com.tp.action.nav;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.nav.Board;
import com.tp.service.nav.ButtonSourceAdapter;
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
	private static final long ONE_WEEK_MILLI_SECONDS = 7 * 24 * 60 * 60 * 1000;
	private Top tops;
	private Bottom bottom;
	private CenterLeft centerLeft;
	private CenterRight centerRight;
	private List<Board> boards;
	private NavigatorService navigatorService;
	private ButtonSourceAdapter buttonAdapter;

	@Override
	public String execute() throws Exception {

		String imei = Struts2Utils.getParameter("imei");
		String imsi = Struts2Utils.getParameter("imsi");

		Map<String, String> users = Maps.newHashMap();
		if (imei != null)
			users.put("imei", imei);
		if (imsi != null)
			users.put("imsi", imsi);
		NavigatorProvider np = new NavigatorProvider();
		np.setButtonClickSource(buttonAdapter);
		np.setStaticsTimeLimit(ONE_WEEK_MILLI_SECONDS);
		Navigator nav = np.getNavigator(users);
		Struts2Utils.getSession().setAttribute("users", users);
		tops = (Top) nav.getTop();
		bottom = nav.getBottom();
		centerLeft = nav.getLeft();
		centerRight = nav.getRight();
		return SUCCESS;
	}

	public String more() throws Exception {
		boards = navigatorService.getAllBoards();

		return "more";
	}

	public String toXml() throws Exception {

		String xml = navigatorService.toXml();
		Struts2Utils.renderXml(xml);
		return null;
	}

	@Deprecated
	public String demo() throws Exception {
		boards = navigatorService.getAllBoards();
		String btnId = Struts2Utils.getParameter("bid");
		if (btnId == null || btnId.isEmpty())
			btnId = "0";
		@SuppressWarnings("unchecked")
		Map<String, String> users = (Map<String, String>) Struts2Utils.getSessionAttribute("users");

		buttonAdapter.logClick(users, Long.valueOf(btnId));
		return "demo";
	}

	public String logClick() throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String> users = (Map<String, String>) Struts2Utils.getSessionAttribute("users");
		String btnId = Struts2Utils.getParameter("id");
		if (btnId == null || btnId.isEmpty())
			btnId = "10000000";
		buttonAdapter.logClick(users, Long.valueOf(btnId));
		Struts2Utils.renderText("success");
		return null;
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

	@Autowired
	public void setButtonAdapter(ButtonSourceAdapter buttonAdapter) {
		this.buttonAdapter = buttonAdapter;
	}
}
