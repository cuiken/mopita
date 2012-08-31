package com.tp.mapper.nav;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class Bottom {
	private String template;

	@XmlAttribute(name = "template", required = true)
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	@XmlElementWrapper(name = "Buttons")
	@XmlElement
	public ArrayList<Button> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<Button> buttons) {
		this.buttons = buttons;
	}

	ArrayList<Button> buttons;
}
