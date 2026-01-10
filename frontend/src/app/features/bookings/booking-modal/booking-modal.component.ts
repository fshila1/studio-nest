import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { BookingService } from '../../../core/services/booking.service';
import { Studio } from '../../../core/services/studio.service';

@Component({
    selector: 'app-booking-modal',
    standalone: true,
    imports: [
        CommonModule,
        ReactiveFormsModule,
        MatDialogModule,
        MatFormFieldModule,
        MatInputModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatSelectModule,
        MatButtonModule,
        MatSnackBarModule
    ],
    templateUrl: './booking-modal.component.html',
    styleUrls: ['./booking-modal.component.scss']
})
export class BookingModalComponent {
    bookingForm: FormGroup;
    timeSlots: string[] = [
        '09:00-10:00', '10:00-11:00', '11:00-12:00',
        '13:00-14:00', '14:00-15:00', '15:00-16:00', '16:00-17:00'
    ];
    availableSlots: string[] = [];

    constructor(
        private fb: FormBuilder,
        private bookingService: BookingService,
        private snackBar: MatSnackBar,
        public dialogRef: MatDialogRef<BookingModalComponent>,
        @Inject(MAT_DIALOG_DATA) public data: { studio: Studio }
    ) {
        this.bookingForm = this.fb.group({
            userName: ['', Validators.required],
            email: ['', [Validators.required, Validators.email]],
            bookingDate: [new Date(), Validators.required],
            timeSlot: ['', Validators.required]
        });

        this.availableSlots = [...this.timeSlots];

        // Watch for date changes to check availability
        this.bookingForm.get('bookingDate')?.valueChanges.subscribe(date => {
            if (date) {
                this.checkAvailability(date);
            }
        });
    }

    checkAvailability(date: Date) {
        const dateStr = date.toISOString().split('T')[0];
        this.bookingService.getAvailability(this.data.studio.id, dateStr).subscribe(bookedSlots => {
            this.availableSlots = this.timeSlots.filter(slot => !bookedSlots.includes(slot));
            // Reset selected slot if it's now booked
            const currentSlot = this.bookingForm.get('timeSlot')?.value;
            if (currentSlot && !this.availableSlots.includes(currentSlot)) {
                this.bookingForm.get('timeSlot')?.setValue('');
            }
        });
    }

    onSubmit() {
        if (this.bookingForm.valid) {
            const formValue = this.bookingForm.value;
            const booking = {
                studioId: this.data.studio.id,
                userName: formValue.userName,
                email: formValue.email,
                bookingDate: formValue.bookingDate.toISOString().split('T')[0],
                timeSlot: formValue.timeSlot
            };

            this.bookingService.createBooking(booking).subscribe({
                next: () => {
                    this.snackBar.open('Booking confirmed!', 'Close', { duration: 3000 });
                    this.dialogRef.close(true);
                },
                error: (err) => {
                    this.snackBar.open(err.error?.message || 'Booking failed', 'Close', { duration: 3000 });
                }
            });
        }
    }
}
