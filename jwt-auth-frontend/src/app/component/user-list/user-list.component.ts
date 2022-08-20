import { Component, OnInit } from '@angular/core';

import { User } from 'src/app/model/user/user.model';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[] = [];

  constructor(
    private _userService: UserService
  ) {
  }

  ngOnInit(): void {
    this.refreshUserList();
  }

  refreshUserList(): void {
    this._userService.getAllUsers().subscribe(
      response => {
        console.log('response', response);
        this.users = response;
      },
      error => {
        console.log('error', error);
      }
    );
  }

  deleteOnClick(userId: number): void {
    console.log('delete button works');
    this._userService.deleteUserById(userId).subscribe(
      response => {
        console.log('response', response);
      },
      error => {
        console.log('error', error);
        if (error.error !== 'no')
          this.refreshUserList();
      }
    );
  }

  updateOnClick(userId: number): void {
    console.log('update button works');
  }

}
