import { Component, inject, signal } from '@angular/core';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from "@angular/router";
import { RegisterRequest } from '../../models/requests/registerRequest';
import { Auth } from '../../services/auth';

@Component({
  selector: 'app-register',
  imports: [RouterLink,ReactiveFormsModule],
  templateUrl: './register.html',
  styleUrl: './register.scss',
})
export class Register {
  isSomethingWrongErrorMessage = signal(false)
  isEmailAdressValid = signal(true);
  isPasswordValid = signal(false);
  isPasswordVisible = signal(false);
  private authService = inject(Auth);
  private router = inject(Router);


togglePasswordVisible(){
    this.isPasswordVisible.set(!this.isPasswordVisible())
  }

  registerForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  });

  onSubmit(){
    const enteredEmail = this.registerForm.value.email;
    const enteredPassword = this.registerForm.value.password;

    const requestBody: RegisterRequest = {
      email: enteredEmail ?? '',
      password: enteredPassword ?? '',
    }

    this.authService.register(requestBody).subscribe({
      next: (res) => {
        console.log('SUCCESS', res)
        localStorage.setItem('token', res.token)
        this.router.navigate(['/profile-setup']);
      },
       error: (err) => {
        console.log('ERROR', err);
      }
    });

  }

}
