import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EtfscreenerComponent } from './etfscreener.component';

describe('EtfscreenerComponent', () => {
  let component: EtfscreenerComponent;
  let fixture: ComponentFixture<EtfscreenerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EtfscreenerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EtfscreenerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
