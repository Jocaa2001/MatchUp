import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navbar } from "./navbar/navbar";
import { CommonModule } from '@angular/common';
import { LandingPage } from "./pages/landing-page/landing-page";
import { Login } from "./pages/login/login";


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Navbar, CommonModule, LandingPage, Login],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('matchup');
}
