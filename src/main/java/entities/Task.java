package entities;

import java.sql.Timestamp;

/**
 * Created by Sasha on 28.04.2017.
 */
public class Task implements Entity{
    private long taskId;
    private String taskName;
    private TaskType type;
    private TaskState state;
    private String description;
    private TaskPriority priority;
    private Timestamp created;
    private long createdBy;
    private Timestamp updated;
    private long updatedBy;
    private long assigneeId;
    private long reporterId;
    private long projectId;

    public Task(){

    }

    public Task(long taskId, String taskName, String type, String state, String description, String priority, long projectId){
        this.taskId = taskId;
        this.taskName = taskName;
        this.type = TaskType.valueOf(type.toUpperCase());
        this.state = TaskState.valueOf(state.toUpperCase());
        this.description = description;
        this.priority = TaskPriority.valueOf(priority.toUpperCase());
        this.projectId = projectId;
    }

//    public Task(long taskId, String taskName, TaskType type, TaskState state, String description, TaskPriority priority,
//                long createdBy, Timestamp updated, Timestamp updatedBy,
//                long assigneeId, long reporterId, long projectId){
//        this.taskId = taskId;
//        this.taskName = taskName;
//        this.type = type;
//        this.state = state;
//        this.description = description;
//        this.priority = priority;
//    }

    public String getDescription() {
        return description;
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskState getState() {
        return state;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public TaskType getType() {
        return type;
    }

    public Timestamp getCreated() {
        return created;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public long getAssigneeId() {
        return assigneeId;
    }

    public long getProjectId() {
        return projectId;
    }

    public long getReporterId() {
        return reporterId;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssigneeId(long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public void setReporterId(long reporterId) {
        this.reporterId = reporterId;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public Long getEntityId() {
        return taskId;
    }
}
