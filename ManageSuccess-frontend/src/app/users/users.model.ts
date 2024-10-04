export interface UserDTO {
    userId: string; // Read-only
    firstName: string;
    lastName: string;
    username: string;
    userRole: UserRole; // Enum representing the user role
    email: string;
    password?: string; // Write-only
    profilePictureBinaryData?: Uint8Array;
    companyId?: string; // Write-only
    company?: CompanyDTO; // Read-only
  }
  
  // UserRole enum (assuming it is an enum)
  export enum UserRole {
    ADMIN = 'ADMIN',
    MANAGER = 'MANAGER',
    DEVELOPER = 'DEVELOPER'
  }
  
  export interface CompanyDTO {
    companyId: string;
    name: string;
    description: string;
    type: string;
    employeesNo: number;
    website: string;
    companyOwner: string;
  }
  