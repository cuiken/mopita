package com.tp.entity.unlock;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.tp.entity.Category;
import com.tp.entity.FileInfo;
import com.tp.entity.FileStoreInfo;
import com.tp.entity.IdEntity;
import com.tp.entity.ShelfFileLink;
import com.tp.entity.unlock.FileType.*;

@Entity
@Table(name = "f_unlock")
public class UnlockFile extends IdEntity {

	private String title;
	private String marketURL;
	private String availMachine;
	private String unavailMachine;
	private Date createTime;

	private Apk apk;
	private Ux ux;
	private Icon icon;
	private PrevieWeb pw;
	private PreviewClient pc;
	private Ad ad;

	private List<Category> categories = Lists.newArrayList();
	private List<FileInfo> fileInfo = Lists.newArrayList();
	private List<FileStoreInfo> infoStore = Lists.newArrayList();
	private List<ShelfFileLink> shelfFiles = Lists.newArrayList();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "market_url")
	public String getMarketURL() {
		return marketURL;
	}

	public void setMarketURL(String marketURL) {
		this.marketURL = marketURL;
	}

	public String getAvailMachine() {
		return availMachine;
	}

	public void setAvailMachine(String availMachine) {
		this.availMachine = availMachine;
	}

	public String getUnavailMachine() {
		return unavailMachine;
	}

	public void setUnavailMachine(String unavailMachine) {
		this.unavailMachine = unavailMachine;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@OneToMany(mappedBy = "unlockFile", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public Apk getApk() {
		return apk;
	}

	public void setApk(Apk apk) {
		this.apk = apk;
	}

	@OneToMany(mappedBy = "unlockFile", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public Ux getUx() {
		return ux;
	}

	public void setUx(Ux ux) {
		this.ux = ux;
	}

	@OneToMany(mappedBy = "unlockFile", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	@OneToMany(mappedBy = "unlockFile", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public PrevieWeb getPw() {
		return pw;
	}

	public void setPw(PrevieWeb pw) {
		this.pw = pw;
	}

	@OneToMany(mappedBy = "unlockFile", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public PreviewClient getPc() {
		return pc;
	}

	public void setPc(PreviewClient pc) {
		this.pc = pc;
	}

	@OneToMany(mappedBy = "unlockFile", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "f_category_file", joinColumns = { @JoinColumn(name = "file_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@OneToMany(mappedBy = "unlockFile", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public List<FileInfo> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileInfo> fileInfo) {
		this.fileInfo = fileInfo;
	}

	@OneToMany(mappedBy = "theme", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public List<FileStoreInfo> getInfoStore() {
		return infoStore;
	}

	public void setInfoStore(List<FileStoreInfo> infoStore) {
		this.infoStore = infoStore;
	}

	@OneToMany(mappedBy = "theme", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public List<ShelfFileLink> getShelfFiles() {
		return shelfFiles;
	}

	public void setShelfFiles(List<ShelfFileLink> shelfFiles) {
		this.shelfFiles = shelfFiles;
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}
}
