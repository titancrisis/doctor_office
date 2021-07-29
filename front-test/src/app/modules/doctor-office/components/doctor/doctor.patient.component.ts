import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatTableDataSource, MatPaginator, MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';

// Services.
import { ConsultationService } from '../../services/consultation.service';
import { NotifyService } from 'src/app/core/services/notify.service';

// Models.
import { ConsultationPatient } from '../../models/consultation.patient.model';

// Components.
import { HistoryComponent } from '../patient/history.component';

@Component({
    selector: 'app-doctor-patient-component',
    templateUrl: 'doctor.patient.component.html',
    styleUrls: ['../../../../styles/styles.scss']
})
export class DoctorPatientComponent implements OnInit {

    // Properties.
    private id: number;
    public dataSource;
    public headerDef: string[] = ['patient', 'birthDate', 'history'];
    @ViewChild(MatSort, { static: true }) sort: MatSort;
    @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

    constructor(
        public dialog: MatDialog,
        private consultationService: ConsultationService,
        private notifyService: NotifyService,
        public dialogRef: MatDialogRef<DoctorPatientComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any) {
        this.id = data.id;
    }

    ngOnInit(): void {
        this.loadPatientCare();
    }

    close(): void {
        this.dialogRef.close(false);
    }

    loadPatientCare(): void {
        this.consultationService.patientCare(this.id).subscribe(obj => {
            this.dataSource = new MatTableDataSource<ConsultationPatient>(obj);
            this.dataSource.sort = this.sort;
            this.dataSource.paginator = this.paginator;
        }, error => {
            this.notifyService.error(error.message);
        });
    }

    filter(value: string): void {
        this.dataSource.filter = value.trim().toLowerCase();
    }

    history(id: number): void {
        const dialogRef = this.dialog.open(HistoryComponent, {
            width: '700px',
            data: { id: id }
        });
        dialogRef.afterClosed().subscribe(() => { });
    }
}
