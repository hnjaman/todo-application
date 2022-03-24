package com.hnj.code.model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponse {
    private Integer id;
    private String itemName;
    private String description;
    private Boolean isDone;
    private Date modifiedAt;
}