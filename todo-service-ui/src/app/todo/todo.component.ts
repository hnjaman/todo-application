import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { SubSink } from 'subsink';
import { TodoService } from '../services/todo.service';
@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  subs = new SubSink();
  todos;
  completedTodos;
  addedTodo;
  updatedTodo;
  markedAsDoneTodo;
  todoForm: FormGroup;
  totalTodos;
  totalCompletedTodos;
  mode = "add";
  selectedItem;

  constructor(
    private todoService: TodoService,
    public modalService: NgbModal,
    private fb: FormBuilder,
    public toast: ToastrService) { 
      this.todoForm = fb.group({
        itemName: ['', [Validators.required]],
        description: ['', [Validators.required]]
      });
    }

  ngOnInit(): void {
    this.getAllTodoItems();
  }

  getAllTodoItems(){
    this.subs.sink = this.todoService.getAllTodos().subscribe((responseData: any) => {
      this.todos = responseData.data.todos;
      this.completedTodos = responseData.data.doneTodos;
      this.totalTodos = this.todos.length;
      this.totalCompletedTodos = this.completedTodos.length;
    },(err:any)=>{
      this.toast.error(err.error.message);
    });
  }

  addTodoItem(item){
    this.subs.sink = this.todoService.addTodo(item).subscribe((responseData: any) => {
      this.addedTodo = responseData.data;
      this.modalService.dismissAll();
      this.toast.success(this.addedTodo.itemName +" successfully added");
      this.getAllTodoItems();
    },(err:any)=>{
      this.toast.error("Something went wrong");
    });
  }

  updateTodoItem(form){
    const item = {
      ...form.value,
      id: this.selectedItem.id
    };

    this.selectedItem = null;
    this.subs.sink = this.todoService.updateTodo(item).subscribe((responseData: any) => {
      this.updatedTodo = responseData.data;
      this.modalService.dismissAll();
      this.toast.success(item.itemName +" successfully updated");
      this.getAllTodoItems();
    },(err:any)=>{
      this.toast.error(err.error.message);
    });
  }

  markTodoAsDone(item){
    this.subs.sink = this.todoService.markTodoAsDone(item.id).subscribe((responseData: any) => {
      this.markedAsDoneTodo = responseData.data;
      this.toast.success(item.itemName +" marked as done");
      this.getAllTodoItems();
    },(err:any)=>{
      this.toast.error(err.error.message);
    });
  }

  deleteTodoItem(item){
    this.subs.sink = this.todoService.deleteTodo(item.id).subscribe((responseData: any) => {
      this.toast.success(item.itemName +" successfully deleted");
      this.getAllTodoItems();
    },(err:any)=>{
      this.toast.error(err.error.message);
    });
  }

  resetForm(){
    this.todoForm.reset();
  }

  openModal(template) {
    this.mode = "add";
    this.modalService.open(template, { size: 'lg' });
  }

  openEditModal(template, item){
    this.mode = "edit";
    this.selectedItem = item;
    this.todoForm.patchValue({
      itemName: item.itemName,
      description: item.description
    });
    this.modalService.open(template, { size: 'lg' });
  }

  close(){
    this.resetForm();
    this.modalService.dismissAll();
  }
}
