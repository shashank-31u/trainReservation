import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { HomeComponent } from './components/home/home.component';
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/Userlogin/login.component';
import {MatFormFieldModule} from '@angular/material/form-field'
import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SearchTrainsComponent } from './components/search-trains/search-trains.component';
import { AdminLoginComponent } from './components/amdin-login/admin-login.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatTableModule} from '@angular/material/table';
import { MatNativeDateModule } from '@angular/material/core';
import { AvailableTrainComponent } from './components/available-train/available-train.component';
import { BookticketComponent } from './components/bookticket/bookticket.component';
import { ManagetrainComponent } from './components/managetrain/managetrain.component';
import { UpdatetrainComponent } from './components/updatetrain/updatetrain.component';
import { MatListModule } from '@angular/material/list';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { AddtrainComponent } from './components/addtrain/addtrain.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BookingComponent } from './components/booking/booking.component';
import { RazorpayComponent } from './components/razorpay/razorpay.component';
import { PaynowComponent } from './components/paynow/paynow.component';
import { AuthGuard } from './services/auth.guard';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';
@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    HomeComponent,
    SignupComponent,
    LoginComponent,
    DashboardComponent,
    SearchTrainsComponent,
    AdminLoginComponent,
    AdminDashboardComponent,
    AvailableTrainComponent,
    BookticketComponent,
    ManagetrainComponent,
    UpdatetrainComponent,
    AddtrainComponent,
    BookingComponent,
    RazorpayComponent,
    PaynowComponent,
    UnauthorizedComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    HttpClientModule,
    MatDatepickerModule,
    MatTableModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatListModule,
    ReactiveFormsModule

  ],
  providers: [
    provideClientHydration(),
    [AuthGuard]

    

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
