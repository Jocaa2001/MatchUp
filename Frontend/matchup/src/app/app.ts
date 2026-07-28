import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navbar } from "./components/navbar/navbar";
import { CommonModule } from '@angular/common';
import { LandingPage } from "./pages/landing-page/landing-page";
import { Login } from "./pages/login/login";
import { Auth } from './services/auth';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Navbar, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('matchup');

  private authService = inject(Auth);

  constructor() {
    this.authService.loadCurrentUser();
  }
  

}
