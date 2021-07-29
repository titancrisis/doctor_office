import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

// Services.
import { SessionService } from '../../../../core/services/session.service';

@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',
  styleUrls: ['header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router,
    private sessionService: SessionService,) { }

  ngOnInit() { }

  patient(): void {
    this.router.navigate(['./doctoroffice/patient']);
  }

  doctor(): void {
    this.router.navigate(['./doctoroffice/doctor']);
  }

  consultation(): void {
    this.router.navigate(['./doctoroffice/consultation']);
  }

  logout() {
    this.sessionService.logout();
    this.router.navigate(['./login']);
  }
}
