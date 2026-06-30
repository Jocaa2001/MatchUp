import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from "@angular/router";
import { ProfileDetailsService } from '../../services/profileDetals.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-user-profile',
  imports: [RouterLink, DatePipe],
  templateUrl: './user-profile.html',
  styleUrl: './user-profile.scss',
})
export class UserProfile implements OnInit {
    private profileDetailsService = inject(ProfileDetailsService);

    user = this.profileDetailsService.user;

    ngOnInit(): void {
      this.profileDetailsService.getLoggedUser().subscribe({
    next: (user) => {
      this.profileDetailsService.user.set(user);
      console.log('USER LOADED:', user);
    },
    error: (err) => console.error(err)
    });
    }
}
  
