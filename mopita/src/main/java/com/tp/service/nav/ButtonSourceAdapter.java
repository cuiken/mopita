package com.tp.service.nav;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tp.entity.nav.Board;
import com.tp.entity.nav.BoardIcon;
import com.tp.entity.nav.ClickLog;
import com.tp.entity.nav.Navigator;
import com.tp.entity.nav.NavigatorIcon;
import com.tp.entity.nav.Tag;
import com.tp.entity.nav.TagIcon;
import com.tp.utils.Constants;
import com.tpadsz.navigator.IButtonSourceAdapter;
import com.tpadsz.navigator.entity.Button;

@Component
public class ButtonSourceAdapter implements IButtonSourceAdapter {

	private static final long ONE_WEEK_MILLI_SECONDS = 7 * 24 * 60 * 60 * 1000;
	private NavigatorService navigatorService;
	private ClickLogService clickLogService;

	@Override
	public Map<Button, Integer> getAllClicksOfClass(Integer cid, String userId, Long millis) {

		Board board = navigatorService.getBoard(cid.longValue());
		Map<Button, Integer> maps = Maps.newHashMap();
		List<Tag> tags = board.getTags();

		List<Navigator> navis = board.getNavigators();

		maps.putAll(getTag(tags, userId));
		maps.putAll(getNavigator(navis, userId));
		return maps;
	}

	private void setPicSize(HashMap<String, String> picMaps, String key, String value) {

		if (key.equals("1")) {
			picMaps.put("1x1", value);
		} else if (key.equals("2")) {
			picMaps.put("2x1", value);
		} else if (key.equals("4")) {
			picMaps.put("2x2", value);
		} else if (key.equals("6")) {
			picMaps.put("3x2", value);
		}

	}

	private Map<Button, Integer> getTag(List<Tag> tags, String userId) {
		Map<Button, Integer> btns = Maps.newHashMap();

		for (Tag tag : tags) {
			HashMap<String, String> picMaps = Maps.newHashMap();
			Button btn = new Button();
			btn.setId(tag.getUuid());
			btn.setTitle(tag.getName());
			btn.setAction(Constants.getDomain() + "/nav/homemore?bid=" + tag.getUuid());
			btn.setValue(tag.getValue());
			for (TagIcon icon : tag.getIcons()) {
				setPicSize(picMaps, icon.getLevel(), icon.getValue());
			}
			btn.setPictures(picMaps);
			int clicks = clickLogService.countButtonClick(tag.getUuid(), userId).intValue();
			clicks = clicks + navigatorClick(tag, userId);
			if (clicks != 0)
				btns.put(btn, clicks);
		}
		return btns;
	}

	private int navigatorClick(Tag tag, String userId) {
		List<Navigator> navis = tag.getNavigators();
		int clicks = 0;
		for (Navigator nav : navis) {
			clicks += clickLogService.countButtonClick(nav.getUuid(), userId).intValue();
		}
		return clicks;
	}

	private Map<Button, Integer> getNavigator(List<Navigator> navis, String userId) {
		Map<Button, Integer> btns = Maps.newHashMap();

		for (Navigator nav : navis) {
			HashMap<String, String> picMaps = Maps.newHashMap();
			Button btn = new Button();
			btn.setId(nav.getUuid());
			btn.setTitle(nav.getName());
			btn.setAction(nav.getNavAddr());
			for (NavigatorIcon icon : nav.getIcons()) {

				setPicSize(picMaps, icon.getLevel(), icon.getValue());
				btn.setPictures(picMaps);
			}
			int clicks = clickLogService.countButtonClick(nav.getUuid(), userId).intValue();
			if (clicks != 0)
				btns.put(btn, clicks);
		}
		return btns;
	}

	@Override
	public Map<Button, Integer> getAllNewsButtonClicks(String userId, Long millis) {
		millis = ONE_WEEK_MILLI_SECONDS;
		Board board = navigatorService.getBoardByValue("news");
		return getAllClicksOfClass(board.getId().intValue(), userId, millis);
	}

