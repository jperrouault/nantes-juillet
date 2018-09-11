import { Component } from '@angular/core';
import { CombatService } from './combat.service';


@Component({
    templateUrl: 'app/combat/combat.component.html'
})
export class CombatComponent {
    constructor(private combatService: CombatService) { }

    public startNewOne() {
        this.combatService.startNewOne();
    }
}
