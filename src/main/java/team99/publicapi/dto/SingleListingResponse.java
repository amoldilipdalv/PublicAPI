package team99.publicapi.dto;

import org.springframework.stereotype.Component;
import team99.publicapi.domain.Listing;


@Component
public class SingleListingResponse {
    private Listing listing;

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public SingleListingResponse setSingleListingResponse(Listing listing)
    {
        SingleListingResponse singleListingResponse = new SingleListingResponse();

        if(null != listing)
        {
            singleListingResponse.setListing(listing);
        }
        else
        {
           singleListingResponse.setListing(new Listing());
        }
        return singleListingResponse;
    }
}
