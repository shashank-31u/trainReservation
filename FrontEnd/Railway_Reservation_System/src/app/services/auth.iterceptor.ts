// import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
// import { Injectable } from "@angular/core";
// import { Observable } from "rxjs";
// import { LoginService } from "./Userlogin.service";
// import { AdminloginService } from "./adminlogin.service";

// const TOKEN_HEADER="Authorization";

// @Injectable()
// export class AuthInterceptor implements HttpInterceptor{
//     constructor(private loginService: LoginService){}
//     intercept(req: HttpRequest<any>, next: HttpHandler):
//      Observable<HttpEvent<any>> {
//         // add token request
//         let authReq=req;
//         const token=this.loginService.getToken();
//         if(token != null){
//             authReq=authReq.clone({setHeaders:{TOKEN_HEADER: `Bearer ${token}`},})
//         }
//         return next.handle(authReq);

        


//     }
    
// }