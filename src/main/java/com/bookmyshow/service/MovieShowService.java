package com.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.dao.MovieDao;
import com.bookmyshow.dao.MovieShowDao;
import com.bookmyshow.dao.ScreenDao;
import com.bookmyshow.dao.TheatreDao;
import com.bookmyshow.dto.ShowDto;
import com.bookmyshow.entity.Movie;
import com.bookmyshow.entity.MovieShow;
import com.bookmyshow.entity.Screen;
import com.bookmyshow.entity.Theatre;
import com.bookmyshow.enums.ScreenAvailability;
import com.bookmyshow.exception.ScreenAlreadyAlloted;
import com.bookmyshow.exception.TheatreIdNotFoundException;
import com.bookmyshow.util.ResponseStructure;

@Service
public class MovieShowService {

	@Autowired
	private MovieShowDao movieShowDao;

	@Autowired
	private TheatreDao theatreDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ScreenDao screenDao;

	@Autowired
	private MovieDao movieDao;

	public ResponseEntity<ResponseStructure<MovieShow>> addShow(ShowDto showDto, long theatreId) {

		Theatre dbTheatre = theatreDao.getTheatreById(theatreId);

		if (dbTheatre != null) {
			MovieShow movieShow = this.modelMapper.map(showDto, MovieShow.class);
			long screenId = movieShow.getScreenId();
			Screen dbScreen = screenDao.getScreenById(screenId);
			if (dbScreen != null) {

				if (dbScreen.getAvailability().equals(ScreenAvailability.NOT_ALLOTED)) {
					long movieId = movieShow.getMovieId();
					Movie dbMovie = movieDao.getMovieById(movieId);

					if (dbMovie != null) {

						movieShow.setMovieDescription(dbMovie.getMovieDescription());
						movieShow.setMovieDuration(dbMovie.getMovieDuration());
						movieShow.setMovieName(dbMovie.getMovieName());
						movieShow.setMovieLanguage(dbMovie.getLanguage());
						movieShow.setScreenName(dbScreen.getScreenName());

						movieShow.setTheatre(dbTheatre);
						MovieShow dbMovieShow = movieShowDao.saveShow(movieShow);

						if (dbTheatre.getMovieShows().isEmpty()) {
							// this is the first show
							List<MovieShow> list = new ArrayList<>();
							list.add(dbMovieShow);
							dbTheatre.setMovieShows(list);
							theatreDao.updateTheatre(dbTheatre, theatreId);
						} else {
							// show already present

							List<MovieShow> list = dbTheatre.getMovieShows();
							list.add(dbMovieShow);

							dbTheatre.setMovieShows(list);
							theatreDao.updateTheatre(dbTheatre, theatreId);

						}

						ResponseStructure<MovieShow> responseStructure = new ResponseStructure<>();
						responseStructure.setMessage("show added sucessfully");
						responseStructure.setStatusCode(HttpStatus.CREATED.value());
						responseStructure.setData(dbMovieShow);
						return new ResponseEntity<ResponseStructure<MovieShow>>(responseStructure, HttpStatus.CREATED);

					} else {
						// MovieIdNotFoundException("Sorry failed to add show");
						return null;
					}

				} else {
					throw new ScreenAlreadyAlloted("Sorry failed to add show");
				}

			} else {
				return null;
				// ScreenIdNotFoundException("Sorry failed to add show");

			}
		} else {
			// return null;

			throw new TheatreIdNotFoundException("sorry failed to add show");
		}
	}

	public ResponseEntity<ResponseStructure<MovieShow>> updateShow(ShowDto showDto, long showId) {
		MovieShow movieShow = this.modelMapper.map(showDto, MovieShow.class);
		MovieShow dbMovieShow = movieShowDao.updateShow(movieShow, showId);

		if (dbMovieShow != null) {
			ResponseStructure<MovieShow> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("show updated sucessfully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(dbMovieShow);

			return new ResponseEntity<ResponseStructure<MovieShow>>(responseStructure, HttpStatus.CREATED);

		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<MovieShow>> getShowById(long showId) {

		MovieShow dbMovieShow = movieShowDao.getShowById(showId);

		if (dbMovieShow != null) {
			ResponseStructure<MovieShow> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("show fetched sucessfully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(dbMovieShow);
			return new ResponseEntity<ResponseStructure<MovieShow>>(responseStructure, HttpStatus.CREATED);

		} else {
			return null;
		}

	}

	public ResponseEntity<ResponseStructure<ShowDto>> deleteShowById(long showId) {
		MovieShow dbMovieShow = movieShowDao.deleteShowById(showId);

		if (dbMovieShow != null) {
			ResponseStructure<ShowDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("show deleted sucessfully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(this.modelMapper.map(dbMovieShow, ShowDto.class));
			return new ResponseEntity<ResponseStructure<ShowDto>>(responseStructure, HttpStatus.CREATED);

		} else {
			return null;
		}
	}
}
