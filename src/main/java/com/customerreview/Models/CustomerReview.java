package com.customerreview.Models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CUSTOMERREVIEW")
public class CustomerReview {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "HEADLINE", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String headline;

    @Column(name = "comment", length = 255)
    @NotNull
    @Size(min = 4, max = 255)
    private String comment;

    @Column(name = "RATING")
    @NotNull
    private double rating;

    @Column(name = "BLOCKED")
    @NotNull
    private boolean blocked;

    @Column(name = "ALIAS", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String alias;
    
    @Column(name = "APPROVALSTATUS", length = 15)
    @NotNull
    @Size(min = 2, max = 15)
    private String approvalStatus;

    @Column(name = "LANGUAGE")
    @NotNull
    @Size(min = 4, max = 15)
    private String language;

    @Column(name = "USERID")
    @NotNull
    private long userId;
    
    @Column(name = "PRODUCTID")
    @NotNull
    private long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
  
}

