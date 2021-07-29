import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

// Models.
import { environment } from '../../../../environments/environment';
import { ConsultationList } from '../models/consultation.list.model';
import { Response } from 'src/app/core/models/response.model';
import { Consultation } from '../models/consultation.model';
import { ConsultationHistory } from '../models/consultation.history.model';
import { ConsultationPatient } from '../models/consultation.patient.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable()
export class ConsultationService {

  constructor(private http: HttpClient) { }

  get(id: number): Observable<Response> {
    return this.http.get<Response>(environment.API_CONSULTATION + id);
  }

  full(): Observable<ConsultationList[]> {
    return this.http.get<ConsultationList[]>(environment.API_CONSULTATION + 'full');
  }

  post(obj: Consultation): Observable<Response> {
    return this.http.post<Response>(environment.API_CONSULTATION, obj, httpOptions);
  }

  put(obj: Consultation): Observable<Response> {
    return this.http.put<Response>(environment.API_CONSULTATION, obj, httpOptions);
  }

  delete(id: number): Observable<Response> {
    return this.http.delete<Response>(environment.API_CONSULTATION + id, httpOptions);
  }

  history(patientId: number): Observable<ConsultationHistory[]> {
    return this.http.get<ConsultationHistory[]>(environment.API_CONSULTATION + 'history/' + patientId);
  }

  patientCare(doctorId: number): Observable<ConsultationPatient[]> {
    return this.http.get<ConsultationPatient[]>(environment.API_CONSULTATION + 'patient/' + doctorId);
  }
}
