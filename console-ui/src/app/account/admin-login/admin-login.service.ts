import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { AppSettings } from '../../AppSettings';
import { AdminLoginModel } from './admin-login-model';

const apiIP = AppSettings;
@Injectable()
export class AdminLoginService {
    signupUrl = apiIP + 'login';

    constructor(private http: Http) { }

    createUser(_login: AdminLoginModel): Observable<number> {
        const cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: cpHeaders });
        return this.http.post(this.signupUrl, _login, options)
            //.map(success => success.status)
            .map((responce: Response) => <any>responce.json())
            .catch(this.handleError);

    }
    private handleError(error: Response | any) {
        // console.error(error.message || error);
        return Observable.throw(error.status);
    }
}
