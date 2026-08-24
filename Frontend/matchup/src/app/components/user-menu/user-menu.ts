import { Component, inject, OnInit, signal } from '@angular/core';

import { UserMenuDropdown } from "./user-menu-dropdown/user-menu-dropdown";

import { Auth } from '../../services/auth';
import { ProfileSetupService } from '../../services/profileSetup.service';

import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-user-menu',
  imports: [UserMenuDropdown, TranslatePipe],
  templateUrl: './user-menu.html',
  styleUrl: './user-menu.scss',
})
export class UserMenu implements OnInit {

  private authService = inject(Auth);
  private profileSetupService = inject(ProfileSetupService);

  user = this.authService.user;

  isOpen = false;

  avatarUrl = this.authService.avatarUrl;

  ngOnInit(): void {
    this.loadAvatar();
  }

loadAvatar() {
  this.profileSetupService.getAvatar().subscribe({
    next: (blob) => {
      this.authService.setAvatar(blob);
    },
    error: (err) => {
      console.error('Failed to load avatar:', err);
    }
  });
}
  toggleDropdown() {
    this.isOpen = !this.isOpen;
  }
}