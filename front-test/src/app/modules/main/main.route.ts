import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Components.
import { ContainerComponent } from './components/container/container.component';
import { PageNotFoundComponent } from './components/page-notfound/page.notfound.component';

const routes: Routes = [
    {
        path: '',
        component: ContainerComponent,
        children: [
            { path: '', loadChildren: () => import('../doctor-office/doctor.office.module').then(m => m.DoctorOfficeModule) },
        ],
    },
    { path: '**', component: PageNotFoundComponent }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class PrincipalRoute {
}
