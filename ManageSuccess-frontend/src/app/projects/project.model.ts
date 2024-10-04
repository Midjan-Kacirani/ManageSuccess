import { UserDTO } from 'src/app/users/users.model';

export interface ProjectDTO {
    projectId: string; // Read-only
    projectName: string;
    description: string;
    status: Status; // Enum representing the project status
    startDate: Date;
    endDate?: Date;
    teamId?: string; // Write-only
    team?: TeamDTO; // Read-only
    createdById?: string; // Write-only
    createdBy?: UserDTO; // Read-only
  }
  
  // Status enum (assuming it is an enum)
  export enum Status {
    ACTIVE = 'ACTIVE',
    COMPLETED = 'COMPLETED',
    ON_HOLD = 'ON_HOLD'
  }
  
  export interface TeamDTO {
    teamId: string;
    teamName: string;
    members: UserDTO[]; // Array of UserDTO
  }
  