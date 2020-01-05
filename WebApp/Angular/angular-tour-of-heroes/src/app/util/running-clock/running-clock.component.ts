import { Component, OnInit } from '@angular/core';
import { RunningClock } from './running-clock';

@Component({
  selector: 'running-clock',
  templateUrl: './running-clock.component.html',
  styleUrls: ['./running-clock.component.css']
})
export class RunningClockComponent implements OnInit {

  runningClock: RunningClock;

  constructor() { }

  ngOnInit() {
    this.startRunningClock();
  }

  private startRunningClock() {
    var now = new Date();
    this.runningClock = new RunningClock(this.formatTime(now.getHours()), this.formatTime(now.getMinutes()), this.formatTime(now.getSeconds()), now.getMilliseconds());
    setInterval(() => { this.startRunningClock() }, 1000);
  }

  formatTime(i) {
    if (i < 10) {
      i = "0" + i
    }
    return i;
  }

}
