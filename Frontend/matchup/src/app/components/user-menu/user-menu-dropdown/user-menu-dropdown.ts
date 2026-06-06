import { Component, HostListener  } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-user-menu-dropdown',
  imports: [RouterLink],
  templateUrl: './user-menu-dropdown.html',
  styleUrl: './user-menu-dropdown.scss',
})
export class UserMenuDropdown {
  isOpen = false;

  toggleDropdown() {
    this.isOpen = !this.isOpen;

  }

  @HostListener('document:click')
  close() {
    this.isOpen = false;
  }
}
