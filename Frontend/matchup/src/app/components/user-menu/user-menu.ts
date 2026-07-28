import { Component, inject } from '@angular/core';
import { UserMenuDropdown } from "./user-menu-dropdown/user-menu-dropdown";
import { Auth } from '../../services/auth';

@Component({
  selector: 'app-user-menu',
  imports: [UserMenuDropdown],
  templateUrl: './user-menu.html',
  styleUrl: './user-menu.scss',
})
export class UserMenu {
  private authService = inject(Auth);
  user = this.authService.user;
  isOpen = false;

toggleDropdown() {
  this.isOpen = !this.isOpen;
}
}
