import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, ɵInternalFormsSharedModule } from '@angular/forms';
import { ProfileSetupService } from '../../services/profileSetup.service';
import { UserProfileRequest } from '../../models/requests/userProfileRequest';
import { Router } from '@angular/router';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-profile-setup',
  imports: [ɵInternalFormsSharedModule,ReactiveFormsModule, TranslatePipe],
  templateUrl: './profile-setup.html',
  styleUrl: './profile-setup.scss',
})
export class ProfileSetup {

  private profileSetupService = inject(ProfileSetupService);
  private router = inject(Router);
  avatarFile: File | null = null;
  avatarPreview: string | null = null;


  //for now without avatar
  profileSetupForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    birthDate: new FormControl(''),
    phone: new FormControl(''),
    city: new FormControl('')
  });

  onSubmit(){

    const enteredFirstName = this.profileSetupForm.controls.firstName.value
    const enteredLastName = this.profileSetupForm.controls.lastName.value
    const enteredBirthDate = this.profileSetupForm.controls.birthDate.value
    const enteredPhoneNumber = this.profileSetupForm.controls.phone.value
    const enteredCity = this.profileSetupForm.controls.city.value

    const request: UserProfileRequest = {
      firstName: enteredFirstName ?? '',
      lastName: enteredLastName ?? '',
      birthDate: enteredBirthDate ?? '',
      phone: enteredPhoneNumber ?? '',
      city: enteredCity ?? ''
    }

    this.profileSetupService.setupProfile(request).subscribe({
      next: (res) => {
        console.log('success', res)

        if (!this.avatarFile) {
        this.router.navigate(['/']);
        return;
      }
        this.profileSetupService.uploadAvatar(this.avatarFile).subscribe({

          next: (fileName) => {
            console.log('Avatar uploaded successfully:', fileName);

            this.router.navigate(['/']);
          },

          error: (err) => {
            console.error('Avatar upload failed:', err);
          }

        });
      },
      error: (err) => {
        console.log('ERROR', err);
      }
    })
  }

  onAvatarSelected(event: Event) {
  const input = event.target as HTMLInputElement;
  console.log('selected avatar')
  if (!input.files || input.files.length === 0) {
    return;
  }

  const file = input.files[0];

  this.avatarFile = file;
  this.avatarPreview = URL.createObjectURL(file);
}



}
