import { HttpClient } from '@angular/common/http';
import { Component, inject, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from "@angular/router";

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
  private request = inject(HttpClient);

  togglePasswordVisible(){
    this.isPasswordVisible.set(!this.isPasswordVisible())
  }

  

  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  });

  onSubmit(){
    //setting a logic to default before going through login logic, for consistent ui behaviour
    this.isPasswordEntered.set(true);
    this.isLoginSuccesful.set(true);

    const enteredEmail = this.loginForm.value.email;
    const enteredPassword = this.loginForm.value.password;
    
    this.request.post('http://localhost:8080/api/auth/login', {
      email: enteredEmail,
      password: enteredPassword
    }).subscribe({
      next: (res) => {
        console.log('success', res)
        this.isLoginSuccesful.set(true);
      },
       error: (err) => {
        console.log('ERROR', err);
        this.isLoginSuccesful.set(false);
      }
    })



    // if(enteredPassword === ''){
    //   this.isPasswordEntered.set(false);
    //   return;
    // }

  }

}
