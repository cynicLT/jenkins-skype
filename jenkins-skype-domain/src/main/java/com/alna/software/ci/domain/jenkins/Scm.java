package com.alna.software.ci.domain.jenkins;

import com.alna.software.ci.domain.Domain;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Scm implements Domain {
    private static final long serialVersionUID = -883672479975576344L;

    private String url;
    private String branch;
    private String commit;

    public String getUrl() {
        return url;
    }

    public String getBranch() {
        return branch;
    }

    public String getCommit() {
        return commit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("url", url)
                .append("branch", branch)
                .append("commit", commit)
                .toString();
    }
}
