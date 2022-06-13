package com.fax.reddit.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="r_community")
@NamedQuery(name="RCommunity.findAll", query="SELECT r FROM RCommunity r")
public class RCommunity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date creationDate;

	private String description;

	private byte isSuspended;

	private String suspendedReason;

	@OneToMany(mappedBy="RCommunity")
	private List<RBanned> RBanneds;

	@ManyToOne
	@JoinColumn(name="user_id")
	private RUser RUser;

	@OneToMany(mappedBy="RCommunity")
	private List<RFlair> RFlairs;

	@OneToMany(mappedBy="RCommunity")
	private List<RPost> RPosts;

	public RCommunity() {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getIsSuspended() {
		return this.isSuspended;
	}

	public void setIsSuspended(byte isSuspended) {
		this.isSuspended = isSuspended;
	}

	public String getSuspendedReason() {
		return this.suspendedReason;
	}

	public void setSuspendedReason(String suspendedReason) {
		this.suspendedReason = suspendedReason;
	}

	public List<RBanned> getRBanneds() {
		return this.RBanneds;
	}

	public void setRBanneds(List<RBanned> RBanneds) {
		this.RBanneds = RBanneds;
	}

	public RBanned addRBanned(RBanned RBanned) {
		getRBanneds().add(RBanned);
		RBanned.setRCommunity(this);

		return RBanned;
	}

	public RBanned removeRBanned(RBanned RBanned) {
		getRBanneds().remove(RBanned);
		RBanned.setRCommunity(null);

		return RBanned;
	}

	public RUser getRUser() {
		return this.RUser;
	}

	public void setRUser(RUser RUser) {
		this.RUser = RUser;
	}

	public List<RFlair> getRFlairs() {
		return this.RFlairs;
	}

	public void setRFlairs(List<RFlair> RFlairs) {
		this.RFlairs = RFlairs;
	}

	public RFlair addRFlair(RFlair RFlair) {
		getRFlairs().add(RFlair);
		RFlair.setRCommunity(this);

		return RFlair;
	}

	public RFlair removeRFlair(RFlair RFlair) {
		getRFlairs().remove(RFlair);
		RFlair.setRCommunity(null);

		return RFlair;
	}

	public List<RPost> getRPosts() {
		return this.RPosts;
	}

	public void setRPosts(List<RPost> RPosts) {
		this.RPosts = RPosts;
	}

	public RPost addRPost(RPost RPost) {
		getRPosts().add(RPost);
		RPost.setRCommunity(this);

		return RPost;
	}

	public RPost removeRPost(RPost RPost) {
		getRPosts().remove(RPost);
		RPost.setRCommunity(null);

		return RPost;
	}

}