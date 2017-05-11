package entities;

import java.sql.Timestamp;

/**
 * Created by Sasha on 28.04.2017.
 */
public class EmployeeComment implements Entity{
    private long commentId;
    private String content;
    private long commentatorId;
    private long taskId;
    private Timestamp created;
    private long createdBy;

    public EmployeeComment(long commentId, long commentatorId, long taskId){
        this.commentId = commentId;
        this.commentatorId = commentatorId;
        this.taskId = taskId;
    }

    public long getTaskId() {
        return taskId;
    }

    public long getCommentatorId() {
        return commentatorId;
    }

    public String getContent() {
        return content;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCommentatorId(long commentatorId) {
        this.commentatorId = commentatorId;
    }

    @Override
    public Long getEntityId() {
        return commentId;
    }
}
