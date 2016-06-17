package cc.picc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author lijinting01
 * 
 */
@Entity
public class Article {

	@Id
	private String id;

	@Size(max = 255, min = 2)
	@NotNull
	@Length(max = 255, min = 2)
	private String author;

	@Length(max = 255)
	private String summary;

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Article [id=%s, author=%s, summary=%s]", id,
				author, summary);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
