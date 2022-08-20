import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Employee } from 'src/app/model/employee/employee.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private _baseURL = 'http://localhost:8888/api/employee';

  private _getAllEmployeesAPI = `${this._baseURL}/getAllEmployees`;

  constructor(
    private _httpClient: HttpClient
  ) {
  }

  getAllEmployees(): Observable<Employee[]> {
    return this._httpClient.get<Employee[]>(this._getAllEmployeesAPI);
  }

}
