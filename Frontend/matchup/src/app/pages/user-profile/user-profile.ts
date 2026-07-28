import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from "@angular/router";
import { DatePipe } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UserProfileRequest } from '../../models/requests/userProfileRequest';
import { ProfileSetupService } from '../../services/profileSetup.service';
import { Auth } from '../../services/auth';
import { UserResponse } from '../../models/responses/userResponse';

@Component({
  selector: 'app-user-profile',
  imports: [RouterLink, DatePipe, ReactiveFormsModule],
  templateUrl: './user-profile.html',
  styleUrl: './user-profile.scss',
})
export class UserProfile implements OnInit {
  private profileSetupService = inject(ProfileSetupService);
  private authService = inject(Auth);
  profileForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    birthDate: new FormControl(''),
    phone: new FormControl(''),
    city: new FormControl('')
  });

  user = this.authService.user;
  isEditing = signal(false);

  ngOnInit(): void {
      
  }

  toggleEdit() {
    if (!this.isEditing()) {
    this.profileForm.patchValue({
      firstName: this.user()?.profile?.firstName,
      lastName: this.user()?.profile?.lastName,
      phone: this.user()?.profile?.phone,
      city: this.user()?.profile?.city,
      birthDate: this.user()?.profile?.birthDate
    });
  }
    
    this.isEditing.update(v => !v);
  }

  saveProfile() {

    const request: UserProfileRequest = {
    firstName: this.profileForm.value.firstName!,
    lastName: this.profileForm.value.lastName!,
    city: this.profileForm.value.city!,
    phone: this.profileForm.value.phone!,
    birthDate: this.profileForm.value.birthDate!,
    avatarUrl: this.user()?.profile?.avatarUrl
  };

   this.profileSetupService.setupProfile(request)
    .subscribe({
      next: (user) => {    
        this.authService.setCurrentUser(user);
        this.isEditing.set(false);
      },
      error: (err) => {
        console.error(err);
      }
    });
    
  }
}

