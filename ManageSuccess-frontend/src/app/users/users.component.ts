import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from 'src/app/users/users.model'; // Assuming you have a UserDTO model
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component'; // Import dialog


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  displayedColumns: string[] = ['userId', 'firstName', 'lastName', 'email'];
  dataSource!: MatTableDataSource<UserDTO>;

  constructor(private http: HttpClient, private dialog: MatDialog, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.http.get<UserDTO[]>('http://localhost:8080/api/users').subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    }, error => {
      console.error('Error fetching users:', error); // Handle errors appropriately
    });
  }
  onDelete(element: UserDTO) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: { entityName: 'User' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.http.delete(`http://localhost:8080/api/users/${element.userId}`).subscribe({
          next: () => {
            this.snackBar.open('User deleted successfully', 'Close', { duration: 3000 });
            this.dataSource.data = this.dataSource.data.filter(user => user.userId !== element.userId);
          },
          error: () => {
            this.snackBar.open('Error deleting user', 'Close', { duration: 3000 });
          }
        });
      }
    });
  }
}
