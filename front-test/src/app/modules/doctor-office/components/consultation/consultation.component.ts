import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TransactionEnum } from 'src/app/core/enums/transaction.enum';

// Services.
import { ConsultationService } from '../../services/consultation.service';
import { NotifyService } from 'src/app/core/services/notify.service';
import { DoctorService } from '../../services/doctor.service';
import { PatientService } from '../../services/patient.service';

// Models.
import { Consultation } from '../../models/consultation.model';
import { PatientSingle } from '../../models/patient.single.model';
import { DoctorSingle } from '../../models/doctor.single.model';

@Component({
  selector: 'app-consultation-component',
  templateUrl: 'consultation.component.html',
  styleUrls: ['../../../../styles/styles.scss']
})
export class ConsultationComponent implements OnInit {

  // Properties.
  public form: FormGroup;
  public textButton: string;
  public transaction: TransactionEnum;
  private id: number;
  public listPatient: PatientSingle[];
  public listDoctor: DoctorSingle[];

  constructor(
    private fb: FormBuilder,
    private consultationService: ConsultationService,
    private doctorService: DoctorService,
    private patientService: PatientService,
    private notifyService: NotifyService,
    public dialogRef: MatDialogRef<ConsultationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {

    this.transaction = data.transaction;
    this.id = data.id;
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      id: [''],
      patientId: ['', Validators.required],
      doctorId: ['', Validators.required],
      description: ['', Validators.required],
      prescriptionDrugs: ['', Validators.required]
    });

    switch (this.transaction) {
      case TransactionEnum.create:
        this.form.enable();
        this.textButton = 'GUARDAR';
        break;
      case TransactionEnum.update:
      case TransactionEnum.delete:
        this.consultationService.get(this.id).subscribe(response => {
          if (response.result) {
            const obj: Consultation = response.body;

            this.form.controls.id.setValue(obj.id);
            this.form.controls.patientId.setValue(obj.patientId);
            this.form.controls.doctorId.setValue(obj.doctorId);
            this.form.controls.description.setValue(obj.description);
            this.form.controls.prescriptionDrugs.setValue(obj.prescriptionDrugs);

            if (this.transaction === TransactionEnum.update) {
              this.form.enable();
              this.textButton = 'GUARDAR CAMBIOS';
            }
            else {
              this.form.disable();
              this.textButton = 'ELIMINAR';
            }
          }
        }, error => {
          this.notifyService.error(error.message);
        });
        break;
    }

    this.loadSelect();
  }

  close(): void {
    this.dialogRef.close(false);
  }


  loadSelect(): void {
    this.patientService.allSingle()
      .subscribe(data => {
        this.listPatient = data;
      }, error => {
        this.notifyService.error(error.message);
      });
    this.doctorService.allSingle()
      .subscribe(data => {
        this.listDoctor = data;
      }, error => {
        this.notifyService.error(error.message);
      });
  }

  saveChanges(form: FormGroup) {

    const obj = new Consultation();
    obj.id = this.id;
    obj.patientId = form.value.patientId;
    obj.doctorId = form.value.doctorId;
    obj.description = form.value.description;
    obj.prescriptionDrugs = form.value.prescriptionDrugs;

    switch (this.transaction) {
      case TransactionEnum.create:
        this.consultationService.post(obj)
          .subscribe(response => {
            if (response.result) {
              this.notifyService.success('La consulta se agregó.');
              this.dialogRef.close(true);
            } else {
              this.notifyService.error(response.message);
            }
          }, error => {
            this.notifyService.error(error.message);
          });
        break;
      case TransactionEnum.update:
        this.consultationService.put(obj)
          .subscribe(response => {
            if (response.result) {
              this.notifyService.success('La consulta se modificó.');
              this.dialogRef.close(true);
            } else {
              this.notifyService.error(response.message);
            }
          }, error => {
            this.notifyService.error(error.message);
          });
        break;
      case TransactionEnum.delete:
        this.consultationService.delete(this.id)
          .subscribe(response => {
            if (response.result) {
              this.notifyService.success('La consulta se eliminó.');
              this.dialogRef.close(true);
            } else {
              this.notifyService.error(response.message);
            }
          }, error => {
            this.notifyService.error(error.message);
          });
        break;
    }
  }
}
