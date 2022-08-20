import { Component, OnInit } from '@angular/core';

import { AuthService } from 'src/app/service/auth/auth.service';
import { Employee } from 'src/app/model/employee/employee.model';
import { EmployeeService } from 'src/app/service/employee/employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees: Employee[] = [];
  isSuperuser: boolean = false;

  constructor(
    private _authService: AuthService,
    private _employeeService: EmployeeService
  ) {
  }

  ngOnInit(): void {
    this.refreshEmployeesList();
  }

  ngDoCheck(): void {
    this.isSuperuser = this._authService.isSuperuser();
  }

  refreshEmployeesList(): void {
    this._employeeService.getAllEmployees().subscribe(
      response => {
        console.log('response', response);
        this.employees = response;
      },
      error => {
        console.log('error', error);
      }
    );
  }

  deleteOnClick(employeeId: number): void {
    console.log('delete button works');
  }

  updateOnClick(employeeId: number): void {
    console.log('update button works');
  }

  registerOnClick(): void {
    console.log('register button works');
  }

}
