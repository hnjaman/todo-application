package com.hnj.code.controller;

import com.hnj.code.model.Response.BooleanResponse;
import com.hnj.code.model.Response.TodoMasterResponse;
import com.hnj.code.model.entity.Todo;
import com.hnj.code.util.StringConst;
import com.hnj.code.model.Response.ResponseEnvelope;
import com.hnj.code.model.Response.TodoResponse;
import com.hnj.code.model.request.TodoRequest;
import com.hnj.code.model.request.TodoUpdateRequest;
import com.hnj.code.model.builder.TodoResponseBuilder;
import com.hnj.code.service.TodoService;
import com.hnj.code.util.ValidationUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoResponseBuilder todoResponseBuilder;

    @ApiOperation(value = "Get all todo items")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEnvelope<TodoMasterResponse> getAllTodos() {
        log.info("get all todo item api invoked");
        return new ResponseEnvelope<>(todoService.getTodoItems(),true, StringConst.SUCCESS,
                HttpStatus.OK.value());
    }

    @ApiOperation(value = "Get a todo item")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEnvelope<TodoResponse> getTodoById(@PathVariable Integer id) {
        log.info("get a todo item api invoked, id = "+ id);
        ValidationUtil.todoIdValidation(id);
        Todo todo = todoService.getTodoById(id);
        return new ResponseEnvelope<>(todoResponseBuilder.buildTodoResponse(todo), true, StringConst.SUCCESS,
                HttpStatus.OK.value());
    }

    @ApiOperation(value = "Delete a todo item")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEnvelope<BooleanResponse> deleteTodoById(@PathVariable Integer id) {
        ValidationUtil.todoIdValidation(id);
        log.info("deleting a todo item api invoked, id = " + id);
        todoService.deleteTodoItem(id);
        return new ResponseEnvelope<>(new BooleanResponse(true), true, StringConst.SUCCESSFULLY_DELETED,
                HttpStatus.NO_CONTENT.value());
    }

    @ApiOperation(value = "Mark a todo item as Done")
    @PutMapping(value = "/{id}/done")
    public ResponseEnvelope<BooleanResponse> markTodoAsDone(@PathVariable Integer id){
        ValidationUtil.todoIdValidation(id);
        log.info("marking a todo item as done api invoked, id = " + id);
        Todo todo = todoService.markTodoAsDone(id);
        return new ResponseEnvelope<>(new BooleanResponse(true), true,
                todo.getItemName()+ " " + StringConst.DONE, HttpStatus.OK.value());
    }

    @ApiOperation(value = "Add todo item")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEnvelope<TodoResponse> addTodoItem(@RequestBody TodoRequest todoRequest) {
        log.info("adding todo item api invoked");
        Todo todo = todoService.addTodoItem(todoRequest);
        return new ResponseEnvelope<>(todoResponseBuilder.buildTodoResponse(todo),true, StringConst.SUCCESS,
                HttpStatus.CREATED.value());
    }

    @ApiOperation(value = "Update a todo item")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEnvelope<TodoResponse> updateTodo(@RequestBody TodoUpdateRequest todoUpdateRequest) {
        ValidationUtil.todoIdValidation(todoUpdateRequest.getId());
        log.info("updating todo item api invoked, id = " + todoUpdateRequest.getId());
        Todo todo = todoService.updateTodoItem(todoUpdateRequest);
        return new ResponseEnvelope<>(todoResponseBuilder.buildTodoResponse(todo), true, StringConst.SUCCESS,
                HttpStatus.OK.value());
    }
}