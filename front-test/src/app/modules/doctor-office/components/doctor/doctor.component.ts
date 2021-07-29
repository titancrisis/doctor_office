import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TransactionEnum } from 'src/app/core/enums/transaction.enum';

// Services.
import { DoctorService } from '../../services/doctor.service';
import { NotifyService } from 'src/app/core/services/notify.service';

// Models.
import { Doctor } from '../../models/doctor.model';

@Component({
  selector: 'app-doctor-component',
  templateUrl: 'doctor.component.html',
  styleUrls: ['../../../../styles/styles.scss']
})
export class DoctorComponent implements OnInit {

  // Properties.
  public form: FormGroup;
  public textButton: string;
  public image: any;
  public transaction: TransactionEnum;
  private id: number;

  constructor(
    private fb: FormBuilder,
    private doctorService: DoctorService,
    private notifyService: NotifyService,
    public dialogRef: MatDialogRef<DoctorComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {

    this.transaction = data.transaction;
    this.id = data.id;
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      id: [''],
      name: ['', Validators.required],
      lastName: ['', Validators.required],
      specialty: ['', Validators.required],
      birthDate: ['', Validators.required],
      address: [''],
      image: [''],
    });

    switch (this.transaction) {
      case TransactionEnum.create:
        this.form.enable();
        this.textButton = 'GUARDAR';
        break;
      case TransactionEnum.update:
      case TransactionEnum.delete:
        this.doctorService.get(this.id).subscribe(response => {
          if (response.result) {
            const obj: Doctor = response.body;

            this.form.controls.id.setValue(obj.id);
            this.form.controls.name.setValue(obj.name);
            this.form.controls.lastName.setValue(obj.lastName);
            this.form.controls.specialty.setValue(obj.specialty);
            this.form.controls.birthDate.setValue(obj.birthDate);
            this.form.controls.address.setValue(obj.address);
            this.image = 'data:image/jpg;base64,' + obj.image;

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
  }

  close(): void {
    this.dialogRef.close(false);
  }

  saveChanges(form: FormGroup) {

    const obj = new Doctor();
    obj.id = this.id;
    obj.name = form.value.name;
    obj.lastName = form.value.lastName;
    obj.birthDate = form.value.birthDate;
    obj.specialty = form.value.specialty;
    obj.address = form.value.address;
    obj.image = this.image ? this.image.split(',')[1] : null;

    switch (this.transaction) {
      case TransactionEnum.create:
        this.doctorService.post(obj)
          .subscribe(response => {
            if (response.result) {
              this.notifyService.success('El doctor se agregó.');
              this.dialogRef.close(true);
            } else {
              this.notifyService.error(response.message);
            }
          }, error => {
            this.notifyService.error(error.message);
          });
        break;
      case TransactionEnum.update:
        this.doctorService.put(obj)
          .subscribe(response => {
            if (response.result) {
              this.notifyService.success('El doctor se modificó.');
              this.dialogRef.close(true);
            } else {
              this.notifyService.error(response.message);
            }
          }, error => {
            this.notifyService.error(error.message);
          });
        break;
      case TransactionEnum.delete:
        this.doctorService.delete(this.id)
          .subscribe(response => {
            if (response.result) {
              this.notifyService.success('El doctor se eliminó.');
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

  // Method for load image.
  loadImage($event): void {
    var file: File = $event.target.files[0];
    if (file !== undefined) {
      var reader: FileReader = new FileReader();
      reader.onloadend = (e) => {
        this.image = reader.result;
      }
      reader.readAsDataURL(file);
    }
    else {
      this.image = null;
    }
  }
}
