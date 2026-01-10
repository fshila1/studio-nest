import { Routes } from '@angular/router';
import { StudioListComponent } from './features/studios/studio-list/studio-list.component';
import { BookingListComponent } from './features/bookings/booking-list/booking-list.component';

export const routes: Routes = [
    { path: '', component: StudioListComponent },
    { path: 'bookings', component: BookingListComponent },
    { path: '**', redirectTo: '' }
];
