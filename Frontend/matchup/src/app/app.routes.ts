import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { LandingPage } from './pages/landing-page/landing-page';
import { Register } from './pages/register/register';

export const routes: Routes = [
    {
        path:'',
        component: LandingPage
    },
    {
        path: 'login',
        component: Login
    },
    {
        path: 'register',
        component: Register
    }
];
