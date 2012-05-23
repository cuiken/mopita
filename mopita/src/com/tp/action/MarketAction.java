package com.tp.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.tp.entity.Market;
import com.tp.service.MarketManager;
import com.tp.utils.Struts2Utils;

@Namespace("/category")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "market.action", type = "redirect") })
public class MarketAction extends CRUDActionSupport<Market> {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Market entity;
	private List<Market> markets;
	private MarketManager marketManager;

	@Override
	public String delete() throws Exception {

		return RELOAD;
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}

	@Override
	public String list() throws Exception {
		markets = marketManager.getAll();
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id == null) {
			entity = new Market();
		} else {
			entity = marketManager.get(id);
		}

	}

	@Override
	public String save() throws Exception {
		marketManager.save(entity);
		return RELOAD;
	}

	public String checkMarketName() throws Exception {
		String oldName = new String(Struts2Utils.getParameter("oldName").getBytes("iso-8859-1"), "utf-8");
		String newName = new String(Struts2Utils.getParameter("name").getBytes("iso-8859-1"), "utf-8");
		if (marketManager.isNameUnique(newName, oldName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		return null;
	}

	public String manage() throws Exception {
		return "manage";
	}

	@Override
	public Market getModel() {

		return entity;
	}

	public List<Market> getMarkets() {
		return markets;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	public void setMarketManager(MarketManager marketManager) {
		this.marketManager = marketManager;
	}

}
