package com.alite.AbstractCheck.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "lit_citation")
public class Citation {

    @Id
    @Column(name="pui")
    private String pui;
    @Column(name = "problem")
    private Boolean problem;
    @Column(name="abs_citation")
    private String absText;

    @Column(name="api_abstract")
    private String apiAbsText;

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
}
