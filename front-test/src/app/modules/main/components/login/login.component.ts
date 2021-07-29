import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

// Services.
import { SessionService } from '../../../../core/services/session.service';
import { NotifyService } from '../../../../core/services/notify.service';

@Component({
  selector: 'app-login',
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.scss']
})
export class LoginComponent implements OnInit {

  // Properties.
  public formLogin: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService,
    private notifyService: NotifyService) { }

  ngOnInit(): void {
    this.formLogin = this.fb.group({
      user: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  // Login method.
  public login(form: FormGroup): void {

    // Verify credentials.
    if (form.value.user === 'admin' && form.value.password === 'admin') {
      this.sessionService.login(form.value.user);
      this.router.navigate(['./doctoroffice/patient']);
    }
    else {
      this.notifyService.warning('El usuario o la contraseña son incorrectos');
    }
  }
}
