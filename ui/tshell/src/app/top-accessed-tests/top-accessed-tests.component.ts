import { Component, OnInit } from '@angular/core';
import { TopAccessedTestsService } from '../top-accessed-tests.service';

@Component({
  selector: 'app-top-accessed-tests',
  templateUrl: './top-accessed-tests.component.html',
  styleUrls: ['./top-accessed-tests.component.css']
})
export class TopAccessedTestsComponent implements OnInit {

  BarColors = [
    {
      backgroundColor: [
        '#99ff99',
        '#99ff99',
        '#99ff99',
        '#99ff99',
        '#99ff99',
      ]
    }
  ];

  chartOptions = {
    responsive: true
  };

  chartData = [{ data: [], label: 'Skills' }];
  chartLabels=[];
  error=false;

  constructor(private topAccessedTestService: TopAccessedTestsService) { }

  ngOnInit() {

    this.topAccessedTestService.getTestDetails().subscribe(
      data => {
        console.log(data);
        data.forEach((skillsarray, index )=> {
          console.log(skillsarray);
          for (var skill in skillsarray) {
            if (skill == '0') {
              this.chartLabels[index]=skillsarray[skill];
            }
            else{
              this.chartData[0].data[index]=skillsarray[1]
            }
          }
        }
        );
      }
    );
    console.log(this.chartLabels);
  }

}
