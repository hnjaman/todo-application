import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { API } from "./config.service";

@Injectable({
  providedIn: "root",
})
export class TodoService {
  baseUrl = environment.HOST_URL;

  constructor(private http: HttpClient) {}

  addTodo(data) {
    return this.http.post(this.baseUrl + API.addTodo, data, {});
  }

  updateTodo(data) {
    return this.http.put(this.baseUrl + API.editTodo, data, {});
  }

  getAllTodos() {
    return this.http.get(this.baseUrl + API.getAllTodos);
  }

  getTodoById(id) {
    return this.http.get(this.baseUrl + API.getTodoById + `/${id}`);
  }

  deleteTodo(id) {
    return this.http.delete(this.baseUrl + API.deleteTodo + `/${id}`);
  }

  markTodoAsDone(id) {
    return this.http.put(this.baseUrl + `/todos/${id}/done`, {});
  }
}
