import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { HttpClient } from '@angular/common/http';
import { ProjectDTO } from 'src/app/projects/project.model'; // Assuming you have a ProjectDTO model

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  displayedColumns: string[] = ['projectId', 'projectName', 'status', 'startDate'];
  dataSource!: MatTableDataSource<ProjectDTO>;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<ProjectDTO[]>('http://localhost:8080/api/projects').subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    }, error => {
      console.error('Error fetching users:', error); // Handle errors appropriately
    });
  }
}
