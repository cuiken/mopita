package com.tp.action.nav;

import java.io.File;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.tp.action.CRUDActionSupport;
import com.tp.dao.HibernateUtils;
import com.tp.entity.nav.Board;
import com.tp.entity.nav.NavigatorIcon;
import com.tp.entity.nav.Tag;
import com.tp.entity.nav.Navigator;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;
import com.tp.service.nav.NavigatorService;
import com.tp.utils.Constants;
import com.tp.utils.FileUtils;
import com.tp.utils.Struts2Utils;
import com.tp.utils.UUIDGenerator;

@Namespace("/nav")
@Results({ @Result(name = CRUDActionSupport.RELOAD, location = "navigator.action", type = "redirect") })
public class NavigatorAction extends CRUDActionSupport<Navigator> {

	private static final long serialVersionUID = 1L;

	private Navigator entity;
	private Long id;
	private File upload;
	private NavigatorService navigatorService;

	private List<Long> checkedTagIds;
	private List<Long> checkedBoardIds;

	private Page<Navigator> page = new Page<Navigator>();
	private List<Integer> sliders = Lists.newArrayList();

	@Override
	public Navigator getModel() {

		return entity;
	}

	@Override
	public String list() throws Exception {

		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		PropertyFilter extraFileter=new PropertyFilter("EQS_status", "enabled");
		filters.add(extraFileter);
		page = navigatorService.searchNavigator(page, filters);
		sliders = page.getSlider(10);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		checkedBoardIds = entity.getCheckedBoardIds();
		checkedTagIds = entity.getCheckedTagIds();
		return INPUT;
	}

	@Override
	public String save() throws Exception {

		HibernateUtils.mergeByCheckedIds(entity.getBoards(), checkedBoardIds, Board.class);
		HibernateUtils.mergeByCheckedIds(entity.getTags(), checkedTagIds, Tag.class);
		navigatorService.saveNav(entity);
		if (upload != null) {
			List<File> icons = FileUtils.unZip(upload, Constants.NAV_STORAGE);
			for (File file : icons) {
				entity.getIcons().clear();
				NavigatorIcon icon = new NavigatorIcon();
				icon.setNavigator(entity);
				icon.setName(file.getName());
				icon.setValue(file.getPath());
				icon.setLevel(FileUtils.getIconLevel(file.getName()));
				navigatorService.saveNavIcon(icon);
			}
		}

		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		entity = navigatorService.getNav(id);
		entity.setStatus("disabled");
		navigatorService.saveNav(entity);
		return RELOAD;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id == null) {
			entity = new Navigator();
			entity.setStatus("enabled");
			entity.setUuid(UUIDGenerator.randomLong());
		} else {
			entity = navigatorService.getNav(id);
		}

	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Board> getAllBoards() {
		return navigatorService.getAllBoards();
	}

	public List<Tag> getAllTags() {
		return navigatorService.getAllTags();
	}

	public List<Long> getCheckedTagIds() {
		return checkedTagIds;
	}

	public void setCheckedTagIds(List<Long> checkedTagIds) {
		this.checkedTagIds = checkedTagIds;
	}

	public List<Long> getCheckedBoardIds() {
		return checkedBoardIds;
	}

	public void setCheckedBoardIds(List<Long> checkedBoardIds) {
		this.checkedBoardIds = checkedBoardIds;
	}

	public List<Integer> getSliders() {
		return sliders;
	}

	public Page<Navigator> getPage() {
		return page;
	}

	@Autowired
	public void setNavigatorService(NavigatorService navigatorService) {
		this.navigatorService = navigatorService;
	}
}
