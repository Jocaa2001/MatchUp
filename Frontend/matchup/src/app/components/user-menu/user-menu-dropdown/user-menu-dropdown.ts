import { Component, HostListener, inject  } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Auth } from '../../../services/auth';

@Component({
  selector: 'app-user-menu-dropdown',
  imports: [RouterLink],
  templateUrl: './user-menu-dropdown.html',
  styleUrl: './user-menu-dropdown.scss',
})
export class UserMenuDropdown {
  private authService = inject(Auth);
  isOpen = false;

  toggleDropdown() {
    this.isOpen = !this.isOpen;

  }

  @HostListener('document:click')
  close() {
    this.isOpen = false;
  }

  logout() {
    this.authService.logout();
  }

}
