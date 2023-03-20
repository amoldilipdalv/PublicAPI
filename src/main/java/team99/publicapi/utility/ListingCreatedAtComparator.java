package team99.publicapi.utility;

import team99.publicapi.domain.Listing;

import java.util.Comparator;

public class ListingCreatedAtComparator implements Comparator<Listing> {
    public int compare(Listing listing1, Listing listing2) {
        return Long.compare(listing2.getCreated_at(), listing1.getCreated_at());
    }
}
