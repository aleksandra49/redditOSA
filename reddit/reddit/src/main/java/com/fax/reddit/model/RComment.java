package com.fax.reddit.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="r_comment")
@NamedQuery(name="RComment.findAll", query="SELECT r FROM RComment r")
public class RComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private byte isDeleted;

	private String text;

	@Temporal(TemporalType.DATE)
	private Date timestamp;

	@ManyToOne
	@JoinColumn(name="post_id")
	private RPost RPost;

	@ManyToOne
	@JoinColumn(name="user_id")
	private RUser RUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id", nullable = true)
	private RComment mainComment;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mainComment") // I am not sure here should be id or mainOrder
	private Set<RComment> subComments = new HashSet<>();

	@OneToMany(mappedBy="RComment")
	private List<RReaction> RReactions;

	@OneToMany(mappedBy="RComment")
	private List<RReport> RReports;

	public RComment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public RPost getRPost() {
		return this.RPost;
	}

	public void setRPost(RPost RPost) {
		this.RPost = RPost;
	}

	public RUser getRUser() {
		return this.RUser;
	}

	public void setRUser(RUser RUser) {
		this.RUser = RUser;
	}

	public List<RReaction> getRReactions() {
		return this.RReactions;
	}

	public void setRReactions(List<RReaction> RReactions) {
		this.RReactions = RReactions;
	}

	public RReaction addRReaction(RReaction RReaction) {
		getRReactions().add(RReaction);
		RReaction.setRComment(this);

		return RReaction;
	}

	public RReaction removeRReaction(RReaction RReaction) {
		getRReactions().remove(RReaction);
		RReaction.setRComment(null);

		return RReaction;
	}

	public List<RReport> getRReports() {
		return this.RReports;
	}

	public void setRReports(List<RReport> RReports) {
		this.RReports = RReports;
	}

	public RReport addRReport(RReport RReport) {
		getRReports().add(RReport);
		RReport.setRComment(this);

		return RReport;
	}

	public RReport removeRReport(RReport RReport) {
		getRReports().remove(RReport);
		RReport.setRComment(null);

		return RReport;
	}

	public RComment getMainComment() {
		return mainComment;
	}

	public void setMainComment(RComment mainComment) {
		this.mainComment = mainComment;
	}

	public Set<RComment> getSubComments() {
		return subComments;
	}

	public void setSubComments(Set<RComment> subComments) {
		this.subComments = subComments;
	}
}