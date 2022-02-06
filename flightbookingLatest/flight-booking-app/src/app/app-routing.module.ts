import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddnewflightComponent } from './components/admin/addnewflight/addnewflight.component';
import { ModifyexistingflightComponent } from './components/admin/modifyexistingflight/modifyexistingflight.component';
import { BookingComponent } from './components/booking/booking.component';
import { BookinghistoryComponent } from './components/bookinghistory/bookinghistory.component';
import { FlightsearchComponent } from './components/flightsearch/flightsearch.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegistrationComponent } from './components/registration/registration.component';

const routes: Routes = [
  {path:'', component:LoginComponent,pathMatch:'full'},
    {path:'registration', component:RegistrationComponent, pathMatch:'full'},
    {path:'login', component:LoginComponent,pathMatch:'full'},
    // {path: 'admin', component: AdminComponent },
    {path: 'userbooking', component:BookingComponent},
    {path: 'admin/addflight', component:AddnewflightComponent},
    {path: 'admin/modifyflight', component:ModifyexistingflightComponent},
    {path: 'booking/:id1', component:BookingComponent},
    {path: 'history' , component:BookinghistoryComponent},
    {path: 'logout' , component:LogoutComponent},
    {path: 'search' , component:FlightsearchComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
