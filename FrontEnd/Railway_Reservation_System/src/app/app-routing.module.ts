import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/Userlogin/login.component';
import path from 'path';
import { SignupComponent } from './components/signup/signup.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthGuard } from './services/auth.guard';
import { SearchTrainsComponent } from './components/search-trains/search-trains.component';
import { AdminLoginComponent } from './components/amdin-login/admin-login.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AvailableTrainComponent } from './components/available-train/available-train.component';
import { ManagetrainComponent } from './components/managetrain/managetrain.component';
import { UpdatetrainComponent } from './components/updatetrain/updatetrain.component';
import { AddtrainComponent } from './components/addtrain/addtrain.component';
import { BookticketComponent } from './components/bookticket/bookticket.component';
import { BookingComponent } from './components/booking/booking.component';
import { RazorpayComponent } from './components/razorpay/razorpay.component';
import { PaynowComponent } from './components/paynow/paynow.component';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';
import { adminguardGuard } from './services/adminguard.guard';

const routes: Routes = [
  {
    path:"",
    component:HomeComponent,
    pathMatch:"full"
  },
  {
    path:"Dashboard",
    component:DashboardComponent,
    pathMatch:"full",
    canActivate: [AuthGuard],
    data: { expectedRole: 'user' }
  
  },
  {
    path:"UserLogin",
    component:LoginComponent,
    pathMatch:"full",
    
  },
  {
    path:"Signup",
    component:SignupComponent,
    pathMatch:"full"
  },
  {
    path:"Searchtrains",
    component:SearchTrainsComponent,
    pathMatch:"full"
  },
  {
    path:"AdminLogin",
    component:AdminLoginComponent,
    pathMatch:"full",
   
  },
  {
    path:"AdminDashboard",
    component:AdminDashboardComponent,
    pathMatch:"full",
    canActivate: [adminguardGuard],
    data: { expectedRole: 'admin' }
    
    
  },
  {
    path: 'Unauthorized',
    component: UnauthorizedComponent,
    pathMatch: 'full'
  },
  {
    path:"AvailableTrain",
    component:AvailableTrainComponent,
    pathMatch:"full",
    canActivate: [AuthGuard],
    data: { expectedRole: 'user' }
    
  },
  {
    path:"ManageTrain",
    component:ManagetrainComponent,
    pathMatch:"full",
    canActivate: [adminguardGuard],
    data: { expectedRole: 'admin' }
    


    
  },
  {
    path:"UpdateTrain",
    component:UpdatetrainComponent,
    pathMatch:"full",
    canActivate: [adminguardGuard],
    data: { expectedRole: 'admin' }
  },
  {
    path:"AddTrain",
    component:AddtrainComponent,
    pathMatch:"full",
    canActivate: [adminguardGuard],
    data: { expectedRole: 'admin' }
  },
  {
    path:"Bookticket",
    component:BookticketComponent,
    pathMatch:"full",
    canActivate: [AuthGuard],
    data: { expectedRole: 'user' }

  },
  {
    path:"Booking",
    component:BookingComponent,
    pathMatch:"full"
  },
  {
    path:"Razorpay",
    component:RazorpayComponent,
    pathMatch:"full",
    // canActivate:[AuthGuard]

  },
  {
    path:"Paynow",
    component:PaynowComponent,
    pathMatch:"full",
    canActivate: [AuthGuard],
    data: { expectedRole: 'user' }
    // canActivate:[AuthGuard]

  },
  


  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
