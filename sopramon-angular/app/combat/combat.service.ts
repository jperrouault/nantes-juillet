import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import { AppConfigService } from '../app-config.service';


@Injectable()
export class CombatService {
    private combats;
    private requestOptions: RequestOptions;

    constructor(private http: Http, private appConfigService: AppConfigService) {
        let myHeaders: Headers = new Headers();

        myHeaders.append('Authorization', this.appConfigService.getApiBasic());
        this.requestOptions = new RequestOptions({ headers: myHeaders });

        this.refresh();
    }

    public findAll() {
        return this.combats;
    }

    public startNewOne() {
        this.http
            .post(this.appConfigService.getApiUrl() + "combats", {
                boss: this.appConfigService.getApiUrl() + "bosses/1",
                sopramon2: this.appConfigService.getApiSopramonUrl(),
                arene: "Donjon"
            }, this.requestOptions)
            .subscribe(resp => {
                this.refresh();
                alert("COMBAT DEMARRE !")
            });
    }


    private refresh() {
        this.http
            .get(this.appConfigService.getApiUrl() + "combats/search/by-sopramon-id?id=1", this.requestOptions)
            .subscribe(resp => this.combats = resp.json()._embedded.combats);
    }
}
