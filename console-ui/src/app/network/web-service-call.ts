import { Injectable } from '@angular/core';
import { ApiConstants } from './api-constants'
import { HttpClientModule } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Http, Response, Headers, ResponseContentType } from '@angular/http';

@Injectable()
export class ServiceCall {

    static httpPostCall = function (reqJson: any, url: any, http: any) {
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return http.post(url, reqJson, { headers: headers })
            .map((responce: Response) => <any>responce.json())
    }
    static httpDownloadPostCall = function (reqJson: any, url: any, http: any) {
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return http.post(url, reqJson, { headers: headers ,
            responseType: ResponseContentType.Blob})
            // .map(response => new Blob([response._body],{ type: file }));
    }
    static httpAuthPostCall = function (url: any, reqJson: any, otp: any, Authorization: any, http: any) {
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('otp', otp);
        headers.append('Authorization', Authorization);
        return http.post(url, reqJson, { headers: headers })
            .map((responce: Response) => <any>responce.json())
    }

    static httpGetCall = function (url: any, reqJson: any, http: any) {
        return http.get(url, reqJson, { headers: "" })
            .map((responce: Response) => <any>responce.json())
    }
    static httpPutCall = function (url: any, reqJson: any, http: any) {
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return http.put(url, reqJson, { headers: "" })
            .map((responce: Response) => <any>responce.json())
    }
}