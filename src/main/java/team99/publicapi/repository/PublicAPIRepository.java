package team99.publicapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import team99.publicapi.domain.Listing;
import team99.publicapi.domain.User;
import team99.publicapi.dto.*;
import team99.publicapi.utility.PublicAPIUtility;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Component
public class PublicAPIRepository {

    @Autowired
    private PublicAPIUtility publicAPIUtility;

    @Value("${listings.service.get.listings.url}")
    private String getAllListingUrl;

    @Value("${listings.service.get.user_id.url}")
    private String getUserListingUrl;

    @Value("${users.service.get.url}")
    private String getUsersUrl;

     @Value("${listings.service.url}")
    private String listingServiceUrl;
     private Map<Integer, User> usersMap = new HashMap<>();

    public MultipleListingResponse getAllListings(Integer pageNum, Integer pageSize, Integer userId)
    {
        HashMap<String, Integer> listingsReqInput = new HashMap<>();
        listingsReqInput.put(PublicAPIServiceConstants.PAGE_NUM, pageNum);
        listingsReqInput.put(PublicAPIServiceConstants.PAGE_SIZE, pageSize);

        if(null != userId)
        {
            listingsReqInput.put("user_id", userId);
            getAllListingUrl = getUserListingUrl;
        }


        ResponseEntity<MultipleListingResponse> responseEntity = new RestTemplate()
                .getForEntity(getAllListingUrl, MultipleListingResponse.class, listingsReqInput);

        MultipleListingResponse multipleListingResponse =  responseEntity.getBody();
        List<Listing> listings = publicAPIUtility.sortListingDescOnCreatedAt(multipleListingResponse.getListings());
        multipleListingResponse.setListings(listings);

        MultipleListingResponse multipleListingWithUserResponse = injectUserObjectInListing(multipleListingResponse);
        return multipleListingWithUserResponse;
    }
    private MultipleListingResponse injectUserObjectInListing(MultipleListingResponse multipleListingResponse) {
        List<Listing> listings = multipleListingResponse.getListings();
        listings.forEach(listing ->{

            if(null != usersMap.get(Integer.parseInt(listing.getUser_id())))
                listing.setUser(usersMap.get(Integer.parseInt(listing.getUser_id())));
            else
            {
                //refresh the users list
                getUsers();
                listing.setUser(usersMap.get(Integer.parseInt(listing.getUser_id())));
            }
        });

        //Set the modified listing with relevant user object
        multipleListingResponse.setListings(listings);
        return multipleListingResponse;
    }



    public void getUsers()
    {
        ResponseEntity<MultipleUserResponse> responseEntity = new RestTemplate()
                .getForEntity(getUsersUrl, MultipleUserResponse.class);
        List<User> users = responseEntity.getBody().getUsers();
        users.forEach(user -> usersMap.put(user.getId(), user));
        System.out.println(users.size());

    }

    public User saveUser(UserInputJson userInputJson) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("name", userInputJson.getName());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<SingleUserResponse> responseEntity = new RestTemplate().postForEntity(new URI(getUsersUrl), httpEntity, SingleUserResponse.class);
        return responseEntity.getBody().getUser();
    }

    public Listing saveListing(ListingInputJson listingInputJson) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("user_id", listingInputJson.getUser_id());
        requestBody.add("listing_type", listingInputJson.getListing_type());
        requestBody.add("price", listingInputJson.getPrice());


        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<SingleListingResponse> responseEntity = new RestTemplate().postForEntity(new URI(listingServiceUrl), httpEntity, SingleListingResponse.class);
        return responseEntity.getBody().getListing();
    }
}
