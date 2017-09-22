package com.alna.software.ci.service.param;

import com.alna.software.ci.domain.jenkins.Job;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Skype message
 */
public class SkypeMessageParam extends AbstractParam {
    private static final long serialVersionUID = -3622272520550304817L;

    private String groupId;
    private Job job;

    public SkypeMessageParam withGroupId(final String groupId) {
        this.groupId = groupId;
        return this;
    }

    public SkypeMessageParam withJob(final Job job) {
        this.job = job;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public Job getJob() {
        return job;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("groupId", groupId)
                .append("job", job)
                .toString();
    }
}
