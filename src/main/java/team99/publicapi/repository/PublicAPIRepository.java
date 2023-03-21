package team99.publicapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team99.publicapi.domain.Listing;
import team99.publicapi.domain.User;
import team99.publicapi.dto.MultipleListingResponse;
import team99.publicapi.dto.MultipleUserResponse;
import team99.publicapi.dto.PublicAPIServiceConstants;
import team99.publicapi.utility.PublicAPIUtility;

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
            listing.setUser(usersMap.get(listing.getUser_id()));
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

}
