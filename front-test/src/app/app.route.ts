import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Components.
import { PageNotFoundComponent } from './modules/main/components/page-notfound/page.notfound.component';
import { LoginComponent } from './modules/main/components/login/login.component';

const routes: Routes = [
    { path: 'doctoroffice', loadChildren: () => import('./modules/main/main.module').then(m => m.PrincipalModule) },
    {
        path: 'login',
        component: LoginComponent,
    },
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: '**', component: PageNotFoundComponent },
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoute {
}
