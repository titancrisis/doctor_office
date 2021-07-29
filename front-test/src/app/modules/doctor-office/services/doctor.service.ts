import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

// Models.
import { environment } from '../../../../environments/environment';
import { DoctorList } from '../models/doctor.list.model';
import { Response } from 'src/app/core/models/response.model';
import { Doctor } from '../models/doctor.model';
import { DoctorSingle } from '../models/doctor.single.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable()
export class DoctorService {

  constructor(private http: HttpClient) { }

  get(id: number): Observable<Response> {
    return this.http.get<Response>(environment.API_DOCTOR + id);
  }

  all(): Observable<DoctorList[]> {
    return this.http.get<DoctorList[]>(environment.API_DOCTOR);
  }

  allSingle(): Observable<DoctorSingle[]> {
    return this.http.get<DoctorSingle[]>(environment.API_DOCTOR + 'single');
  }

  post(obj: Doctor): Observable<Response> {
    return this.http.post<Response>(environment.API_DOCTOR, obj, httpOptions);
  }

  put(obj: Doctor): Observable<Response> {
    return this.http.put<Response>(environment.API_DOCTOR, obj, httpOptions);
  }

  delete(id: number): Observable<Response> {
    return this.http.delete<Response>(environment.API_DOCTOR + id, httpOptions);
  }
}
