import { Component, inject } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Auth } from '../../services/auth';
import { UserMenu } from "../user-menu/user-menu";

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, UserMenu],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class Navbar {

  private authService = inject(Auth);

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

}
