import { Injectable } from '@angular/core';
import { NotifierService } from 'angular-notifier';

@Injectable()
export class NotifyService {
    private notifierService: NotifierService;

    constructor(notifierService: NotifierService) {
        this.notifierService = notifierService;
    }

    // Show notifier.
    default(message: string): void {
        this.notifierService.notify('default', message);
    }
    info(message: string): void {
        this.notifierService.notify('info', message);
    }
    success(message: string): void {
        this.notifierService.notify('success', message);
    }
    error(message: string): void {
        this.notifierService.notify('error', message);
    }
    warning(message: string): void {
        this.notifierService.notify('warning', message);
    }

    // Hiden notifier.
    esconderNotificacionesAntiguas(): void {
        this.notifierService.hideOldest();
    }
    esconderNotificacionesNuevas(): void {
        this.notifierService.hideNewest();
    }
    esconderNotificacionesTodas(): void {
        this.notifierService.hideAll();
    }
}
