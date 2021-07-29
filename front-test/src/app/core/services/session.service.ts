import { Injectable } from '@angular/core';

@Injectable()
export class SessionService {

    private session;
    private key = 'user';

    constructor() {
        this.session = sessionStorage;
    }

    public login(user: string): void {
        this.session.setItem(this.key, user);
    }

    public logout(): void {
        this.session.removeItem(this.key);
    }

    public isLogin(): boolean {
        const user: string = this.session.getItem(this.key);
        return (user != null) ? true : false;
    }
}
