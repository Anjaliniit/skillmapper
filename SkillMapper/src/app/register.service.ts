import { Injectable } from '@angular/core';
import { Employee } from './registration/registration';
import { Http } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class RegisterService {

  constructor(private http: HttpClient) { }

register(employee:Employee):Observable<Object>
{
  console.log(employee);
  return this.http.post<Employee>('',employee,httpOptions);
}

}
