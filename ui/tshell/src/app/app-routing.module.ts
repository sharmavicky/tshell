import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchSkillComponent } from './search-skill/search-skill.component';
import { SearchExistingQuestionsComponent } from './search-existing-questions/search-existing-questions.component';
import { PreviewQuestionsComponent } from './preview-questions/preview-questions.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MostSearchedSkillsComponent } from './most-searched-skills/most-searched-skills.component';

import { LearnerHomepageComponent } from './learner-homepage/learner-homepage.component';
import { SignupComponent } from './signup/signup.component';
import { AdminSignupComponent } from './admin-signup/admin-signup.component';
import { LoginComponent } from './login/login.component';
import { AdminHomepageComponent } from './admin-homepage/admin-homepage.component';
import { ChangepasswordComponent } from './changepassword/changepassword.component';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { AssessmenthistoryComponent } from './assessmenthistory/assessmenthistory.component';
import { RecentlyAddedSkillsComponent } from './recently-added-skills/recently-added-skills.component';
import { CountOfQuestionsToReviewComponent } from './count-of-questions-to-review/count-of-questions-to-review.component';
import { HomeComponent } from './home/home.component';
import { SearchResultComponent } from './search-result/search-result.component';
import { SkillpageComponent } from './skillpage/skillpage.component';
import { ExitAssesmentComponent } from './exit-assesment/exit-assesment.component';
import { InstructionComponent } from './instruction/instruction.component';
import { ScoreAssesmentComponent } from './score-assesment/score-assesment.component';

import { AddQuestionComponent } from './add-question/add-question.component';
import { QuestiongraphComponent } from './questiongraph/questiongraph.component';
import { TopAccessedTestsComponent } from './top-accessed-tests/top-accessed-tests.component';
import { ViewprofileComponent } from './viewprofile/viewprofile.component';



const routes: Routes = [

  { path: 'search-skill', component: SearchSkillComponent },
  { path: 'reviewq/:id/:name', component: SearchExistingQuestionsComponent },
  { path: 'preview', component: PreviewQuestionsComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'preview', component: PreviewQuestionsComponent },
  { path: "", component: HomeComponent },
  { path: "login", component: LoginComponent },
  { path: "authenticate", component: AdminSignupComponent },
  { path: "signup", component: SignupComponent },
  { path: "learner-homepage", component: LearnerHomepageComponent },
  { path: "recentSkills", component: RecentlyAddedSkillsComponent },
  { path: 'dash', component: DashboardComponent },
  { path: "admin-homepage", component: AdminHomepageComponent },
  { path: "changepassword", component: ChangepasswordComponent },
  { path: "userprofile", component: UserprofileComponent },
  { path: "assessmenthistory", component: AssessmenthistoryComponent },
  { path: "viewprofile", component: ViewprofileComponent },
  { path: "questions", component: CountOfQuestionsToReviewComponent },
  { path: 'assesment/:skillid/:skillname/:type', component: ExitAssesmentComponent },
  { path: 'instruction/:skillid/:skillname/:type', component: InstructionComponent },
  { path: 'assesmentscore/:skillname/:assessmentid', component: ScoreAssesmentComponent },
  { path: "skills", component: SearchResultComponent },
  { path: "skills/:name", component: SkillpageComponent },
  { path: "addquestion", component: AddQuestionComponent },
  { path: "graph", component: QuestiongraphComponent },
  { path: "top5Tests", component: TopAccessedTestsComponent },
  { path: "graph", component: QuestiongraphComponent }

];


@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }


