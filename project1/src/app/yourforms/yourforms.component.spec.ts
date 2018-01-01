import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { YourformsComponent } from './yourforms.component';

describe('YourformsComponent', () => {
  let component: YourformsComponent;
  let fixture: ComponentFixture<YourformsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ YourformsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(YourformsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
