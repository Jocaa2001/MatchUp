import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { LandingPage } from './pages/landing-page/landing-page';
import { Register } from './pages/register/register';
import { ProfileSetup } from './pages/profile-setup/profile-setup';
import { Events } from './pages/events/events';
import { UserMenu } from './components/user-menu/user-menu';
import { UserProfile } from './pages/user-profile/user-profile';
import { CreateEvent } from './pages/create-event/create-event';

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
    },
    {
        path: 'events',
         children: [
            {
            path: '',
            component: Events
            },
            {
            path: 'create',
            component: CreateEvent
            }
        ]

    },
    {
        path: 'user-profile',
        component: UserProfile
    }
];
