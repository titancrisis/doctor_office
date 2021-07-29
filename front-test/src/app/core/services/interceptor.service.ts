import { Observable } from 'rxjs/observable';
import { EMPTY } from 'rxjs';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/observable/empty';
import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';

@Injectable()
export class InterceptorService implements HttpInterceptor {

    constructor() { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req)
            .catch((event) => {
                if (event instanceof HttpErrorResponse) {
                    return this.error401(event);
                }
            });
    }

    private error401(error: HttpErrorResponse): Observable<any> {
        if (error.status === 401) {
            return EMPTY;
        }
        return Observable.throwError(error);
    }
}

