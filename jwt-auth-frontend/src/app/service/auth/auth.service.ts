import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { TokenService } from '../token/token.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _tokenService: TokenService = new TokenService();
  private _loginAPI = 'http://localhost:8888/api/auth/login';

  constructor(
    private _httpClient: HttpClient
  ) {
  }

  logout(): void {
    this._tokenService.removeToken();
  }

  isLoggedIn(): boolean {
    return (this._tokenService.isTokenValidToLogin()) ? true : false;
  }

  isSuperuser(): boolean {
    return true;
  }

  login(credential: any): Observable<any> {
    return this._httpClient.post<any>(this._loginAPI, credential);
  }

}
