import { HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";

export class JwtHttpInterceptor implements HttpInterceptor{
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log('interceptor')
        //intercepts http request and attaches bearer token to header
        const token = localStorage.getItem('token');

        if (!token) {
            return next.handle(req);
        }

        const authReq = req.clone({
        setHeaders: {
            Authorization: `Bearer ${token}`
            }
        });

        return next.handle(authReq);

    }

}