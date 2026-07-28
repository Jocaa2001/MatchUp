import { Component, HostListener, inject, Input, OnInit  } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Auth } from '../../../services/auth';
import { UserResponse } from '../../../models/responses/userResponse';

@Component({
  selector: 'app-user-menu-dropdown',
  imports: [RouterLink],
  templateUrl: './user-menu-dropdown.html',
  styleUrl: './user-menu-dropdown.scss',
})
export class UserMenuDropdown {
  private authService = inject(Auth);
  @Input() user: UserResponse | null = null;
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
