package com.tp.entity.unlock;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

public class FileType {

	@Entity
	@DiscriminatorValue("ad")
	public static class Ad extends FileItem {

	}

	@Entity
	@DiscriminatorValue("apk")
	public static class Apk extends FileItem {

	}

	@Entity
	@DiscriminatorValue("icon")
	public static class Icon extends FileItem {

	}

	@Entity
	@DiscriminatorValue("p_w")
	public static class PrevieWeb extends FileItem {

	}

	@Entity
	@DiscriminatorValue("p_c")
	public static class PreviewClient extends FileItem {

	}

	@Entity
	@DiscriminatorValue("ux")
	public static class Ux extends FileItem {

	}
}
