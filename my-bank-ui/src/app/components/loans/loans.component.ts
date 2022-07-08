import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { DashboardService } from '../../services/dashboard/dashboard.service';

@Component({
  selector: 'app-loans',
  templateUrl: './loans.component.html',
  styleUrls: ['./loans.component.css']
})
export class LoansComponent implements OnInit {

  user = new User();
  loans = new Array();
  currOutstandingBalance:Number = 0;

  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
    if(this.user){
      this.dashboardService.getLoansDetails(this.user).subscribe(
        responseData => {
          const self = this;
        this.loans = <any> responseData.body;
        this.loans.forEach(function (loan) {
          self.currOutstandingBalance = self.currOutstandingBalance+loan.outstandingAmount;
        }.bind(this)); 
        }, error => {
          console.log(error);
        });
    }
  }

  

}
