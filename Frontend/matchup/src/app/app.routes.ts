import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { LandingPage } from './pages/landing-page/landing-page';
import { Register } from './pages/register/register';
import { ProfileSetup } from './pages/profile-setup/profile-setup';

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
    },
    {
        path: 'profile-setup',
        component: ProfileSetup
    }
];
