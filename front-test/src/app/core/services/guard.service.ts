import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { SessionService } from './session.service';

@Injectable()
export class GuardService implements CanActivate {

    constructor(
        private router: Router,
        private sessionService: SessionService) { }

    // Can activate.
    canActivate() {
        if (this.sessionService.isLogin()) {
            return true;
        } else {
            this.router.navigate(['./login']);
            return false;
        }
    }
}
