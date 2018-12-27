import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppRoutingModule } from './app-routing.module';
import {NgxPaginationModule} from 'ngx-pagination';
import { InfiniteScrollModule } from 'angular2-infinite-scroll';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule,MatSortModule , MatFormFieldModule , MatNativeDateModule,MatInputModule} from '@angular/material';
import { MatSelectModule } from '@angular/material/select';
import {MatDatepickerModule } from '@angular/material/datepicker';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateAccountComponent } from './account/create-account/create_account';
import { AdminLoginComponent } from './account/admin-login/admin-login.component';
import { AdminLoginService } from './account/admin-login/admin-login.service';
import { ConfigureAccountComponent } from './dashboard/configure-account/configure-account.component';
// import { CompareValidatorModule } from 'angular-compare-validator';
import { ForgotPasswordComponent } from './login/forgot-password/forgot-password.component';
import { GenerateOtpComponent } from './login/generate-otp/generate-otp.component';
import { ResetPasswordComponent } from './login/reset-password/reset-password.component';
import { SecurityConfigurationComponent } from './dashboard/security-configuration/security-configuration.component';
import { CreateTokenComponent } from './dashboard/create-token/create-token.component';
import { UserAccountLedgerComponent } from './dashboard/user-account-ledger/user-account-ledger.component';
import { NewDatePipePipe } from './new-date-pipe.pipe';
import { AccountLedgerService } from './dashboard/user-account-ledger/user-account-ledger.service';
import { TokenValuePipe } from './token-value.pipe';
import { UserManagementComponent } from './dashboard/user-management/user-management.component';
import { RateExchangerComponent } from './dashboard/rate-exchanger/rate-exchanger.component';
import { HomeComponent } from './dashboard/home/home.component';
import { UnderDevelopmentComponent } from './dashboard/under-development/under-development.component';
import { AlertComponent } from './alert/alert.component';
import { VerificationComponent } from './account/verification/verification.component';
import { ClientComponent } from './account/client/client.component';
import { ProductComponent } from './dashboard/product/product.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { FeeComponent } from './account/fee/fee.component';
import { WithrawalFeeComponent } from './account/fee/withrawal-fee/withrawal-fee.component';
import { SettlementFeeComponent } from './account/fee/settlement-fee/settlement-fee.component';
// import { AlertNotification } from './alert-notification';

// import { NgBootstrapFormValidationModule } from 'ng-bootstrap-form-validation';

// import { UserService } from './login/login.services';

// import {CUSTOM_ERRORS} from "./utils/custom-errors";
// import {  AssignRoleComponent} from './user_role/assign_role.component';
// import { AddUserComponent } from './account/add-user/add-user.component';
// import { FormsModule } from '@angular/forms';
// import { AdminManageUsersComponent } from './account/admin-manage-users/admin-manage-users.component';

// import { LoaderComponent } from './loader/loader.component';

// import { CustomDatePipe } from "./utils/date-pipe";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    CreateAccountComponent,
    AdminLoginComponent,
    ConfigureAccountComponent,
    ForgotPasswordComponent,
    GenerateOtpComponent,
    ResetPasswordComponent,
    SecurityConfigurationComponent,
    CreateTokenComponent,
    UserAccountLedgerComponent,
    NewDatePipePipe,
    TokenValuePipe,
    UserManagementComponent,
    RateExchangerComponent,
    HomeComponent,
    UnderDevelopmentComponent,
    AlertComponent,
    VerificationComponent,
    ClientComponent,
    ProductComponent,
    ErrorPageComponent,
    FeeComponent,
    WithrawalFeeComponent,
    SettlementFeeComponent,
    
    // AssignRoleComponent,
    // AddUserComponent,
    // AdminManageUsersComponent,
    // SafePipe,
    // LoaderComponent,
    
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    InfiniteScrollModule,
    BrowserAnimationsModule,
    MatSortModule,
    MatTableModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    MatSelectModule
    // CompareValidatorModule
     // NgBootstrapFormValidationModule.forRoot(CUSTOM_ERRORS),
    // FormsModule,
  ],
  exports: [
    MatSortModule,
    MatTableModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    MatSelectModule
  ],
  providers: [ AdminLoginService, AccountLedgerService,AlertComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
