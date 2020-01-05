import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable()
export class ApplicationHTTPInterceptor implements HttpInterceptor {

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log('Request' + JSON.stringify(request));
        // add a custom header
        const clonedRequest = request.clone({
            headers: request.headers.set('app-language', 'it')
        });
        console.log('Request after clone' + JSON.stringify(clonedRequest));
        return next.handle(clonedRequest).pipe(
            tap((evt: HttpEvent<any>) => {
                if (evt instanceof HttpResponse) {
                    console.log('---> status:', evt.status);
                    console.log('---> filter:', clonedRequest.params.get('filter'));
                }
            }));
    }
}
