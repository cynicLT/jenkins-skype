package com.alna.software.ci.domain.jenkins;

import com.alna.software.ci.domain.Domain;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Job implements Domain {
    private static final long serialVersionUID = 1776442718659484474L;

    public Job withName(final String name) {
        this.name = name;
        return this;
    }

    public Job withUrl(final String url) {
        this.url = url;
        return this;
    }

    public Job withBuild(final Build build) {
        this.build = build;
        return this;
    }

    private String name;
    private String url;
    private Build build;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Build getBuild() {
        return build;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", name)
                .append("url", url)
                .append("build", build)
                .toString();
    }
}
