import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatTableDataSource, MatDialog, MatPaginator } from '@angular/material';
import { TransactionEnum } from 'src/app/core/enums/transaction.enum';

// Services.
import { DoctorService } from '../../services/doctor.service';
import { NotifyService } from 'src/app/core/services/notify.service';

// Models.
import { DoctorList } from '../../models/doctor.list.model';

// Components.
import { DoctorComponent } from './doctor.component';
import { DoctorPatientComponent } from './doctor.patient.component';

@Component({
    selector: 'app-doctor-list-component',
    templateUrl: 'doctor.list.component.html',
    styleUrls: ['../../../../styles/styles.scss']
})
export class DoctorListComponent implements OnInit {

    // Properties.
    public dataSource;
    public headerDef: string[] = ['name', 'lastName', 'birthDate', 'specialty', 'address', 'patientCare', 'update', 'delete'];
    @ViewChild(MatSort, { static: true }) sort: MatSort;
    @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

    constructor(
        public dialog: MatDialog,
        private doctorService: DoctorService,
        private notifyService: NotifyService) {
    }

    ngOnInit(): void {
        this.getAll();
    }

    getAll(): void {
        this.doctorService.all().subscribe(obj => {
            this.dataSource = new MatTableDataSource<DoctorList>(obj);
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
        const dialogRef = this.dialog.open(DoctorComponent, {
            width: '400px',
            data: { transaction: TransactionEnum.create, title: 'AGREGAR DOCTOR', id: null }
        });
        dialogRef.afterClosed().subscribe((result: boolean) => {
            if (result) { this.getAll(); }
        });
    }

    update(id: number): void {
        const dialogRef = this.dialog.open(DoctorComponent, {
            width: '400px',
            data: { transaction: TransactionEnum.update, title: 'MODIFICAR DOCTOR', id: id }
        });
        dialogRef.afterClosed().subscribe((result: boolean) => {
            if (result) { this.getAll(); }
        });
    }

    delete(id: number): void {
        const dialogRef = this.dialog.open(DoctorComponent, {
            width: '400px',
            data: { transaction: TransactionEnum.delete, title: 'ELIMINAR DOCTOR', id: id }
        });
        dialogRef.afterClosed().subscribe((result: boolean) => {
            if (result) { this.getAll(); }
        });
    }

    patientCare(id: number): void {
        const dialogRef = this.dialog.open(DoctorPatientComponent, {
            width: '750px',
            data: { id: id }
        });
        dialogRef.afterClosed().subscribe((result: boolean) => {
            if (result) { this.getAll(); }
        });
    }
}
