package com.web.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name = "menu")
public class Menu {
	public Integer menuId;
	public String menuName;
	public List<MenuItems> menuItems = new ArrayList<MenuItems>();
	
	
	@Id
	@GeneratedValue
	@Column(name = "menu_id")
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
	@Column(name = "menu_name")
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "menu", cascade = CascadeType.ALL)
	public List<MenuItems> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(List<MenuItems> menuItems) {
		this.menuItems = menuItems;
	}
	
}
