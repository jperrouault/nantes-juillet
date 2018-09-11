import { Injectable } from '@angular/core';

@Injectable()
export class AppConfigService {
    private apiUrl: string = "http://localhost:8080/api/";
    private apiSopramonUrl: string = "http://localhost:8080/api/sopramons/1";
    private apiUser: string = "fd";
    private apiPassword: string = "fd";


    public getApiUrl(): string {
        return this.apiUrl;
    }

    public getApiSopramonUrl(): string {
        return this.apiSopramonUrl;
    }

    public getApiBasic(): string {
        return 'Basic ' + btoa(this.apiUser + ':' + this.apiPassword);
    }
}
