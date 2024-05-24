import { Component } from '@angular/core';
import { SearchtrainsService } from '../../services/searchtrains.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent {
constructor(private searchtrainsService: SearchtrainsService){}
getAllTrains() {
  this.searchtrainsService.getAllTrains().subscribe(
    (data)=>{
      
    }
  )

}

}
