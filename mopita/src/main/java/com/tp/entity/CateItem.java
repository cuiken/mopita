package com.tp.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.DiscriminatorOptions;

@Entity
@Table(name = "f_category")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorOptions(force = true)
public abstract class CateItem extends IdEntity {

	protected String name;
	protected String value;
	protected String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}
}
