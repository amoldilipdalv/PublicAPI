package team99.publicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import team99.publicapi.domain.User;
import team99.publicapi.dto.*;
import team99.publicapi.service.PublicAPIService;

import java.net.URISyntaxException;


@RestController
@RequestMapping("/public-api")
public class PublicAPIController {

    @Autowired
    private PublicAPIService publicAPIService;

    @GetMapping("/listings")
    public MultipleListingResponse getAllListings(@RequestParam(name = "page_num", defaultValue = PublicAPIServiceConstants.defaultPageNum) Integer pageNum,
                                                  @RequestParam(name = "page_size", defaultValue = PublicAPIServiceConstants.defaultPageSize) Integer pageSize,
                                                  @RequestParam(name = "user_id", required = false) Integer userId)
    {
        return publicAPIService.getAllListings(pageNum, pageSize, userId);
    }

    @PostMapping("/users")
    public SingleUserResponse saveUser(@RequestBody UserInputJson userInputJson) throws URISyntaxException {
        return publicAPIService.saveUser(userInputJson);
    }

    @PostMapping("/listings")
    public SingleListingResponse saveListings(@RequestBody ListingInputJson listingInputJson) throws URISyntaxException {
        return publicAPIService.saveListing(listingInputJson);
    }

}
