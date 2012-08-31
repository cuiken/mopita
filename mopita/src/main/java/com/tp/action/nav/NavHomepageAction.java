package com.tp.action.nav;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.mapper.nav.Button;
import com.tp.mapper.nav.Navigator;

@Namespace("/nav")
public class NavHomepageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {

		File file = new File("c:\\navigator.xml");
		Navigator nav = Navigator.unmarshall(new FileInputStream(file));
		List<Button> btn_tops = nav.getTop().getButtons();
		List<Button> btn_bottoms = nav.getBottom().getButtons();
		List<Button> btn_centerLeft = nav.getLeft().getButtons();
		List<Button> btn_centerRight = nav.getRight().getButtons();
		return SUCCESS;
	}

}
