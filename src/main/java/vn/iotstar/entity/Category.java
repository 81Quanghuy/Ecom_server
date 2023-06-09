package vn.iotstar.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Category")
public class Category {	
	@Id	
	private String id;
	private String name;
	private String image;
	private Boolean isdeleted;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	public Category(String id, String name, String image, Boolean isdeleted) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.isdeleted = isdeleted;
	}
	public Category() {
		super();
	}

}
