package de.homelabs.moonrat.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="public", name="template")
public class Template {

	@Id
	private long id;
	private int type;
	@Column(name="referenz_id")
	private long referenzId;
	private String content;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getReferenzId() {
		return referenzId;
	}
	public void setReferenzId(long referenzId) {
		this.referenzId = referenzId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
