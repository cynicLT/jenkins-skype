package com.alna.software.ci.domain.jenkins;

import com.alna.software.ci.domain.Domain;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;
import java.util.Map;

public class Build implements Domain {
    private static final long serialVersionUID = 5124165167115607514L;


    public enum Phase {
        STARTED, COMPLETED, FINALIZED;
    }

    private String fullUrl;
    private int number;
    private long queueId;
    private Phase phase;
    private String status;
    private String url;
    private String displayName;
    private Scm scm;
    private Map<String, String> parameters;
    private StringBuilder log;
    private final Map<String, Map<String, String>> artifacts = new HashMap<>();

    public String getFullUrl() {
        return fullUrl;
    }

    public int getNumber() {
        return number;
    }

    public long getQueueId() {
        return queueId;
    }

    public Phase getPhase() {
        return phase;
    }

    public String getStatus() {
        return status;
    }

    public String getUrl() {
        return url;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Scm getScm() {
        return scm;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public StringBuilder getLog() {
        return log;
    }

    public Map<String, Map<String, String>> getArtifacts() {
        return artifacts;
    }

    public Build withLog(final StringBuilder log) {
        this.log = log;
        return this;
    }

    public Build withParameters(final Map<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    public Build withScm(final Scm scm) {
        this.scm = scm;
        return this;
    }

    public Build withDisplayName(final String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Build withUrl(final String url) {
        this.url = url;
        return this;
    }

    public Build withStatus(final String status) {
        this.status = status;
        return this;
    }

    public Build withPhase(final Phase phase) {
        this.phase = phase;
        return this;
    }

    public Build withQueueId(final long queueId) {
        this.queueId = queueId;
        return this;
    }

    public Build withNumber(final int number) {
        this.number = number;
        return this;
    }

    public Build withFullUrl(final String fullUrl) {
        this.fullUrl = fullUrl;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("fullUrl", fullUrl)
                .append("number", number)
                .append("queueId", queueId)
                .append("phase", phase)
                .append("status", status)
                .append("url", url)
                .append("displayName", displayName)
                .append("scm", scm)
                .append("parameters", parameters)
                .append("log", log)
                .append("artifacts", artifacts)
                .toString();
    }
}
