import { Component, OnInit } from '@angular/core';
import {SearchTop5SkillsService} from '../search-top5-skills.service';
@Component({
  selector: 'app-most-searched-skills',
  templateUrl: './most-searched-skills.component.html',
  styleUrls: ['./most-searched-skills.component.css']
})
export class MostSearchedSkillsComponent implements OnInit {
  skillArray: any = [];
  skill:any;
  error:string;
  chartData=[{ data: [], label: 'Total Question' },];
  BarColors = [{backgroundColor:  [
        '#66ffff',
        '#66ffff',
        '#66ffff',
        '#66ffff',
      ]
    }
  ];

  chartOptions = {
    responsive: true,
    chartLabels:{
      fontSize: 40
    }
  };

  chartLabels = [];
 
  constructor(private searchTop5SkillsService: SearchTop5SkillsService ) { }

  ngOnInit() {
    console.log("inside ngOnInit");

    this.searchTop5SkillsService.getSkills().subscribe(
      data=>{
        console.log("inside getskills()");
        console.log(data);
        let i:any;
        for(i=0;i<data.length;i++){
          console.log(data[i][0]);
          this.chartLabels[i]=data[i][0];
          this.chartData[0].data[i]=data[i][1];
         
        }
        console.log(this.chartLabels);
        console.log(this.chartData);
       
      },
      error => {
        console.log("inside error");
        this.error=error;
        
        console.log(error);
      } )
  }
  
  onChartClick(event) {
    console.log(event);
  }
  donutColors = [
    {
      backgroundColor: [
        '#ced',
        '#ced',
        '#ced',
        '#ced',
      ]
    }
  ];

  

}
