package entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Sasha on 28.04.2017.
 */
public class Project implements Entity{
    private long projectId;
    private String projectName;
    private Timestamp created;
    private long createdBy;
    private Timestamp updated;
    private long updatedBy;
    private Date startDate;
    private Date finishDate;
    private long headId;

    public Project(){}

    public Project(long projectId, String projectName, long headId){
        this.projectId = projectId;
        this.projectName = projectName;
        this.headId = headId;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public long getHeadId() {
        return headId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setHeadId(long headId) {
        this.headId = headId;
    }

    @Override
    public Long getEntityId() {
        return projectId;
    }
}
