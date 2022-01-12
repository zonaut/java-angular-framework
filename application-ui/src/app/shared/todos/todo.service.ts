import {Injectable} from '@angular/core';
import {Todo} from './todo';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {CreateTodo} from './create-todo';
import {apiPaths} from '../../config';
import {UpdateTodo} from './update-todo';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  constructor(private http: HttpClient) {
  }

  getAllTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(apiPaths.todos, httpOptions).pipe(
      tap(todos => console.log(todos)),
      catchError(this.handleError([]))
    );
  }

  getTodoById(id: number): Observable<Todo> {
    return this.http.get<Todo>(apiPaths.todos + `${id}`, httpOptions).pipe(
      tap(todo => console.log(todo)),
      catchError(this.handleError<Todo>())
    );
  }

  addTodo(createTodo: CreateTodo): Observable<Todo> {
    return this.http.post<Todo>(apiPaths.todos, createTodo, httpOptions).pipe(
      tap(todo => console.log(todo)),
      catchError(this.handleError<Todo>())
    );
  }

  deleteTodoById(id: string): Observable<null> {
    return this.http.delete<null>(apiPaths.todos + `${id}`, httpOptions).pipe(
      catchError(this.handleError<null>())
    );
  }

  updateTodo(id: string, updateTodo: UpdateTodo): Observable<Todo> {
    return this.http.put<Todo>(apiPaths.todos + `${id}`, updateTodo, httpOptions).pipe(
      tap(todo => console.log(todo)),
      catchError(this.handleError<Todo>())
    );
  }

  toggleTodoComplete(todo: Todo) {
    todo.done = !todo.done;
    return this.updateTodo(todo.id, <UpdateTodo>{
      title: todo.title
    });
  }

  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      return of(result as T);
    };
  }
}
