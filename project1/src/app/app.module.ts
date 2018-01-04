import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {FormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { NewformComponent } from './newform/newform.component';
import { YourformsComponent } from './yourforms/yourforms.component';
import { ManformsComponent } from './manforms/manforms.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    NewformComponent,
    YourformsComponent,
    ManformsComponent
  ],
  imports: [
    BrowserModule,
	FormsModule,
    RouterModule.forRoot([
      {path: '', component: LoginComponent},
      {path: 'YourRequests', component: YourformsComponent},
      {path: 'ManRequests', component: ManformsComponent},
      {path: 'NewForm', component: NewformComponent}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
