package com.hnj.code.model.builder;

import com.hnj.code.model.Response.TodoResponse;
import com.hnj.code.model.entity.Todo;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoResponseBuilder {

    public List<TodoResponse> buildTodoResponses(List<Todo> todoList) {
        return todoList.stream().map(this::buildTodoResponse).collect(Collectors.toList());
    }

    public TodoResponse buildTodoResponse(Todo todo){
        return TodoResponse.builder()
                .id(todo.getId())
                .itemName(todo.getItemName())
                .description(todo.getDescription())
                .isDone(todo.getIsDone())
                .modifiedAt(todo.getModifiedAt())
                .build();
    }
}