import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

import { Role } from 'src/app/model/role/role.model';
import { User } from 'src/app/model/user/user.model';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  role: Role = new Role();
  errorMessage: string | null = null;
  successMessage: string | null = null;

  registerForm: FormGroup = new FormGroup({
    fname: new FormControl(''),
    lname: new FormControl(''),
    uname: new FormControl(''),
    email: new FormControl(''),
    passw: new FormControl(''),
    cpassw: new FormControl('')
  });

  constructor(
    private _userService: UserService
  ) {
  }

  ngOnInit(): void {
  }

  private _prepareUserToRegister(): void {
    this.role.roleName = 'ROLE_USER';

    this.user.role = this.role;
    this.user.email = this.registerForm.value['email'];
    this.user.username = this.registerForm.value['uname'];
    this.user.password = this.registerForm.value['passw'];
    this.user.lastName = this.registerForm.value['lname'];
    this.user.firstName = this.registerForm.value['fname'];
  }

  registerOnClick(): void {
    this._prepareUserToRegister();
    this._userService.registerUser(this.user).subscribe(
      response => {
        console.log('response', response);
        this.errorMessage = null;
        this.successMessage = `${response.username} is registered successfully`;
      },
      error => {
        console.log('error', error);
        this.errorMessage = error.error;
        this.successMessage = null;
      }
    );
  }

  closeErrorMessage(): void {
    this.errorMessage = null;
  }

  closeSuccessMessage(): void {
    this.successMessage = null;
  }

}
