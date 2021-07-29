import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Servicios.
import { GuardService } from 'src/app/core/services/guard.service';

// Componentes.
import { PatientListComponent } from './components/patient/patient.list.component';
import { ConsultationListComponent } from './components/consultation/consultation.list.component';
import { DoctorListComponent } from './components/doctor/doctor.list.component';

const routes: Routes = [
  {
    path: '',
    children: [
      { path: 'patient', component: PatientListComponent, canActivate: [GuardService] },
      { path: 'doctor', component: DoctorListComponent, canActivate: [GuardService] },
      { path: 'consultation', component: ConsultationListComponent, canActivate: [GuardService] },
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class DoctorOfficeRoute {
}
