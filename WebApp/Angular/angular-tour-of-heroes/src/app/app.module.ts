import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS }    from '@angular/common/http';

import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService }  from './util/in-memory/in-memory-data.service';

import { NgxJsonViewerModule } from 'ngx-json-viewer';
import { AceEditorModule } from 'ng2-ace-editor';

import { AppComponent } from './app.component';
import { HeroesComponent } from './hero/heroes/heroes.component';
import { HeroDetailComponent } from './hero/hero-detail/hero-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { VarDirective } from './custom/directive/var.directive';
import { DashboardComponent } from './dashboard/dashboard.component';
import { RunningClockComponent } from './util/running-clock/running-clock.component';
import { HeroSearchComponent } from './hero/hero-search/hero-search.component';
import { FormattedStringPipe } from './custom/pipe/format/formatted-string.pipe';
import { ApplicationHTTPInterceptor } from './interceptor/http/application-httpinterceptor';
import { ApplicationHTTPInterceptorSecond } from './interceptor/http/application-httpinterceptor-second';

@NgModule({
  declarations: [
    AppComponent,
    HeroesComponent,
    HeroDetailComponent,
    MessagesComponent,
    VarDirective,
    DashboardComponent,
    RunningClockComponent,
    HeroSearchComponent,
    FormattedStringPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    // The HttpClientInMemoryWebApiModule module intercepts HTTP requests
    // and returns simulated server responses.
    // Remove it when a real server is ready to receive requests.
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, { dataEncapsulation: false }
    ),
    NgxJsonViewerModule,
    AceEditorModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: ApplicationHTTPInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ApplicationHTTPInterceptorSecond, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