	@Override
	public Map<Button, Integer> getAllShoppingButtonClicks(String userId, Long millis) {
		millis = ONE_WEEK_MILLI_SECONDS;
		Board board = navigatorService.getBoardByValue("shop");
		return getAllClicksOfClass(board.getId().intValue(), userId, millis);
	}

	@Override
	public Map<Button, Integer> getAllTravelingButtonClicks(String userId, Long millis) {
		millis = ONE_WEEK_MILLI_SECONDS;
		Board board = navigatorService.getBoardByValue("travel");
		return getAllClicksOfClass(board.getId().intValue(), userId, millis);
	}

	@Autowired
	public void setNavigatorService(NavigatorService navigatorService) {
		this.navigatorService = navigatorService;
	}

	@Autowired
	public void setClickLogService(ClickLogService clickLogService) {
		this.clickLogService = clickLogService;
	}

	@Override
	public void logClick(Map<String, String> userIds, Long btnId) {

		if (userIds != null && !userIds.isEmpty()) {
			String imei = userIds.get("imei");
			if (imei == null || imei.isEmpty()) {
				imei = userIds.get("imsi");
			}

			ClickLog log = new ClickLog();
			log.setButtonId(btnId.longValue());
			log.setUserId(imei);
			clickLogService.saveClickLog(log);
		} else {
			ClickLog log = new ClickLog();
			log.setButtonId(btnId.longValue());
			log.setUserId("00000000");
			clickLogService.saveClickLog(log);
		}
	}

	@Override
	public List<Button> getRandom4News() {
		Board board = navigatorService.getBoardByValue("news");
		return random(board);

	}

	@Override
	public List<Button> getRandom4Shopping() {
		Board board = navigatorService.getBoardByValue("shop");
		return random(board);
	}

	@Override
	public List<Button> getRandom4Traveling() {
		Board board = navigatorService.getBoardByValue("travel");
		return random(board);
	}

	@Override
	public Button getNewsButton(String temp) {

		return getButton("news");
	}

	@Override
	public Button getShoppingButton(String temp) {

		return getButton("shop");
	}

	@Override
	public Button getTravelingButton(String temp) {

		return getButton("travel");
	}

	@Override
	public Button getReadingButton(String temp) {

		return getButton("read");
	}

	@Override
	public List<Button> getRandom4Reading() {
		Board board = navigatorService.getBoardByValue("read");
		return random(board);
	}

	@Override
	public boolean isShoppingHotterThanTraveling(String userId) {
		if (userId == null || userId.equals(""))
			return true;
		Map<Button, Integer> shop = getAllShoppingButtonClicks(userId, ONE_WEEK_MILLI_SECONDS);
		Map<Button, Integer> trave = getAllTravelingButtonClicks(userId, ONE_WEEK_MILLI_SECONDS);
		int shopClicks = 0;
		int traveClicks = 0;
		for (Entry<Button, Integer> e : shop.entrySet()) {
			shopClicks += e.getValue();
		}
		for (Entry<Button, Integer> e : trave.entrySet()) {
			traveClicks += e.getValue();
		}
		return shopClicks >= traveClicks;
	}

	@Override
	public List<Button> getBottom(String userId, Long millis) {
		Board board = navigatorService.getBoardByValue("more");

		return getAllByBoard(board);
	}

	@Override
	public List<Button> getRandomBottom() {
		Board board = navigatorService.getBoardByValue("more");
		return random(board);
	}

	@Override
	public Map<Button, Integer> getAllReadingButtonClicks(String userId, Long millis) {
		millis = ONE_WEEK_MILLI_SECONDS;
		Board board = navigatorService.getBoardByValue("read");
		return getAllClicksOfClass(board.getId().intValue(), userId, millis);
	}

	private Button getButton(String btnName) {
		HashMap<String, String> picMaps = Maps.newHashMap();
		Board board = navigatorService.getBoardByValue(btnName);
		Button button = new Button();
		button.setId(board.getUuid());
		button.setTitle(btnName);
		button.setAction(Constants.getDomain() + "/nav/homemore?bid=" + board.getUuid());
		button.setValue(board.getValue());
		for (BoardIcon icon : board.getIcons()) {
			setPicSize(picMaps, icon.getLevel(), icon.getValue());
		}
		button.setPictures(picMaps);
		return button;
	}

