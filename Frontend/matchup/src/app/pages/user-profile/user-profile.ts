import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from "@angular/router";
import { ProfileDetailsService } from '../../services/profileDetals.service';
import { DatePipe } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UserProfileRequest } from '../../models/requests/userProfileRequest';
import { ProfileSetupService } from '../../services/profileSetup.service';

@Component({
  selector: 'app-user-profile',
  imports: [RouterLink, DatePipe, ReactiveFormsModule],
  templateUrl: './user-profile.html',
  styleUrl: './user-profile.scss',
})
export class UserProfile implements OnInit {
  private profileDetailsService = inject(ProfileDetailsService);
  private profileSetupService = inject(ProfileSetupService);
  profileForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    birthDate: new FormControl(''),
    phone: new FormControl(''),
    city: new FormControl('')
  });

  user = this.profileDetailsService.user;
  isEditing = signal(false);

  ngOnInit(): void {
    this.profileDetailsService.getLoggedUser().subscribe({
      next: (user) => {
        this.profileDetailsService.user.set(user);
        //console.log('USER LOADED:', user);
        this.profileForm.patchValue({
          firstName: user.profile.firstName,
          lastName: user.profile.lastName,
          phone: user.profile.phone,
          city: user.profile.city,
          birthDate: user.profile.birthDate
        });

      },
      error: (err) => console.error(err)
    });
  }

  toggleEdit() {
    if (this.isEditing()) {

      const user = this.user();

      if (user) {
        this.profileForm.patchValue({
          firstName: user.profile.firstName,
          lastName: user.profile.lastName,
          phone: user.profile.phone,
          city: user.profile.city,
          birthDate: user.profile.birthDate
        });
      }
    }
    
    this.isEditing.update(v => !v);
  }

  saveProfile() {

    const request: UserProfileRequest = {
    firstName: this.profileForm.value.firstName ?? '',
    lastName: this.profileForm.value.lastName ?? '',
    city: this.profileForm.value.city ?? '',
    phone: this.profileForm.value.phone ?? '',
    birthDate: this.profileForm.value.birthDate ?? '',
    avatarUrl: this.user()?.profile?.avatarUrl
  };

   this.profileSetupService.setupProfile(request)
    .subscribe({
      next: (response) => {
        this.isEditing.set(false);
        window.location.reload();
      },
      error: (err) => {
        console.error(err);
      }
    });
    
  }
}

