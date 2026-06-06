import { Component } from '@angular/core';
import { UserMenuDropdown } from "./user-menu-dropdown/user-menu-dropdown";

@Component({
  selector: 'app-user-menu',
  imports: [UserMenuDropdown],
  templateUrl: './user-menu.html',
  styleUrl: './user-menu.scss',
})
export class UserMenu {
  isOpen = false;

toggleDropdown() {
  this.isOpen = !this.isOpen;
}
}
