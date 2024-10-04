import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { HttpClient } from '@angular/common/http';
import { ProjectDTO } from 'src/app/projects/project.model'; // Assuming you have a ProjectDTO model
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';


@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  displayedColumns: string[] = ['projectId', 'projectName', 'status', 'startDate'];
  dataSource!: MatTableDataSource<ProjectDTO>;

  constructor(private http: HttpClient, private dialog: MatDialog, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.http.get<ProjectDTO[]>('http://localhost:8080/api/projects').subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    }, error => {
      console.error('Error fetching users:', error); // Handle errors appropriately
    });
  }
  onDelete(element: ProjectDTO) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: { entityName: 'Project' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.http.delete(`http://localhost:8080/api/projects/${element.projectId}`).subscribe({
          next: () => {
            this.snackBar.open('Project deleted successfully', 'Close', { duration: 3000 });
            this.dataSource.data = this.dataSource.data.filter(project => project.projectId !== element.projectId);
          },
          error: () => {
            this.snackBar.open('Error deleting project', 'Close', { duration: 3000 });
          }
        });
      }
    });
  }
}
