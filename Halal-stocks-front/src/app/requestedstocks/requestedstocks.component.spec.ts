import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestedstocksComponent } from './requestedstocks.component';

describe('RequestedstocksComponent', () => {
  let component: RequestedstocksComponent;
  let fixture: ComponentFixture<RequestedstocksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RequestedstocksComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RequestedstocksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
