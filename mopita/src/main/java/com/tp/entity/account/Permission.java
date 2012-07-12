package com.tp.entity.account;

import java.util.Map;

import com.google.common.collect.Maps;

public enum Permission {

	USER_VIEW("user:view", "查看用戶"), USER_EDIT("user:edit", "修改用户"),

	GROUP_VIEW("group:view", "查看权限组"), GROUP_EDIT("group:edit", "修改权限组"),

	FILE_VIEW("file:view", "查看文件"), FILE_EDIT("file:edit", "修改文件"),

	STORE_VIEW("store:view", "查看商店"), STORE_EDIT("store:edit", "修改商店"), 
	
	SHELF_VIEW("shelf:view", "查看货架"), SHELF_EDIT("shelf:edit", "修改货架"),
	
	MARKET_VIEW("market:view", "查看市场"), MARKET_EDIT("market:edit", "修改市场"),
	
	CATEGORY_VIEW("category:view","查看分类"),CATEGORY_EDIT("category:edit","修改分类");

	private static Map<String, Permission> valueMap = Maps.newHashMap();

	public String value;
	public String displayName;

	static {
		for (Permission permission : Permission.values()) {
			valueMap.put(permission.value, permission);
		}
	}

	Permission(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static Permission parse(String value) {
		return valueMap.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}
}
