package com.tp.entity.unlock;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.DiscriminatorOptions;

import com.tp.entity.IdEntity;

@Entity
@Table(name = "f_fitem")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorOptions(force = true)
public abstract class FileItem extends IdEntity {

	protected String name;
	protected long size;
	protected String path;

	protected UnlockFile unlockFile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_id")
	public UnlockFile getUnlockFile() {
		return unlockFile;
	}

	public void setUnlockFile(UnlockFile unlockFile) {
		this.unlockFile = unlockFile;
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}

}
