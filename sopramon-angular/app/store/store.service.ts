import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import { AppConfigService } from '../app-config.service';


@Injectable()
export class StoreService {
    private items;
    private requestOptions: RequestOptions;

    constructor(private http: Http, private appConfigService: AppConfigService) {
        let myHeaders: Headers = new Headers();

        myHeaders.append('Authorization', this.appConfigService.getApiBasic());
        this.requestOptions = new RequestOptions({ headers: myHeaders });

        this.http
            .get(this.appConfigService.getApiUrl() + "items", this.requestOptions)
            .subscribe(resp => this.items = resp.json()._embedded.items);
    }

    public findAll() {
        return this.items;
    }

    public acheter(item) {
        this.http
            .post(this.appConfigService.getApiUrl() + "achats", {
                prix: item.prix,
                item: item._links.self.href,
                acheteur: this.appConfigService.getApiSopramonUrl()
            }, this.requestOptions)
            .subscribe(resp => alert("ACHAT OK !"));
    }
}
