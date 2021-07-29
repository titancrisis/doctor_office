import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatTableDataSource, MatDialog, MatPaginator } from '@angular/material';
import { TransactionEnum } from 'src/app/core/enums/transaction.enum';

// Services.
import { PatientService } from '../../services/patient.service';
import { NotifyService } from 'src/app/core/services/notify.service';

// Models.
import { PatientList } from '../../models/patient.list.model';

// Components.
import { PatientComponent } from './patient.component';
import { HistoryComponent } from './history.component';

@Component({
    selector: 'app-patient-list-component',
    templateUrl: 'patient.list.component.html',
    styleUrls: ['../../../../styles/styles.scss']
})
export class PatientListComponent implements OnInit {

    // Properties.
    public dataSource;
    public headerDef: string[] = ['name', 'lastName', 'birthDate', 'address', 'history', 'update', 'delete'];
    @ViewChild(MatSort, { static: true }) sort: MatSort;
    @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

    constructor(
        public dialog: MatDialog,
        private patientService: PatientService,
        private notifyService: NotifyService) {
    }

    ngOnInit(): void {
        this.getAll();
    }

    getAll(): void {
        this.patientService.all().subscribe(obj => {
            this.dataSource = new MatTableDataSource<PatientList>(obj);
            this.dataSource.sort = this.sort;
            this.dataSource.paginator = this.paginator;
        }, error => {
            this.notifyService.error(error.message);
        });
    }

    filter(value: string): void {
        this.dataSource.filter = value.trim().toLowerCase();
    }

    create(): void {
        const dialogRef = this.dialog.open(PatientComponent, {
            width: '400px',
            data: { transaction: TransactionEnum.create, title: 'AGREGAR PACIENTE', id: null }
        });
        dialogRef.afterClosed().subscribe((result: boolean) => {
            if (result) { this.getAll(); }
        });
    }

    update(id: number): void {
        const dialogRef = this.dialog.open(PatientComponent, {
            width: '400px',
            data: { transaction: TransactionEnum.update, title: 'MODIFICAR PACIENTE', id: id }
        });
        dialogRef.afterClosed().subscribe((result: boolean) => {
            if (result) { this.getAll(); }
        });
    }

    delete(id: number): void {
        const dialogRef = this.dialog.open(PatientComponent, {
            width: '400px',
            data: { transaction: TransactionEnum.delete, title: 'ELIMINAR PACIENTE', id: id }
        });
        dialogRef.afterClosed().subscribe((result: boolean) => {
            if (result) { this.getAll(); }
        });
    }

    history(id: number): void {
        const dialogRef = this.dialog.open(HistoryComponent, {
            width: '700px',
            data: { id: id }
        });
        dialogRef.afterClosed().subscribe((result: boolean) => {
            if (result) { this.getAll(); }
        });
    }
}
