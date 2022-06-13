package com.fax.reddit.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="r_report")
@NamedQuery(name="RReport.findAll", query="SELECT r FROM RReport r")
public class RReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private byte accepted;

	private String reason;

	@Temporal(TemporalType.DATE)
	private Date timestamp;

	@ManyToOne
	@JoinColumn(name="comment_id")
	private RComment RComment;

	@ManyToOne
	@JoinColumn(name="post_id")
	private RPost RPost;

	public RReport() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getAccepted() {
		return this.accepted;
	}

	public void setAccepted(byte accepted) {
		this.accepted = accepted;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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

}