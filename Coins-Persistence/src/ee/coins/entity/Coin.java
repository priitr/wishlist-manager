package ee.coins.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mynt")
public class Coin implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum QualityType {
		F, XF, UNC, PRF, BU
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tableGenerator_mynt")
	private Long id;
	@Column(name="pilt_eest")
	private byte[] aversPicture; 
	@Column(name="pilt_tagant")
	private byte[] reversPicture; 
	@Column(name="kirjeldus")
	private String description; 
	@Column(name="kvaliteet",nullable=false)
	@Enumerated(EnumType.STRING)
	private QualityType qualityType;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="erim_id", nullable=false)
	private Issue issue;
		
	public Coin() {
		super();
	} 
	   
	public byte[] getAversPicture() {
 		return this.aversPicture;
	}

	public void setAversPicture(byte[] aversPicture) {
		this.aversPicture = aversPicture;
	}
	   
	public byte[] getReversPicture() {
 		return this.reversPicture;
	}

	public void setReversPicture(byte[] reversPicture) {
		this.reversPicture = reversPicture;
	}
	   
	public String getDescription() {
 		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	   
	public QualityType getQualityType() {
 		return this.qualityType;
	}

	public void setQualityType(QualityType qualityType) {
		this.qualityType = qualityType;
	}
	public Issue getIssue() {
		return issue;
	}
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
}
