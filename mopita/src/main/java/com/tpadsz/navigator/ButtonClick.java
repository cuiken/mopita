package com.tpadsz.navigator;

import com.tpadsz.navigator.entity.Button;

public class ButtonClick implements Comparable<ButtonClick> {
	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public Integer getClicks() {
		return clicks;
	}

	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}

	public ButtonClick(Button button, Integer clicks) {
		super();
		this.button = button;
		this.clicks = clicks;
	}

	Button button;
	Integer clicks;

	public int compareTo(ButtonClick o) {
		return this.clicks.compareTo(o.clicks);
	}
}
