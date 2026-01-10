import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { StudioService, Studio } from '../../../core/services/studio.service';
import { StudioCardComponent } from '../studio-card/studio-card.component';
import { BookingModalComponent } from '../../bookings/booking-modal/booking-modal.component';

@Component({
    selector: 'app-studio-list',
    standalone: true,
    imports: [
        CommonModule,
        MatFormFieldModule,
        MatInputModule,
        MatIconModule,
        MatButtonModule,
        MatDialogModule,
        FormsModule,
        StudioCardComponent
    ],
    templateUrl: './studio-list.component.html',
    styleUrls: ['./studio-list.component.scss']
})
export class StudioListComponent implements OnInit {
    studios: Studio[] = [];
    searchArea: string = '';

    constructor(
        private studioService: StudioService,
        private dialog: MatDialog
    ) { }

    ngOnInit(): void {
        this.loadStudios();
    }

    loadStudios() {
        this.studioService.getAllStudios().subscribe(data => {
            this.studios = data;
        });
    }

    onSearch() {
        this.studioService.searchStudios(this.searchArea).subscribe(data => {
            this.studios = data;
        });
    }

    openBookingModal(studio: Studio) {
        this.dialog.open(BookingModalComponent, {
            width: '500px',
            data: { studio }
        });
    }
}
