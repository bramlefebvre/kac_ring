package be.bram.kac;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RingBuilder {
	
	private static final Random rng = new Random();
	
	private int numberOfSites;
	private int numberOfObstacles = 0;
	private int initialNumberOfWhiteParticles = 0;
	
	public RingBuilder numberOfSites(int numberOfSites) {
		this.numberOfSites = numberOfSites;
		return this;
	}
	
	public RingBuilder numberOfObstacles(int numberOfObstacles) {
		this.numberOfObstacles = numberOfObstacles;
		return this;
	}
	
	public RingBuilder initialNumberOfWhiteParticles(int initialNumberOfWhiteParticles) {
		this.initialNumberOfWhiteParticles = initialNumberOfWhiteParticles;
		return this;
	}
	
	public Ring buildRing() {
		if (numberOfSites == 0) {
			throw new RuntimeException("number_of_sites should not be 0");
		}
		List<Color> initialParticleColors = generateInitialParticleColors();
		Set<Integer> obstacleLocations = generateObstacleLocations();
		return new Ring(obstacleLocations, initialParticleColors);
	}
	
	private Set<Integer> generateObstacleLocations() {
		List<Integer> locationsWithoutObstacles = generateLocations();
		Set<Integer> obstacleLocations = new HashSet<>();
		for (int i = 0; i < numberOfObstacles; i++) {
			int locationIndex = rng.nextInt(numberOfSites - i);
			Integer location = locationsWithoutObstacles.remove(locationIndex);
			obstacleLocations.add(location);
		}
		return obstacleLocations;
		
	}
	
	private List<Integer> generateLocations() {
		List<Integer> locations = new ArrayList<>();
		for (int i = 0; i < numberOfSites; i++) {
			locations.add(i);
		}
		return locations;
	}
	
	private List<Color> generateInitialParticleColors() {
		List<Integer> locationsWithBlackParticleColor = generateLocations();
		for (int i = 0; i < initialNumberOfWhiteParticles; i++) {
			int locationIndex = rng.nextInt(numberOfSites - i);
			locationsWithBlackParticleColor.remove(locationIndex);
		}
		List<Color> initialParticleColors = new ArrayList<>();
		for (int i = 0; i < numberOfSites; i++) {
			if (locationsWithBlackParticleColor.contains(i)) {
				initialParticleColors.add(Color.BLACK);
			} else {
				initialParticleColors.add(Color.WHITE);
			}
		}
		return initialParticleColors;
	}
}