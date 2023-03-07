package com.devsuperior.movieflix.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRpository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private MovieRepository movieRepository;
	
	
	@PreAuthorize("hasAnyRole('MEMBER')")
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		
		Review entity = new Review();
		User user = authService.authenticated();
		Optional<Movie> obj = movieRepository.findById(dto.getMovieId());
		Movie movie = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		entity.setText(dto.getText());
		entity.setMovie(movie);
		entity.setUser(user);
		
		entity = reviewRpository.save(entity);
		return new ReviewDTO(entity);
	}
	
}
