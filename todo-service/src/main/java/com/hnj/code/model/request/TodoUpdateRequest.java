package com.hnj.code.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoUpdateRequest {
    private Integer id;
    private String itemName;
    private String description;
}