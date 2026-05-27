import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, ɵInternalFormsSharedModule } from '@angular/forms';
import { ProfileSetupService } from '../../services/profileSetup.service';
import { UserProfileRequest } from '../../models/requests/userProfileRequest';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-setup',
  imports: [ɵInternalFormsSharedModule,ReactiveFormsModule],
  templateUrl: './profile-setup.html',
  styleUrl: './profile-setup.scss',
})
export class ProfileSetup {

  private profileSetupService = inject(ProfileSetupService);
  private router = inject(Router);


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
       this.router.navigate(['/']);
      },
      error: (err) => {
        console.log('ERROR', err);
      }
    })

  }

}
