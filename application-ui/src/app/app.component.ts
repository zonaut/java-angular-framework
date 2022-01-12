import {Component, OnInit} from '@angular/core';
import {Todo} from './shared/todos/todo';
import {CreateTodo} from './shared/todos/create-todo';
import {TodoService} from './shared/todos/todo.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'application-ui';

  todos: Todo[] = [];

  createTodo: CreateTodo = <CreateTodo>{
    title: ''
  }

  constructor(private todoService: TodoService) {
  }

  ngOnInit(): void {
    this.todoService
      .getAllTodos()
      .subscribe(
        (todos) => {
          this.todos = todos;
        }
      );
  }

  addTodo(createTodo: CreateTodo) {
    this.todoService
      .addTodo(createTodo)
      .subscribe(
        (newTodo) => {
          this.todos = this.todos.concat(newTodo);
          this.createTodo = <CreateTodo>{
            title: ''
          }
        }
      );
  }

  toggleTodoComplete(todo: Todo) {
    this.todoService
      .toggleTodoComplete(todo)
      .subscribe(
        (updatedTodo) => {
          todo = updatedTodo;
        }
      );
  }

  removeTodo(todo: Todo) {
    this.todoService
      .deleteTodoById(todo.id)
      .subscribe(
        (_) => {
          this.todos = this.todos.filter((t) => t.id !== todo.id);
        }
      );
  }
}
