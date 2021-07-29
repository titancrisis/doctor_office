import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

// Models.
import { environment } from '../../../../environments/environment';
import { PatientList } from '../models/patient.list.model';
import { Response } from 'src/app/core/models/response.model';
import { Patient } from '../models/patient.model';
import { PatientSingle } from '../models/patient.single.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable()
export class PatientService {

  constructor(private http: HttpClient) { }

  get(id: number): Observable<Response> {
    return this.http.get<Response>(environment.API_PATIENT + id);
  }

  all(): Observable<PatientList[]> {
    return this.http.get<PatientList[]>(environment.API_PATIENT);
  }

  allSingle(): Observable<PatientSingle[]> {
    return this.http.get<PatientSingle[]>(environment.API_PATIENT + 'single');
  }

  post(obj: Patient): Observable<Response> {
    return this.http.post<Response>(environment.API_PATIENT, obj, httpOptions);
  }

  put(obj: Patient): Observable<Response> {
    return this.http.put<Response>(environment.API_PATIENT, obj, httpOptions);
  }

  delete(id: number): Observable<Response> {
    return this.http.delete<Response>(environment.API_PATIENT + id, httpOptions);
  }
}
