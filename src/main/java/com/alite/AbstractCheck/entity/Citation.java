package com.alite.AbstractCheck.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;
import java.sql.Timestamp;

@Entity(name = "lit_citation")
public class Citation {

    @Id
    @Column(name = "record_id", updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID recordId;

    @Column(name="pui")
    private String pui;
    @Column(name = "problem")
    private Boolean problem;
    @Column(name="abs_citation")
    private String absText;

    @Column(name="api_abstract")
    private String apiAbsText;

    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @Column(name = "user_created")
    private String userCreated;

    public String getAbsText() {
        return absText;
    }

    public void setAbsText(String absText) {
        this.absText = absText;
    }

    public String getPui() {
        return pui;
    }

    public void setPui(String pui) {
        this.pui = pui;
    }

    public Boolean getProblem() {
        return problem;
    }

    public void setProblem(Boolean problem) {
        this.problem = problem;
    }

    public String getApiAbsText() {
        return apiAbsText;
    }

    public void setApiAbsText(String apiAbsText) {
        this.apiAbsText = apiAbsText;
    }

    public UUID getRecordId() {
        return recordId;
    }

    public void setRecordId(UUID recordId) {
        this.recordId = recordId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }
}
