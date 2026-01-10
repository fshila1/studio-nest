import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { BookingService, Booking } from '../../../core/services/booking.service';

@Component({
    selector: 'app-booking-list',
    standalone: true,
    imports: [CommonModule, MatTableModule],
    templateUrl: './booking-list.component.html',
    styleUrls: ['./booking-list.component.scss']
})
export class BookingListComponent implements OnInit {
    bookings: Booking[] = [];
    displayedColumns: string[] = ['studioName', 'userName', 'date', 'time', 'email'];

    constructor(private bookingService: BookingService) { }

    ngOnInit(): void {
        this.bookingService.getAllBookings().subscribe(data => {
            this.bookings = data;
        });
    }
}
