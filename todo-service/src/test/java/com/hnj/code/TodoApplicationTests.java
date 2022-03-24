package com.hnj.code;

import com.hnj.code.model.entity.Todo;
import com.hnj.code.model.request.TodoRequest;
import com.hnj.code.model.request.TodoUpdateRequest;
import com.hnj.code.service.TodoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoApplicationTests {

	public static Integer itemId;

	@Autowired
	private TodoService todoService;

	@Test
	public void addTodoTest() {
		TodoRequest todoRequest = new TodoRequest();
        todoRequest.setItemName("Meeting with HNJ");
        todoRequest.setDescription("About technical discussion");

        Todo todo = todoService.addTodoItem(todoRequest);
        itemId = todo.getId();

        Assert.assertEquals(true, todoRequest.getItemName().equalsIgnoreCase(todo.getItemName()));
	}

	@Test
	public void updateTodoTest() {
		addTodoTest();
		Todo todoBeforeUpdate = todoService.getTodoById(itemId);

		TodoUpdateRequest todoUpdateRequest = new TodoUpdateRequest();
		todoUpdateRequest.setId(itemId);
		todoUpdateRequest.setItemName("Meeting with HNJaman");
		todoUpdateRequest.setDescription(todoBeforeUpdate.getDescription() + " of Java Project");

		Todo todoAfterUpdate = todoService.updateTodoItem(todoUpdateRequest);

		Assert.assertEquals(false, todoAfterUpdate.getDescription().equalsIgnoreCase(
				todoBeforeUpdate.getDescription()));
	}

	@Test
	public void getTodoByIdTest(){
		addTodoTest();
		Todo todo = todoService.getTodoById(itemId);

		Assert.assertEquals(itemId, todo.getId());
	}

	@Test
	public void markTodoAsDoneTest() {
		addTodoTest();
		Todo todoBeforeDone = todoService.getTodoById(itemId);
		Todo todoAfterDone = todoService.markTodoAsDone(itemId);
		Assert.assertEquals(todoBeforeDone.getIsDone(), !todoAfterDone.getIsDone());
	}
}