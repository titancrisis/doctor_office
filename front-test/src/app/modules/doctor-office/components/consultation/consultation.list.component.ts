import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatTableDataSource, MatDialog, MatPaginator } from '@angular/material';
import { TransactionEnum } from 'src/app/core/enums/transaction.enum';

// Services.
import { ConsultationService } from '../../services/consultation.service';
import { NotifyService } from 'src/app/core/services/notify.service';

// Models.
import { ConsultationList } from '../../models/consultation.list.model';

// Components.
import { ConsultationComponent } from './consultation.component';

@Component({
    selector: 'app-consultation-list-component',
    templateUrl: 'consultation.list.component.html',
    styleUrls: ['../../../../styles/styles.scss']
})
export class ConsultationListComponent implements OnInit {

    // Properties.
    public dataSource;
    public headerDef: string[] = ['patient', 'doctor', 'createdAt', 'prescriptionDrugs', 'description', 'update', 'delete'];
    @ViewChild(MatSort, { static: true }) sort: MatSort;
    @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

    constructor(
        public dialog: MatDialog,
        private consultationService: ConsultationService,
        private notifyService: NotifyService) {
    }

    ngOnInit(): void {
        this.getAll();
    }

    getAll(): void {
        this.consultationService.full().subscribe(obj => {
            this.dataSource = new MatTableDataSource<ConsultationList>(obj);
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
        const dialogRef = this.dialog.open(ConsultationComponent, {
            width: '400px',
            data: { transaction: TransactionEnum.create, title: 'AGREGAR CONSULTA', id: null }
        });
        dialogRef.afterClosed().subscribe((result: boolean) => {
            if (result) { this.getAll(); }
        });
    }

    update(id: number): void {
        const dialogRef = this.dialog.open(ConsultationComponent, {
            width: '400px',
            data: { transaction: TransactionEnum.update, title: 'MODIFICAR CONSULTA', id: id }
        });
        dialogRef.afterClosed().subscribe((result: boolean) => {
            if (result) { this.getAll(); }
        });
    }

    delete(id: number): void {
        const dialogRef = this.dialog.open(ConsultationComponent, {
            width: '400px',
            data: { transaction: TransactionEnum.delete, title: 'ELIMINAR CONSULTA', id: id }
        });
        dialogRef.afterClosed().subscribe((result: boolean) => {
            if (result) { this.getAll(); }
        });
    }
}
