package com.fax.reddit.dto;

import java.util.Date;

public class UpdateCommunityReq {
	
	private Date creationDate;
	
	private String description;
    
	//private byte isSuspended;

	//private String suspendedReason;

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public byte getIsSuspended() {
		return isSuspended;
	}

	public void setIsSuspended(byte isSuspended) {
		this.isSuspended = isSuspended;
	}*/

	/*public String getSuspendedReason() {
		return suspendedReason;
	}

	public void setSuspendedReason(String suspendedReason) {
		this.suspendedReason = suspendedReason;
	}*/

	//private String creator;
	
	


}
