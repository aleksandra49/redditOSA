package com.fax.reddit.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="r_post")
@NamedQuery(name="RPost.findAll", query="SELECT r FROM RPost r")
public class RPost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date creationDate;

	private String imagePath;
	
	private String title;

	private String text;

	@OneToMany(mappedBy="RPost")
	private List<RComment> RComments;

	@OneToMany(mappedBy="RPost")
	private List<RFlair> RFlairs;

	@ManyToOne
	@JoinColumn(name="community_id")
	private RCommunity RCommunity;

	@ManyToOne
	@JoinColumn(name="user_id")
	private RUser RUser;

	@OneToMany(mappedBy="RPost")
	private List<RReaction> RReactions;

	@OneToMany(mappedBy="RPost")
	private List<RReport> RReports;

	public RPost() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<RComment> getRComments() {
		return this.RComments;
	}

	public void setRComments(List<RComment> RComments) {
		this.RComments = RComments;
	}

	public RComment addRComment(RComment RComment) {
		getRComments().add(RComment);
		RComment.setRPost(this);

		return RComment;
	}

	public RComment removeRComment(RComment RComment) {
		getRComments().remove(RComment);
		RComment.setRPost(null);

		return RComment;
	}

	public List<RFlair> getRFlairs() {
		return this.RFlairs;
	}

	public void setRFlairs(List<RFlair> RFlairs) {
		this.RFlairs = RFlairs;
	}

	public RFlair addRFlair(RFlair RFlair) {
		getRFlairs().add(RFlair);
		RFlair.setRPost(this);

		return RFlair;
	}

	public RFlair removeRFlair(RFlair RFlair) {
		getRFlairs().remove(RFlair);
		RFlair.setRPost(null);

		return RFlair;
	}

	public RCommunity getRCommunity() {
		return this.RCommunity;
	}

	public void setRCommunity(RCommunity RCommunity) {
		this.RCommunity = RCommunity;
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
		RReaction.setRPost(this);

		return RReaction;
	}

	public RReaction removeRReaction(RReaction RReaction) {
		getRReactions().remove(RReaction);
		RReaction.setRPost(null);

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
		RReport.setRPost(this);

		return RReport;
	}

	public RReport removeRReport(RReport RReport) {
		getRReports().remove(RReport);
		RReport.setRPost(null);

		return RReport;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}