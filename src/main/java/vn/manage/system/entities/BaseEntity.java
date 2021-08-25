package vn.manage.system.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseEntity {

  @CreatedDate
  @Column(name = "created_on", updatable = false)
  private LocalDateTime createdOn;

  @LastModifiedDate
  @Column(name = "updated_on")
  private LocalDateTime updatedOn;

  @Column(name = "is_deleted")
  private boolean deleted;

}
