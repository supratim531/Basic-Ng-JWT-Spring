import { Router } from '@angular/router';
import { Component, DoCheck, OnInit } from '@angular/core';

import { AuthService } from 'src/app/service/auth/auth.service';
import { UserService } from 'src/app/service/user/user.service';
import { TokenService } from 'src/app/service/token/token.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, DoCheck {

  isLoggedIn: boolean = false;
  isSuperuser: boolean = false;
  username: string | null = 'Test';

  constructor(
    private _router: Router,
    private _authService: AuthService,
    private _userService: UserService,
    private _tokenService: TokenService
  ) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this._authService.isLoggedIn();
    this.username = this._tokenService.loadUsername();
    this.isSuperuser = this._authService.isSuperuser();
    this.checkUserExistance();
  }

  ngDoCheck(): void {
    this.isLoggedIn = this._authService.isLoggedIn();
    this.username = this._tokenService.loadUsername();
    this.isSuperuser = this._authService.isSuperuser();
  }

  checkUserExistance(): void {
    if (this.username !== null) {
      this._userService.getUserByUsername(this.username).subscribe(
        response => {
          console.log('response', response);
        },
        error => {
          console.log('error', error);
          this.isLoggedIn = false;
          this.isSuperuser = false;
          this._tokenService.removeToken();
          this._router.navigateByUrl('/login');
        }
      );
    } else {
      this.isLoggedIn = false;
      this.isSuperuser = false;
    }
  }

  logoutOnClick(): void {
    this._authService.logout();
    this._router.navigateByUrl('/login');
  }

}
