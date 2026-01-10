import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Studio } from '../../../core/services/studio.service';

@Component({
    selector: 'app-studio-card',
    standalone: true,
    imports: [CommonModule, MatCardModule, MatButtonModule, MatIconModule],
    templateUrl: './studio-card.component.html',
    styleUrls: ['./studio-card.component.scss']
})
export class StudioCardComponent {
    @Input() studio!: Studio;
    @Output() book = new EventEmitter<Studio>();

    onBook() {
        this.book.emit(this.studio);
    }
}
