
package com.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.entity.Screen;
import com.bookmyshow.entity.Theatre;
import com.bookmyshow.repo.ScreenRepo;

@Repository
public class ScreenDao {

	@Autowired
	private ScreenRepo screenRepo;

	public Screen addScreen(Screen screen) {
		return screenRepo.save(screen);
	}

	public Screen updateScreen(Screen screen, long screenId) {
		Optional<Screen> optional = screenRepo.findById(screenId);

		if (optional.isPresent()) {
			screen.setScreenId(screenId);
			screen.setSeats(optional.get().getSeats());
			screen.setTheatre(optional.get().getTheatre());

			return screenRepo.save(screen);
		} else {
			return null;
		}

	}

	public Screen getScreenById(long screenId) {
		Optional<Screen> optional = screenRepo.findById(screenId);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}

	}

	public Screen deleteScreenById(long screenId) {

		Optional<Screen> optional = screenRepo.findById(screenId);

		if (optional.isPresent()) {
			Screen screen = optional.get();
			// screen.setSeats(null);
			//Theatre theatre = screen.getTheatre();
			//theatre.setScreens(null);
			//screen.setTheatre(null);
			screenRepo.delete(screen);
			return optional.get();
		} else {
			return null;
		}
	}

}
