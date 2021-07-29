import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppMaterialModule } from '../../app.material.module';
import { DirectiveModule } from 'src/app/core/directives/directive.module';

// Routes.
import { DoctorOfficeRoute } from './doctor.office.route';

// Services.
import { GuardService } from 'src/app/core/services/guard.service';
import { PatientService } from './services/patient.service';
import { DoctorService } from './services/doctor.service';
import { ConsultationService } from './services/consultation.service';

// Components.
import { PatientListComponent } from './components/patient/patient.list.component';
import { PatientComponent } from './components/patient/patient.component';
import { DoctorListComponent } from './components/doctor/doctor.list.component';
import { DoctorComponent } from './components/doctor/doctor.component';
import { ConsultationListComponent } from './components/consultation/consultation.list.component';
import { ConsultationComponent } from './components/consultation/consultation.component';
import { HistoryComponent } from './components/patient/history.component';
import { DoctorPatientComponent } from './components/doctor/doctor.patient.component';

@NgModule({
    declarations: [
        PatientListComponent,
        PatientComponent,
        DoctorListComponent,
        DoctorComponent,
        ConsultationListComponent,
        ConsultationComponent,
        HistoryComponent,
        DoctorPatientComponent
    ],
    imports: [
        DirectiveModule,
        CommonModule,
        DoctorOfficeRoute,
        FormsModule,
        ReactiveFormsModule,
        AppMaterialModule
    ],
    entryComponents: [
        PatientComponent,
        DoctorComponent,
        ConsultationComponent,
        HistoryComponent,
        DoctorPatientComponent
    ],
    providers: [
        GuardService,
        PatientService,
        DoctorService,
        ConsultationService
    ],
    exports: []
})
export class DoctorOfficeModule {
}
