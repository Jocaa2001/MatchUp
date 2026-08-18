import { Component, inject } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Auth } from '../../services/auth';
import { UserMenu } from "../user-menu/user-menu";
import { TranslatePipe } from '@ngx-translate/core'

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, UserMenu, TranslatePipe],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class Navbar {

  private authService = inject(Auth);

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

}
