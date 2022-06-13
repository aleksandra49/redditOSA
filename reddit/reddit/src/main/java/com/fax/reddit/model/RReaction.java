package com.fax.reddit.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="r_reaction")
@NamedQuery(name="RReaction.findAll", query="SELECT r FROM RReaction r")
public class RReaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date timestamp;

	private String type;

	@ManyToOne
	@JoinColumn(name="comment_id")
	private RComment RComment;

	@ManyToOne
	@JoinColumn(name="post_id")
	private RPost RPost;

	@ManyToOne
	@JoinColumn(name="user_id")
	private RUser RUser;

	public RReaction() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RComment getRComment() {
		return this.RComment;
	}

	public void setRComment(RComment RComment) {
		this.RComment = RComment;
	}

	public RPost getRPost() {
		return this.RPost;
	}

	public void setRPost(RPost RPost) {
		this.RPost = RPost;
	}

	public com.fax.reddit.model.RUser getRUser() {
		return RUser;
	}

	public void setRUser(com.fax.reddit.model.RUser RUser) {
		this.RUser = RUser;
	}
}