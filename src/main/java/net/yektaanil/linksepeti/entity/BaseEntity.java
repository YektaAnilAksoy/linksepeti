package net.yektaanil.linksepeti.entity;

import static net.yektaanil.linksepeti.common.Util.isNullOrEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import net.yektaanil.linksepeti.common.DateUtil;

@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8485815568313405602L;

    @Column(name = "Status", length = 1, nullable = false, columnDefinition = "integer default 1")
    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "CreateDate")
    private LocalDateTime createDate;

    @Column(name = "CreatedBy", nullable = false)
    private String createdBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "UpdateDate")
    private LocalDateTime updateDate;

    @Column(name = "UpdatedBy")
    private String updatedBy;


    public BaseEntity() {}

    @PrePersist
    public void prePersist() {
        this.createDate = DateUtil.getCurrentLocalDateTime();
        this.createdBy = isNullOrEmpty(this.createdBy) ? "SYSCREATE" : this.createdBy;
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = DateUtil.getCurrentLocalDateTime();
        this.updatedBy = isNullOrEmpty(this.updatedBy) ? "SYSUPDATE" : this.updatedBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "BaseEntity [status=" + status + ", createDate=" + createDate + ", createdBy="
                + createdBy + ", updateDate=" + updateDate + ", updatedBy=" + updatedBy + "]";
    }

}
