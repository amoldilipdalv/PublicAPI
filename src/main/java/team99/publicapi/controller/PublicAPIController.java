package team99.publicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import team99.publicapi.dto.MultipleListingResponse;
import team99.publicapi.dto.UserServiceConstants;
import team99.publicapi.service.PublicAPIService;


@RestController
@RequestMapping("/public-api")
public class PublicAPIController {

    @Autowired
    private PublicAPIService publicAPIService;

    @GetMapping("/listings")
    public MultipleListingResponse getAllListings(@RequestParam(name = "page_num", defaultValue = UserServiceConstants.defaultPageNum) Integer pageNum,
                                                  @RequestParam(name = "page_size", defaultValue = UserServiceConstants.defaultPageSize) Integer pageSize,
                                                  @RequestParam(name = "user_id", required = false) Integer userId)
    {
        return publicAPIService.getAllListings(pageNum, pageSize, userId);
    }

}
