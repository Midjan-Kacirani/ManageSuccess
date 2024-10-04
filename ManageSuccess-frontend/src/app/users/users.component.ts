import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from 'src/app/users/users.model'; // Assuming you have a UserDTO model

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  displayedColumns: string[] = ['userId', 'firstName', 'lastName', 'email'];
  dataSource!: MatTableDataSource<UserDTO>;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<UserDTO[]>('http://localhost:8080/api/users').subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    }, error => {
      console.error('Error fetching users:', error); // Handle errors appropriately
    });
  }
}
