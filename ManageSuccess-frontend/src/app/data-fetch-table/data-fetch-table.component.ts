import { Component, Input, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar'; // For showing alerts


@Component({
  selector: 'app-data-fetch-table',
  templateUrl: './data-fetch-table.component.html',
  styleUrls: ['./data-fetch-table.component.css']
})

export class DataFetchTableComponent<T> implements OnInit {

  @Input() displayedColumns: string[] = [];
  @Input() dataSource!: MatTableDataSource<T>;
  @Input() entityName: string = '';

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private dialog: MatDialog, private http: HttpClient, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  @Output() onDelete = new EventEmitter<any>(); // Emit delete event

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  onEdit(element: T) {
    // Implement edit logic here
  }

}
