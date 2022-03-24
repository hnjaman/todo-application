package com.hnj.code.service.impl;

import com.hnj.code.exception.AppException;
import com.hnj.code.model.Response.TodoMasterResponse;
import com.hnj.code.model.builder.TodoResponseBuilder;
import com.hnj.code.model.entity.Todo;
import com.hnj.code.model.request.TodoRequest;
import com.hnj.code.model.request.TodoUpdateRequest;
import com.hnj.code.repository.TodoRepository;
import com.hnj.code.service.TodoService;
import com.hnj.code.util.StringConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoResponseBuilder todoResponseBuilder;

    public Todo addTodoItem(TodoRequest todoRequest) {
        log.info("adding a todo item");
        return todoRepository.save(Todo.builder()
                .itemName(todoRequest.getItemName())
                .description(todoRequest.getDescription())
                .isDone(false)
                .createdAt(new Date())
                .modifiedAt(new Date())
                .build());
    }

    @Override
    public Todo updateTodoItem(TodoUpdateRequest todoUpdateRequest) {
        Todo existingTodo = getTodoById(todoUpdateRequest.getId());
        existingTodo.setItemName(todoUpdateRequest.getItemName());
        existingTodo.setDescription(todoUpdateRequest.getDescription());
        existingTodo.setModifiedAt(new Date());

        log.info("updating a todo item, id = " + todoUpdateRequest.getId());
        return todoRepository.save(existingTodo);
    }

    @Override
    public TodoMasterResponse getTodoItems() {
        log.info("fetching all todos");
        List<Todo> todoList = todoRepository.findAllByIsDoneFalseOrderByModifiedAtDesc();

        log.info("fetching all done todos");
        List<Todo> doneTodoList = todoRepository.findAllByIsDoneTrueOrderByModifiedAtDesc();

        return TodoMasterResponse.builder()
                .todos(todoResponseBuilder.buildTodoResponses(todoList))
                .doneTodos(todoResponseBuilder.buildTodoResponses(doneTodoList))
                .build();
    }

    @Override
    public Todo getTodoById(Integer id) {
        log.info("fetching todo item, id = " + id);
        Optional<Todo> todo = todoRepository.findById(id);
        if (!todo.isPresent()){
            throw new AppException(StringConst.TODO_ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return todo.get();
    }

    @Override
    public void deleteTodoItem(Integer id) {
        Todo todo = getTodoById(id);
        log.info("deleting todo item, id = " + id);
        todoRepository.deleteById(todo.getId());
    }

    @Override
    public Todo markTodoAsDone(Integer id) {
        Todo existingTodo = getTodoById(id);
        if (existingTodo.getIsDone()){
            throw new AppException(StringConst.TODO_ITEM_ALREADY_DONE, HttpStatus.BAD_REQUEST);
        }
        existingTodo.setIsDone(true);
        existingTodo.setModifiedAt(new Date());
        log.info("marking todo item as done, id = " + existingTodo.getId());
        return todoRepository.save(existingTodo);
    }
}