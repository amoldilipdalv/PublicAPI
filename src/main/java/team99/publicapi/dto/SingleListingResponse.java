package team99.publicapi.dto;

import org.springframework.stereotype.Component;
import team99.publicapi.domain.Listing;


@Component
public class SingleListingResponse extends Response{
    private Listing listing;

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public SingleListingResponse setSingleListingResponse(Listing user)
    {
        SingleListingResponse singleListingResponse = new SingleListingResponse();

        if(null != user)
        {
            singleListingResponse.setResult(true);
            singleListingResponse.setListing(listing);
        }
        else
        {
            singleListingResponse.setResult(false);
            singleListingResponse.setListing(new Listing());
        }
        return singleListingResponse;
    }
}
