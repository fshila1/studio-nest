import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface Booking {
    id?: number;
    studioId: number;
    studioName?: string;
    userName: string;
    email: string;
    bookingDate: string; // YYYY-MM-DD
    timeSlot: string;
    createdAt?: string;
}

@Injectable({
    providedIn: 'root'
})
export class BookingService {
    private apiUrl = `${environment.apiUrl}`;

    constructor(private http: HttpClient) { }

    createBooking(booking: Booking): Observable<Booking> {
        return this.http.post<Booking>(`${this.apiUrl}/bookings`, booking);
    }

    getAllBookings(): Observable<Booking[]> {
        return this.http.get<Booking[]>(`${this.apiUrl}/bookings`);
    }

    getAvailability(studioId: number, date: string): Observable<string[]> {
        let params = new HttpParams().set('date', date);
        return this.http.get<string[]>(`${this.apiUrl}/studios/${studioId}/availability`, { params });
    }
}
