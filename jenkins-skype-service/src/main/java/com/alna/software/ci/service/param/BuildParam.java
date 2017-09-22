package com.alna.software.ci.service.param;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Build parameters
 */
public class BuildParam extends AbstractParam {
    private String name;
    private String status;
    private List<String> artifacts = new ArrayList<>();

    public BuildParam withName(final String name) {
        this.name = name;
        return this;
    }

    public BuildParam withStatus(final String status) {
        this.status = status;
        return this;
    }

    public BuildParam withArtifacts(final List<String> artifacts) {
        this.artifacts = artifacts;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getArtifacts() {
        return artifacts;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("name", name)
                .append("status", status)
                .append("artifacts", artifacts)
                .toString();
    }
}
