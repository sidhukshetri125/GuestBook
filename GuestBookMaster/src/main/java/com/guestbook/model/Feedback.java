
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
	private String firstName;
	private String feedbackImageName;
	private String feedbackText;
	@Lob
	private byte[] feedbackImage;
	private Date timestamp;
	private boolean status = false;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userid", referencedColumnName = "id", insertable = true, updatable = true)
	private User user;

	public Feedback() {

	}

	public Feedback(String firstName, String feedbackImageName, String feedbackText, byte[] feedbackImage,
			Date timestamp, boolean status, User user) {
		super();
		this.firstName = firstName;
		this.feedbackImageName = feedbackImageName;
		this.feedbackText = feedbackText;
		this.feedbackImage = feedbackImage;
		this.timestamp = timestamp;
		this.status = status;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFeedbackImageName() {
		return feedbackImageName;
	}

	public void setFeedbackImageName(String feedbackImageName) {
		this.feedbackImageName = feedbackImageName;
	}

	public String getFeedbackText() {
		return feedbackText;
	}

	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}

	public byte[] getFeedbackImage() {
		return feedbackImage;
	}

	public void setFeedbackImage(byte[] feedbackImage) {
		this.feedbackImage = feedbackImage;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", firstName=" + firstName + ", feedbackImageName=" + feedbackImageName
				+ ", feedbackText=" + feedbackText + ", feedbackImage=" + Arrays.toString(feedbackImage)
				+ ", timestamp=" + timestamp + ", status=" + status + ", user=" + user + "]";
	}

}
