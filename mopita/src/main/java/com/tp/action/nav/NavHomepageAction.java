package com.tp.action.nav;

import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;

import com.google.common.collect.Maps;
import com.opensymphony.xwork2.ActionSupport;
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

	private Top top;
	private Bottom bottom;
	private CenterLeft centerLeft;
	private CenterRight centerRight;

	@Override
	public String execute() throws Exception {

		String imei = Struts2Utils.getParameter("imei");
		String imsi = Struts2Utils.getParameter("imsi");

		Map<String, String> users = Maps.newHashMap();
		users.put("imei", imei);
		users.put("imsi", imsi);
		NavigatorProvider np = new NavigatorProvider();
		Navigator nav = np.getNavigator(users);

		top = nav.getTop();
		bottom = nav.getBottom();
		centerLeft = nav.getLeft();
		centerRight = nav.getRight();
		return SUCCESS;
	}

	public Top getTop() {
		return top;
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

}
