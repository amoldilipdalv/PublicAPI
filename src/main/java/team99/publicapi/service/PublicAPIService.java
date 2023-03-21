package team99.publicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team99.publicapi.domain.Listing;
import team99.publicapi.domain.User;
import team99.publicapi.dto.*;
import team99.publicapi.repository.PublicAPIRepository;
import team99.publicapi.utility.PublicAPIUtility;


import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

@Service
public class PublicAPIService {

    @Autowired
    private PublicAPIRepository publicAPIRepository;

    @Autowired
    private SingleUserResponse singleUserResponse;

    @Autowired
    private SingleListingResponse singleListingResponse;

    public MultipleListingResponse getAllListings(Integer pageNum, Integer pageSize, String userId)
    {
        return publicAPIRepository.getAllListings( pageNum,  pageSize,  userId);
    }

    public void getUsers()
    {
        publicAPIRepository.getUsers();
    }

    public SingleUserResponse saveUser(UserInputJson userInputJson) throws URISyntaxException {
        User user = publicAPIRepository.saveUser(userInputJson);
        singleUserResponse.setUser(user);

        return singleUserResponse;
    }

    public SingleListingResponse saveListing(ListingInputJson listingInputJson) throws URISyntaxException {
        Listing listing = publicAPIRepository.saveListing(listingInputJson);
        singleListingResponse.setListing(listing);

        return singleListingResponse;
    }
}
