package team99.publicapi.utility;

import org.springframework.stereotype.Component;
import team99.publicapi.domain.Listing;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Component
public class PublicAPIUtility {

    public List<Listing> sortListingDescOnCreatedAt(List<Listing> listings)
    {
        Collections.sort(listings, new ListingCreatedAtComparator().reversed());
        return listings;
    }

}
