import { NgModule } from '@angular/core';
import { Routes, RouterModule, ExtraOptions } from '@angular/router';
import { IbanFormComponent } from './iban-form/iban-form.component';
import { IbanListComponent } from './iban-list/iban-list.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { SingleIbanComponent } from './single-iban/single-iban.component';

const routes: Routes = [
  { path: '', component: SingleIbanComponent },
  { path: 'list', component: IbanListComponent },
  { path: "not-found", component: NotFoundComponent },
  { path: "**", redirectTo: "/not-found"}
]

const routerOptions: ExtraOptions = {
  useHash: false,
  anchorScrolling: 'enabled',
  scrollOffset: [0, 64]
};

@NgModule({
  imports: [RouterModule.forRoot(routes, routerOptions)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
