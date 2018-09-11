import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
    templateUrl: 'app/home/home.component.html',
    styleUrls: [ 'assets/css/world.css' ]
})
export class HomeComponent {
    constructor(private router: Router) { }

    public navigate(target: string) {
        this.router.navigateByUrl(target);
    }
}
