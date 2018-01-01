import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManformsComponent } from './manforms.component';

describe('ManformsComponent', () => {
  let component: ManformsComponent;
  let fixture: ComponentFixture<ManformsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManformsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManformsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
