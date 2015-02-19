package org.bihe.semantic.model;
/**
 * @author Mozhde
 *
 */
public class Category {
	private long id;
	private String shortname;
	private String categoryName;

	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
}
