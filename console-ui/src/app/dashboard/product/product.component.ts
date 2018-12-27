import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  p:any;
  _totalElements:any;
  productForm: FormGroup;
  _clients: any = [];
  constructor() { }

  ngOnInit() {
    this.createProductForm();

    this._clients = [
      'test','test1','test2'
    ]
  }
  createProductForm() {
    this.productForm = new FormGroup({
      'pCode': new FormControl('', [Validators.required, Validators.minLength(3)]),
      'pType': new FormControl('', [Validators.required, Validators.minLength(3)]),
      'pDesc': new FormControl('', []),
      'pPrice': new FormControl('', [Validators.required]),
    });
  }

  saveProduct() {
    if (this.productForm.invalid) {
      for (let control in this.productForm.controls) {
        this.productForm.get(control).markAsTouched();
        this.productForm.get(control).invalid;
        this.productForm.get(control).updateValueAndValidity();
      }
      console.log("form not valid")
      return;
    } else {
      console.log(this.productForm)
      alert("form submitted")
    }

  }

  get pCode() { return this.productForm.get('pCode'); }
  get pType() { return this.productForm.get('pType'); }
  get pDesc() { return this.productForm.get('pDesc'); }
  get pPrice() { return this.productForm.get('pPrice'); }
}
