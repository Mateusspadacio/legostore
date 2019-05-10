package rc.legostore.persistence;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import rc.legostore.model.LegoSet;

@Repository
public interface LegoSetRepository extends MongoRepository<LegoSet, String> {
	
	public Collection<LegoSet> findAllByThemeContains(String theme);
	
	@Query("'delivery.deliveryFee': { $lt: ?0 }")
	public Collection<LegoSet> findAllByDeliveryPriceLessThan(int price);
	
	@Query("'reviews.rating': { $eq: ?0 }")
	public Collection<LegoSet> findByReviewRating(int rating);
}
