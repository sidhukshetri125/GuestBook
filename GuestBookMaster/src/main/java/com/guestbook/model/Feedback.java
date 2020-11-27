
package com.guestbook.model;

import java.util.Arrays;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <h1>Entity class Feedback</h1>
 * 
 * 
 * @author Sidhu Kshetri
 * 
 */

@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String firstname;
	private String feedbackimagename;
	private String feedbacktext;
	@Lob
	private byte[] feedbackimage;
	private Date timestamp;
	private boolean status = false;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userid", referencedColumnName = "id", insertable = true, updatable = true)
	private User user;

	public Feedback() {

	}

	public Feedback(String firstname, String feedbacktext, String feedbackimagename, byte[] feedbackimage,
			Date timestamp, boolean status, User user) {
		super();
		this.firstname = firstname;
		this.feedbacktext = feedbacktext;
		this.feedbackimagename = feedbackimagename;
		this.feedbackimage = feedbackimage;
		this.timestamp = timestamp;
		this.status = status;

		this.user = user;

	}

	/*
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; }
	 */
	public String getFeedbackimagename() {
		return feedbackimagename;
	}

	public void setFeedbackimagename(String feedbackimagename) {
		this.feedbackimagename = feedbackimagename;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFeedbacktext() {
		return feedbacktext;
	}

	public void setFeedbacktext(String feedbacktext) {
		this.feedbacktext = feedbacktext;
	}

	public byte[] getFeedbackimage() {
		return feedbackimage;
	}

	public void setFeedbackimage(byte[] feedbackimage) {
		this.feedbackimage = feedbackimage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", firstname=" + firstname + ", feedbackimagename=" + feedbackimagename
				+ ", feedbacktext=" + feedbacktext + ", feedbackimage=" + Arrays.toString(feedbackimage)
				+ ", timestamp=" + timestamp + ", status=" + status + ", user=" + user + ", feedbackApproved=" + "]";
	}

}
