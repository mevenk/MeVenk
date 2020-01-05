import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RunningClockComponent } from './running-clock.component';

describe('RunningClockComponent', () => {
  let component: RunningClockComponent;
  let fixture: ComponentFixture<RunningClockComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RunningClockComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RunningClockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
