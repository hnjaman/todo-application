package com.hnj.code.model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoMasterResponse {
    List<TodoResponse> todos;
    List<TodoResponse> doneTodos;
}
