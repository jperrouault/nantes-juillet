import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { AppConfigService } from './app-config.service';

import { HomeComponent } from './home/home.component';

import { StoreComponent } from './store/store.component';
import { StoreService } from './store/store.service';

import { CombatComponent } from './combat/combat.component';
import { CombatService } from './combat/combat.service';





//Configuration des routes
const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'store', component: StoreComponent },
    { path: 'combat', component: CombatComponent },
    { path: '', redirectTo: 'home', pathMatch: 'full' }
];



@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        RouterModule.forRoot(routes)
    ],

    declarations: [
        AppComponent,
        HomeComponent,
        StoreComponent,
        CombatComponent
    ],

    providers: [
        AppConfigService,
        StoreService,
        CombatService
    ],

    bootstrap: [ AppComponent ]
})
export class AppModule { }
