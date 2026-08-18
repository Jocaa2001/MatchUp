import { Component, inject } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Auth } from '../../services/auth';
import { UserMenu } from "../user-menu/user-menu";
import { TranslatePipe } from '@ngx-translate/core'
import { LanguageService } from '../../services/language.service';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, UserMenu, TranslatePipe],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class Navbar {

  private authService = inject(Auth);
  languageService = inject(LanguageService);
  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }


  changeLanguage(language: 'en' | 'sr'): void {
    this.languageService.changeLanguage(language);
  }

}
