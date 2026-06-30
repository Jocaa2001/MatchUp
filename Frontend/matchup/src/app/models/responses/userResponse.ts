import { UserProfileResponse } from './userProfileResponse';



export interface UserResponse {
  id: number;
  profile: UserProfileResponse;
  email: string;
  role: string;
}