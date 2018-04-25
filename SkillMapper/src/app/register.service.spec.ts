import { TestBed, inject } from '@angular/core/testing';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RegisterService } from './register.service';

describe('RegisterService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RegisterService]
    });
  });

  it('should be created', inject([RegisterService], (service: RegisterService) => {
    expect(service).toBeTruthy();
  }));
});
