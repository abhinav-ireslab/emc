import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { GenerateOtpComponent } from './login/generate-otp/generate-otp.component';
import { ForgotPasswordComponent } from './login/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './login/reset-password/reset-password.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateAccountComponent } from './account/create-account/create_account';
import { AdminLoginComponent } from './account/admin-login/admin-login.component';

import { HomeComponent } from './dashboard/home/home.component';
import { ConfigureAccountComponent } from './dashboard/configure-account/configure-account.component';
import { SecurityConfigurationComponent } from './dashboard/security-configuration/security-configuration.component';
import { CreateTokenComponent } from './dashboard/create-token/create-token.component';
import { UserManagementComponent } from './dashboard/user-management/user-management.component';
import { RateExchangerComponent } from './dashboard/rate-exchanger/rate-exchanger.component';
import { UserAccountLedgerComponent } from './dashboard/user-account-ledger/user-account-ledger.component';
import { UnderDevelopmentComponent } from './dashboard/under-development/under-development.component';
import { ClientComponent } from './account/client/client.component';
import { VerificationComponent } from './account/verification/verification.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { ProductComponent } from './dashboard/product/product.component';
import { FeeComponent } from './account/fee/fee.component';
import { SettlementFeeComponent } from './account/fee/settlement-fee/settlement-fee.component';
import { WithrawalFeeComponent } from './account/fee/withrawal-fee/withrawal-fee.component';


const appRoutes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'error', component: ErrorPageComponent },
    { path: 'forgot_password', component: ForgotPasswordComponent },
    { path: 'reset_password', component: ResetPasswordComponent },
    { path: 'validating_user', component: GenerateOtpComponent },
    { path: 'login/admin', component: AdminLoginComponent },
    {
        path: 'admin_account', component: CreateAccountComponent, children: [
            { path: 'client_mgmt', component: ClientComponent },
            { path: 'ekyc_mgmt', component: VerificationComponent },
            {
                path: 'fee_config', component: FeeComponent, children: [
                    { path: 'settlement', component: SettlementFeeComponent },
                    { path: 'withdrawal', component: WithrawalFeeComponent },
                    { path: '', redirectTo: 'settlement', pathMatch: 'full' }
                ]
            },
            { path: '', redirectTo: 'client_mgmt', pathMatch: 'full' }
        ]
    },
    {
        path: 'dashboard', component: DashboardComponent, children: [
            { path: 'home', component: HomeComponent },
            { path: 'account_config', component: ConfigureAccountComponent },
            { path: 'security_config', component: SecurityConfigurationComponent },
            { path: 'product_config', component: ProductComponent },
            { path: 'token_mgmt', component: CreateTokenComponent },
            { path: 'user_mgmt', component: UserManagementComponent },
            // { path: 'admin_user_mgmt', component: RateExchangerComponent },
            { path: 'business_ledger', component: UserAccountLedgerComponent },
            { path: 'BLR', component: UnderDevelopmentComponent },
            { path: 'STR', component: UnderDevelopmentComponent },
            { path: '', redirectTo: 'home', pathMatch: 'full' }
        ]
    },
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: '**', redirectTo: '/login', pathMatch: 'full' },
];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes)
    ],
    exports: [
        RouterModule
    ],
})
export class AppRoutingModule { }
