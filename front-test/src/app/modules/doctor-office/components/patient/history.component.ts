import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatTableDataSource, MatPaginator, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

// Services.
import { ConsultationService } from '../../services/consultation.service';
import { NotifyService } from 'src/app/core/services/notify.service';

// Models.
import { ConsultationHistory } from '../../models/consultation.history.model';

@Component({
    selector: 'app-history-component',
    templateUrl: 'history.component.html',
    styleUrls: ['../../../../styles/styles.scss']
})
export class HistoryComponent implements OnInit {

    // Properties.
    private id: number;
    public dataSource;
    public headerDef: string[] = ['doctor', 'specialty', 'createdAt', 'prescriptionDrugs', 'description'];
    @ViewChild(MatSort, { static: true }) sort: MatSort;
    @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

    constructor(
        private consultationService: ConsultationService,
        private notifyService: NotifyService,
        public dialogRef: MatDialogRef<HistoryComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any) {
        this.id = data.id;
    }

    ngOnInit(): void {
        this.loadHistory();
    }

    close(): void {
        this.dialogRef.close(false);
    }

    loadHistory(): void {
        this.consultationService.history(this.id).subscribe(obj => {
            this.dataSource = new MatTableDataSource<ConsultationHistory>(obj);
            this.dataSource.sort = this.sort;
            this.dataSource.paginator = this.paginator;
        }, error => {
            this.notifyService.error(error.message);
        });
    }

    filter(value: string): void {
        this.dataSource.filter = value.trim().toLowerCase();
    }
}
