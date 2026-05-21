export interface UserProfileRequest {
  firstName: string;
  lastName: string;
  city: string;
  phone: string;
  birthDate: string; // ISO string: "2000-12-31"
  avatarUrl?: string;
}