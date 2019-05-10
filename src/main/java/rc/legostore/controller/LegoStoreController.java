package rc.legostore.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rc.legostore.model.LegoSet;
import rc.legostore.persistence.LegoSetRepository;

@RestController
@RequestMapping("/legostore/api")
public class LegoStoreController {
	
	@Autowired
	private LegoSetRepository legoSetRepository;

	@PostMapping
	public void insert(@RequestBody LegoSet legoSet) {
		legoSetRepository.insert(legoSet);
	}
	
	@PutMapping
	public void update(@RequestBody LegoSet legoSet) {
		legoSetRepository.save(legoSet);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		legoSetRepository.deleteById(id);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Collection<LegoSet>> all() {
		Collection<LegoSet> legoSets = legoSetRepository.findAll();
		return ResponseEntity.ok(legoSets);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LegoSet> byId(@PathVariable String id) {
		LegoSet legoSet = legoSetRepository.findById(id).orElse(null);
		return ResponseEntity.ok(legoSet);
	}
	
	@GetMapping("/{theme}")
	public ResponseEntity<Collection<LegoSet>> byTheme(@PathVariable String theme) {
		Collection<LegoSet> legoSets = legoSetRepository.findAllByThemeContains(theme);
		return ResponseEntity.ok(legoSets);
	}
	
	@GetMapping("/byDeliveryFee/{price}")
	public ResponseEntity<Collection<LegoSet>> byDeliveryFee(@PathVariable int price) {
		Collection<LegoSet> legoSets = legoSetRepository.findAllByDeliveryPriceLessThan(price);
		return ResponseEntity.ok(legoSets);
	}
	
	@GetMapping("/byReviewRating/{rating}")
	public ResponseEntity<Collection<LegoSet>> byReviewRating(@PathVariable int rating) {
		Collection<LegoSet> legoSets = legoSetRepository.findByReviewRating(rating);
		return ResponseEntity.ok(legoSets);
	}
}
