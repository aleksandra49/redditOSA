package com.fax.reddit.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="r_banned")
@NamedQuery(name="RBanned.findAll", query="SELECT r FROM RBanned r")
public class RBanned implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@ManyToOne
	@JoinColumn(name="community_id")
	private RCommunity RCommunity;

	@ManyToOne
	@JoinColumn(name="user_id")
	private RUser RUser;

	public RBanned() {
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

}