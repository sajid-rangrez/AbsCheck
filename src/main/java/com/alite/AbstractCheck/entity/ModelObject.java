package com.alite.AbstractCheck.entity;

public class ModelObject {
    private String pui;
    private String absText;
    private Boolean problem;
    private String apiAbstract;

    public String getPui() {
        return pui;
    }

    public void setPui(String pui) {
        this.pui = pui;
    }

    public String getAbsText() {
        return absText;
    }

    public void setAbsText(String absText) {
        this.absText = absText;
    }

    public Boolean getProblem() {
        return problem;
    }

    public void setProblem(Boolean problem) {
        this.problem = problem;
    }

    public String getApiAbstract() {
        return apiAbstract;
    }

    public void setApiAbstract(String apiAbstract) {
        this.apiAbstract = apiAbstract;
    }
}
