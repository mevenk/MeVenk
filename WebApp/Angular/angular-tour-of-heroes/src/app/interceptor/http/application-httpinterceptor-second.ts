
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';


@Injectable()
export class ApplicationHTTPInterceptorSecond implements HttpInterceptor {

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log('Request 2I' + JSON.stringify(request));
        // add a custom header
        const clonedRequest = request.clone({
            headers: request.headers.set('app-language', 'it-2i')
        });
        console.log('Request after clone  2I' + JSON.stringify(clonedRequest));
        return next.handle(clonedRequest).pipe(
            tap((evt: HttpEvent<any>) => {
                if (evt instanceof HttpResponse) {
                    console.log('--->  2I status:', evt.status);
                    console.log('---> 2I filter:', clonedRequest.params.get('filter'));
                }
            }));
    }

}
