package com.hnj.code.service;

import com.hnj.code.model.Response.TodoMasterResponse;
import com.hnj.code.model.entity.Todo;
import com.hnj.code.model.request.TodoRequest;
import com.hnj.code.model.request.TodoUpdateRequest;
import java.util.List;

public interface TodoService {
    Todo addTodoItem(TodoRequest todoRequest);
    Todo updateTodoItem(TodoUpdateRequest todoUpdateRequest);
    TodoMasterResponse getTodoItems();
    Todo getTodoById(Integer id);
    void deleteTodoItem(Integer id);
    Todo markTodoAsDone(Integer id);
}