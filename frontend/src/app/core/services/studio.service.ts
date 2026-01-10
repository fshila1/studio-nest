import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface Studio {
    id: number;
    name: string;
    type: string;
    area: string;
    address: string;
    latitude: number;
    longitude: number;
    amenities: string[];
    pricePerHour: number;
    rating: number;
    images: string[];
    description: string;
}

@Injectable({
    providedIn: 'root'
})
export class StudioService {
    private apiUrl = `${environment.apiUrl}/studios`;

    constructor(private http: HttpClient) { }

    getAllStudios(): Observable<Studio[]> {
        return this.http.get<Studio[]>(this.apiUrl);
    }

    getStudioById(id: number): Observable<Studio> {
        return this.http.get<Studio>(`${this.apiUrl}/${id}`);
    }

    searchStudios(area: string): Observable<Studio[]> {
        let params = new HttpParams();
        if (area) {
            params = params.set('area', area);
        }
        return this.http.get<Studio[]>(`${this.apiUrl}/search`, { params });
    }

    getNearbyStudios(lat: number, lng: number, radius: number): Observable<Studio[]> {
        let params = new HttpParams()
            .set('lat', lat.toString())
            .set('lng', lng.toString())
            .set('radius', radius.toString());
        return this.http.get<Studio[]>(`${this.apiUrl}/nearby`, { params });
    }
}
