import { Component, HostListener, inject, Input } from '@angular/core';

import { RouterLink } from '@angular/router';

import { Auth } from '../../../services/auth';

import { UserResponse } from '../../../models/responses/userResponse';

import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-user-menu-dropdown',
  imports: [RouterLink, TranslatePipe],
  templateUrl: './user-menu-dropdown.html',
  styleUrl: './user-menu-dropdown.scss',
})
export class UserMenuDropdown {

  private authService = inject(Auth);

  @Input() user: UserResponse | null = null;

  avatarUrl = this.authService.avatarUrl;

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