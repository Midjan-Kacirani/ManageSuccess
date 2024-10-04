import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersComponent } from './users/users.component'; // Adjust the path based on your structure
import { ProjectsComponent } from './projects/projects.component';


const routes: Routes = [
  { path: 'users', component: UsersComponent },
  { path: 'projects', component: ProjectsComponent }
    // Add other routes here
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
