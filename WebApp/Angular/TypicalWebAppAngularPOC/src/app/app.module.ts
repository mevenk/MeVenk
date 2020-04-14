import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InitialViewComponent } from './component/initial-view/initial-view.component';
import { FormattedStringPipe } from './pipe/format/formatted-string.pipe';

@NgModule({
  declarations: [
    AppComponent,
    InitialViewComponent,
    FormattedStringPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
