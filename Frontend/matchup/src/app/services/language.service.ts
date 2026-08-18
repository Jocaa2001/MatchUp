import { inject, Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Injectable({
  providedIn: 'root',
})
export class LanguageService {

  private translate = inject(TranslateService);

  currentLanguage = this.translate.currentLang;

  constructor() {
    this.translate.use(localStorage.getItem('language') || 'en');
  }

  changeLanguage(language: 'en' | 'sr'): void {
    this.translate.use(language);
    localStorage.setItem('language', language);
  }
}
