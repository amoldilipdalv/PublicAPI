package team99.publicapi.dto;

import org.springframework.stereotype.Component;
import team99.publicapi.domain.Listing;
import team99.publicapi.domain.Listing;

import java.util.Collections;
import java.util.List;
@Component
public class MultipleListingResponse extends Response{
    private List<Listing> listings;

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    public MultipleListingResponse setMultipleListingResponse(List<Listing> listings)
    {
        MultipleListingResponse multipleListingResponse = new MultipleListingResponse();

        if(null != listings && !listings.isEmpty())
        {
            multipleListingResponse.setResult(true);
            multipleListingResponse.setListings(listings);
        }
        else
        {
            multipleListingResponse.setResult(false);
            multipleListingResponse.setListings(Collections.EMPTY_LIST);
        }
        return multipleListingResponse;
    }
}
