import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

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

  constructor() {}

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  onEdit(element: T) {
    // Implement edit logic here
  }

  onDelete(element: T) {
    // Implement delete logic here
  }
}
