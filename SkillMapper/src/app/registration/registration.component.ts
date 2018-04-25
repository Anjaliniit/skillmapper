import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../register.service';
import { Employee } from './registration';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  employee: any ={};

  constructor(private registerService: RegisterService,private router:Router) { }

  ngOnInit() {
  }

  register():void{
 console.log(this.employee);
 this.registerService.register(this.employee).subscribe(

data=>{
  console.log("Inserted");
  this.router.navigate(['login']);
}
);
this.employee;
  }
}



  
 /* add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.registerService.addHero({ name } as Hero)
      .subscribe(hero => {
        this.heroes.push(hero);
      });
  }*/


