import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppMaterialModule } from './app.material.module';
import { DatePipe } from '@angular/common';
import { AppRoute } from './app.route';

// Components.
import { AppComponent } from './app.component';

// Services.
import { InterceptorService } from './core/services/interceptor.service';

// Modules.
import { PrincipalModule } from './modules/main/main.module';
import { DoctorOfficeModule } from './modules/doctor-office/doctor.office.module';
import { DirectiveModule } from './core/directives/directive.module';

import localeEs from '@angular/common/locales/es';
import { registerLocaleData } from '@angular/common';
registerLocaleData(localeEs);

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    DirectiveModule,
    AppRoute,
    BrowserModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    PrincipalModule,
    DoctorOfficeModule,
    HttpClientModule
  ],
  entryComponents: [],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true },
    { provide: LOCALE_ID, useValue: 'es' },
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
