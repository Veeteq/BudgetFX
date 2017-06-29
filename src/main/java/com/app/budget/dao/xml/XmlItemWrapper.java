package com.app.budget.dao.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.app.budget.model.Item;

@XmlRootElement(name="items")
public class XmlItemWrapper {

	private List<Item> items;
	
	@XmlElement(name="item")
	public List<Item> getItems(){
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
