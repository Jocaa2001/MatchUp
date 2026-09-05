import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DistinctCityResponse } from '../models/responses/DistinctCityResponse';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class LocationService {

private http = inject(HttpClient);

getDistinctCities(): Observable<DistinctCityResponse[]> {
  return this.http.get<DistinctCityResponse[]>(
    'http://localhost:8080/api/locations/cities'
  );
}

}
