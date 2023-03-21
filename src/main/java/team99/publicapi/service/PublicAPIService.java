package team99.publicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team99.publicapi.domain.Listing;
import team99.publicapi.dto.MultipleListingResponse;
import team99.publicapi.dto.MultipleUserResponse;
import team99.publicapi.dto.PublicAPIServiceConstants;
import team99.publicapi.repository.PublicAPIRepository;
import team99.publicapi.utility.PublicAPIUtility;


import java.util.HashMap;
import java.util.List;

@Service
public class PublicAPIService {

    @Autowired
    private PublicAPIRepository publicAPIRepository;

    public MultipleListingResponse getAllListings(Integer pageNum, Integer pageSize, Integer userId)
    {
        return publicAPIRepository.getAllListings( pageNum,  pageSize,  userId);
    }

    public void getUsers()
    {
        publicAPIRepository.getUsers();
    }

}
