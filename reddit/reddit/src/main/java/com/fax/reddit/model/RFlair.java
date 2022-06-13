package com.fax.reddit.model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="r_flair")
@NamedQuery(name="RFlair.findAll", query="SELECT r FROM RFlair r")
public class RFlair implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	@ManyToOne
	@JoinColumn(name="community_id")
	private RCommunity RCommunity;

	@ManyToOne
	@JoinColumn(name="post_id")
	private RPost RPost;

	public RFlair() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RCommunity getRCommunity() {
		return this.RCommunity;
	}

	public void setRCommunity(RCommunity RCommunity) {
		this.RCommunity = RCommunity;
	}

	public RPost getRPost() {
		return this.RPost;
	}

	public void setRPost(RPost RPost) {
		this.RPost = RPost;
	}

}