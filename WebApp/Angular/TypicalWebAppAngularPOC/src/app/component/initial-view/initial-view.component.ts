import { Title } from './../title';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'initial-view',
  templateUrl: './initial-view.component.html',
  styleUrls: ['./initial-view.component.css']
})
export class InitialViewComponent implements OnInit, Title {

  initialViewElements;

  constructor() {
    this.initialViewElements = ['One', 'Two', 'Three'];
  }

  ngOnInit() {
  }

  getTitle() {
    return 'Initial   View';
  }

}
