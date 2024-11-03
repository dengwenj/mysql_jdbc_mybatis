package vip.dengwj.entity;

public class TJobsEntity {
    private String jobId;
    private String jobTitle;
    private String minSalary;
    private String maxSalary;

    public TJobsEntity() {}

    public TJobsEntity(String jobId, String jobTitle, String minSalary, String maxSalary) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public TJobsEntity(Builder builder) {
        this.jobId = builder.jobId;
        this.jobTitle = builder.jobTitle;
        this.minSalary = builder.minSalary;
        this.maxSalary = builder.maxSalary;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public String toString() {
        return "TJobs{" +
            "jobId='" + jobId + '\'' +
            ", jobTitle='" + jobTitle + '\'' +
            ", minSalary='" + minSalary + '\'' +
            ", maxSalary='" + maxSalary + '\'' +
            '}';
    }

    public static class Builder {
        private String jobId;
        private String jobTitle;
        private String minSalary;
        private String maxSalary;

        public Builder setJobId(String jobId) {
            this.jobId = jobId;
            return this;
        }

        public Builder setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public Builder setMinSalary(String minSalary) {
            this.minSalary = minSalary;
            return this;
        }

        public Builder setMaxSalary(String maxSalary) {
            this.maxSalary = maxSalary;
            return this;
        }

        public TJobsEntity build() {
            return new TJobsEntity(this);
        }
    }
}
