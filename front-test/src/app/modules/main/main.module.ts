import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NotifierModule, NotifierOptions } from 'angular-notifier';
import { AppMaterialModule } from '../../app.material.module';
import { DirectiveModule } from 'src/app/core/directives/directive.module';

// Routes.
import { PrincipalRoute } from './main.route';

// Services.
import { SessionService } from '../../core/services/session.service';
import { GuardService } from '../../core/services/guard.service';
import { NotifyService } from '../../core/services/notify.service';

// Components.
import { LoginComponent } from './components/login/login.component';
import { PageNotFoundComponent } from './components/page-notfound/page.notfound.component';
import { ContainerComponent } from './components/container/container.component';
import { HeaderComponent } from './components/header/header.component';

const customNotifierOptions: NotifierOptions = {
  position: {
    horizontal: { position: 'middle', distance: 8 },
    vertical: { position: 'bottom', distance: 38, gap: 10 }
  },
  theme: 'material',
  behaviour: { autoHide: 4000, onClick: 'hide', onMouseover: 'pauseAutoHide', showDismissButton: true, stacking: 4 },
  animations: {
    enabled: true, show: { preset: 'slide', speed: 300, easing: 'ease' },
    hide: { preset: 'fade', speed: 300, easing: 'ease', offset: 50 },
    shift: { speed: 300, easing: 'ease' },
    overlap: 150
  }
};

@NgModule({
  declarations: [
    LoginComponent,
    PageNotFoundComponent,
    ContainerComponent,
    HeaderComponent
  ],
  imports: [
    DirectiveModule,
    CommonModule,
    PrincipalRoute,
    FormsModule,
    ReactiveFormsModule,
    AppMaterialModule,
    FormsModule,
    NotifierModule.withConfig(customNotifierOptions)
  ],
  entryComponents: [],
  providers: [
    SessionService,
    GuardService,
    NotifyService
  ],
  exports: [
  ]
})
export class PrincipalModule {
}
