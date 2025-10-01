/*
 *
 * * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {


  /**
   * Get all the restaurants that are open now within a specific service radius.
   * - For peak hours: 8AM - 10AM, 1PM-2PM, 7PM-9PM
   * - service radius is 3KMs.
   * - All other times, serving radius is 5KMs.
   * - If there are no restaurants, return empty list of restaurants.
   * @param getRestaurantsRequest valid lat/long
   * @param currentTime current time.
   * @return GetRestaurantsResponse object containing a list of open restaurants or an
   *     empty list if none fits the criteria.
   */

  private RestaurantService restaurantService;

  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;
  
  @Autowired
  private RestaurantRepositoryService restaurantRepositoryService;

  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
    // TODO Auto-generated method stub

    Double latitude=getRestaurantsRequest.getLatitude();
    Double longitude=getRestaurantsRequest.getLongitude();
    Double servingRadiusInKms;

    if(isPeakHour(currentTime)){
      servingRadiusInKms=peakHoursServingRadiusInKms;
    }
    else{
      servingRadiusInKms=normalHoursServingRadiusInKms;
    }

    List<Restaurant> restaurants =restaurantRepositoryService.findAllRestaurantsCloseBy(latitude, longitude, currentTime, servingRadiusInKms);
    
    // GetRestaurantsResponse getRestaurantsResponse =  ;

    return new GetRestaurantsResponse(restaurants);
  }

  // TODO: CRIO_TASK_MODULE_RESTAURANTSAPI - Implement findAllRestaurantsCloseby.
  // Check RestaurantService.java file for the interface contract.
  
  public Boolean isPeakHour(LocalTime t){
    LocalTime t8=LocalTime.of(8,0);
    LocalTime t10=LocalTime.of(10,0);
    LocalTime t13=LocalTime.of(13,0);
    LocalTime t14=LocalTime.of(14,0);
    LocalTime t19=LocalTime.of(19,0);
    LocalTime t21=LocalTime.of(21,0);

    return (t.isAfter(t8) && t.isBefore(t10)) || (t.isAfter(t13) && t.isBefore(t14)) || (t.isAfter(t19) && t.isBefore(t21)) || t.equals(t8) || t.equals(t10) || t.equals(t13) || t.equals(t14) || t.equals(t19) || t.equals(t21);
  }

}