	private Button getTagButton(String btnName) {
		HashMap<String, String> picMaps = Maps.newHashMap();
		Tag tag = navigatorService.getTagByValue(btnName);
		Button button = new Button();
		button.setId(tag.getUuid());
		button.setTitle(btnName);
		button.setAction(Constants.getDomain() + "/nav/homemore?bid=" + tag.getUuid());
		button.setValue(tag.getValue());
		for (TagIcon icon : tag.getIcons()) {
			setPicSize(picMaps, icon.getLevel(), icon.getValue());
		}
		button.setPictures(picMaps);
		return button;
	}

	private List<Button> getAllByBoard(Board board) {
		List<Button> buttons = Lists.newArrayList();

		List<Tag> tags = board.getTags();

		for (Tag tag : tags) {
			HashMap<String, String> picMaps = Maps.newHashMap();
			Button button = new Button();
			button.setId(tag.getUuid());
			button.setTitle(tag.getName());
			button.setAction(Constants.getDomain() + "/nav/homemore?bid=" + tag.getUuid());
			button.setValue(tag.getValue());
			for (TagIcon icon : tag.getIcons()) {
				setPicSize(picMaps, icon.getLevel(), icon.getValue());
			}
			button.setPictures(picMaps);
			buttons.add(button);
		}
		List<Navigator> navs = board.getNavigators();
		for (Navigator nav : navs) {
			HashMap<String, String> picMaps1 = Maps.newHashMap();
			Button button = new Button();
			button.setId((nav.getUuid()));
			button.setTitle(nav.getName());
			button.setAction(nav.getNavAddr());

			for (NavigatorIcon icon : nav.getIcons()) {
				setPicSize(picMaps1, icon.getLevel(), icon.getValue());
			}
			button.setPictures(picMaps1);
			buttons.add(button);
		}

		return buttons;
	}

	private List<Button> random(Board board) {
		List<Button> buttons = getAllByBoard(board);

		if (buttons.size() > 4) {
			Collections.shuffle(buttons);
			return buttons.subList(0, 4);
		}

		return buttons;
	}

	@Override
	public List<Button> getDefaultNews() {
		List<Button> ret = new ArrayList<Button>(2);
		Button btnPE = getTagButton("sports");
		Button btnEnt = getTagButton("entertainment");

		btnPE.setPicture(btnPE.getPictures().get("1x1"));
		btnEnt.setPicture(btnEnt.getPictures().get("1x1"));
		ret.add(btnPE);
		ret.add(btnEnt);

		return ret;
	}

	@Override
	public List<Button> getDefaultBottom() {
		List<Button> ret = new ArrayList<Button>(4);
		Button btnDate = getTagButton("friends");
		Button btnFood = getTagButton("food");
		Button btnMusic = getTagButton("music");
		Button btnGame = getTagButton("game");

		btnDate.setPicture(btnDate.getPictures().get("1x1"));
		btnFood.setPicture(btnFood.getPictures().get("1x1"));
		btnMusic.setPicture(btnDate.getPictures().get("1x1"));
		btnGame.setPicture(btnDate.getPictures().get("1x1"));

		ret.add(btnDate);
		ret.add(btnFood);
		ret.add(btnMusic);
		ret.add(btnGame);

		return ret;
	}

	@Override
	public List<Button> getDefaultReading() {
		List<Button> ret = new ArrayList<Button>(2);
		Button btnPE = getTagButton("funny");
		Button btnEnt = getTagButton("novel");

		btnPE.setPicture(btnPE.getPictures().get("1x1"));
		btnEnt.setPicture(btnEnt.getPictures().get("1x1"));
		ret.add(btnPE);
		ret.add(btnEnt);

		return ret;
	}

	@Override
	public List<Button> getDefaultShopping() {
		List<Button> ret = new ArrayList<Button>(0);
		return ret;
	}
}
