package com.tp.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.service.XmlService;
import com.tp.utils.Encodes;
import com.tp.utils.Struts2Utils;

@Namespace("/service")
public class XmlServiceAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private XmlService xmlService;

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String domain = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + request.getContextPath();
		String store = Struts2Utils.getParameter("st");
		String language = Struts2Utils.getParameter("l");
		String xml = xmlService.getXml(store, language, domain);
		Struts2Utils.renderText(Encodes.encodeBase64(xml.getBytes()));

		return null;
	}

	public String getXml() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String domain = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + request.getContextPath();
		String store = Struts2Utils.getParameter("st");
		String language = Struts2Utils.getParameter("l");
		String xml = xmlService.getXml(store, language, domain);

		Struts2Utils.renderXml(xml);
		return null;
	}

	@Autowired
	public void setXmlService(XmlService xmlService) {
		this.xmlService = xmlService;
	}

}
