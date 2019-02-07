import { Component, OnInit } from '@angular/core';
import { DeactivateActivateSkillService } from '../deactivate-activate-skill.service';

@Component({
  selector: 'app-search-skill',
  templateUrl: './search-skill.component.html',
  styleUrls: ['./search-skill.component.css']
})
export class SearchSkillComponent implements OnInit {
  list: any;
  id: any;
  name: any;
  active: any;
  constructor(private skillSearch: DeactivateActivateSkillService) { }

  ngOnInit() {
  }
  getSkill(name) {
    this.skillSearch.gettingSkill(name).subscribe(
      data => {
        this.list = data;
        console.log(data);
      }
    );
  }
  toggllingSkill(id, name) {
    this.skillSearch.toggleSkill(id).subscribe(
      data => {
        this.skillSearch.gettingSkill(name).subscribe(
          data => {
            this.list = data;
          }
        );
      }
    );
  }
}


