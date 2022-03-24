package com.hnj.code.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_done", columnDefinition="tinyint(1) default 0")
    private Boolean isDone;

    @Column(name = "created_at", columnDefinition="DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "modified_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date modifiedAt;
}
