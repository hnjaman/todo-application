package com.hnj.code.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TodoRequest {
    private String itemName;
    private String description;
}