package com.fax.reddit.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="r_user")
@NamedQuery(name="RUser.findAll", query="SELECT r FROM RUser r")
public class RUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String avatar;

	private String description;

	private String displayName;

	private String password;

	@Temporal(TemporalType.DATE)
	private Date registrationDate;

	private String role;

	private String username;

	@OneToMany(mappedBy="RUser")
	private List<RBanned> RBanneds;

	@OneToMany(mappedBy="RUser")
	private List<RComment> RComments;

	@OneToMany(mappedBy="RUser")
	private List<RCommunity> RCommunities;

	@OneToMany(mappedBy="RUser")
	private List<RReaction> RReactions;

	@OneToMany(mappedBy="RUser")
	private List<RPost> RPosts;

	public RUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<RBanned> getRBanneds() {
		return this.RBanneds;
	}

	public void setRBanneds(List<RBanned> RBanneds) {
		this.RBanneds = RBanneds;
	}

	public RBanned addRBanned(RBanned RBanned) {
		getRBanneds().add(RBanned);
		RBanned.setRUser(this);

		return RBanned;
	}

	public RBanned removeRBanned(RBanned RBanned) {
		getRBanneds().remove(RBanned);
		RBanned.setRUser(null);

		return RBanned;
	}

	public List<RComment> getRComments() {
		return this.RComments;
	}

	public void setRComments(List<RComment> RComments) {
		this.RComments = RComments;
	}

	public RComment addRComment(RComment RComment) {
		getRComments().add(RComment);
		RComment.setRUser(this);

		return RComment;
	}

	public RComment removeRComment(RComment RComment) {
		getRComments().remove(RComment);
		RComment.setRUser(null);

		return RComment;
	}

	public List<RCommunity> getRCommunities() {
		return this.RCommunities;
	}

	public void setRCommunities(List<RCommunity> RCommunities) {
		this.RCommunities = RCommunities;
	}

	public RCommunity addRCommunity(RCommunity RCommunity) {
		getRCommunities().add(RCommunity);
		RCommunity.setRUser(this);

		return RCommunity;
	}

	public RCommunity removeRCommunity(RCommunity RCommunity) {
		getRCommunities().remove(RCommunity);
		RCommunity.setRUser(null);

		return RCommunity;
	}

	public List<RPost> getRPosts() {
		return this.RPosts;
	}

	public void setRPosts(List<RPost> RPosts) {
		this.RPosts = RPosts;
	}

	public RPost addRPost(RPost RPost) {
		getRPosts().add(RPost);
		RPost.setRUser(this);

		return RPost;
	}

	public RPost removeRPost(RPost RPost) {
		getRPosts().remove(RPost);
		RPost.setRUser(null);

		return RPost;
	}

}