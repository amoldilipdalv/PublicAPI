package team99.publicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team99.publicapi.domain.Listing;
import team99.publicapi.dto.MultipleListingResponse;
import team99.publicapi.utility.PublicAPIUtility;


import java.util.HashMap;
import java.util.List;

@Service
public class PublicAPIService {

    @Autowired
    private PublicAPIUtility publicAPIUtility;

    @Value("${listings.service.get.listings.url}")
    private String getAllListingUrl;

    @Value("${listings.service.get.user_id.url}")
    private String getUserListingUrl;

    public MultipleListingResponse getAllListings(Integer pageNum, Integer pageSize, Integer userId)
    {
        HashMap<String, Integer> listingsReqInput = new HashMap<>();
        listingsReqInput.put("page_num", pageNum);
        listingsReqInput.put("page_size", pageSize);

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

        return multipleListingResponse;
    }

}
