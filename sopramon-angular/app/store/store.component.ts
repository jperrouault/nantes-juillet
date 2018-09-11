import { Component } from '@angular/core';
import { StoreService } from './store.service';


@Component({
    templateUrl: 'app/store/store.component.html'
})
export class StoreComponent {
    constructor(private storeService: StoreService) { }

    public acheter(item) {
        this.storeService.acheter(item);
    }
}
