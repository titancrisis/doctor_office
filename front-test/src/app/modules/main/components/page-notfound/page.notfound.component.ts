import { Component } from '@angular/core';
import { Location } from '@angular/common';

@Component({
    selector: 'app-page-notfound',
    templateUrl: 'page.notfound.component.html',
    styleUrls: ['page.notfound.component.scss']
})
export class PageNotFoundComponent {

    constructor(private location: Location) { }

    public back(): void {
        this.location.back();
    }
}
