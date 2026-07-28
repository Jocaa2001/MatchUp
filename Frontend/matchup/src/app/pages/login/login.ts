import { HttpClient } from '@angular/common/http';
import { Component, inject, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink, Router } from "@angular/router";
import { LoginResponse } from '../../models/responses/loginResponse';
import { LoginRequest } from '../../models/requests/loginRequest';
import { Auth } from '../../services/auth';
import { tap, switchMap } from 'rxjs';



@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  isLoginSuccesful = signal(true);
  isEmailAdressValid = signal(true);
  isPasswordEntered = signal(true);
  isPasswordVisible = signal(false);
  private authService = inject(Auth);
  private router = inject(Router);

  togglePasswordVisible(){
    this.isPasswordVisible.set(!this.isPasswordVisible())
  }

  loginForm = new FormGroup({
    email: new FormControl('', {
      validators: [Validators.required]
    }),
    password: new FormControl('',{
      validators: [Validators.required]
    })
  });

  onSubmit(){
    //setting a logic to default before going through login logic, for consistent ui behaviour
    this.isPasswordEntered.set(true);
    this.isLoginSuccesful.set(true);

    const enteredEmail = this.loginForm.value.email;
    const enteredPassword = this.loginForm.value.password;
    
    if(enteredPassword === ''){
      this.isPasswordEntered.set(false);
      return;
    }

    const requestBody: LoginRequest = {
      email: enteredEmail ?? '',
      password: enteredPassword ?? ''
    }

this.authService.login(requestBody).subscribe({
  next: () => {
    this.isLoginSuccesful.set(true);
    this.router.navigate(['']);
  },
  error: (err) => {
    this.isLoginSuccesful.set(false);
    console.error(err);
  }
});



    

  }

}
