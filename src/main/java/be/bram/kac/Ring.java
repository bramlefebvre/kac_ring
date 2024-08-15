package be.bram.kac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ring {

	private final Set<Integer> obstacleLocations;
	private List<Color> particleColors;
	
	
	public Ring(Set<Integer> obstacleLocations, List<Color> particleColors) {
		this.obstacleLocations = new HashSet<>(obstacleLocations);
		this.particleColors = new ArrayList<>(particleColors);
	}

	public List<Color> getValues() {
		return Collections.unmodifiableList(particleColors);
	}
	
	public int getNumberOfWhiteParticles1() {
		return particleColors.stream().filter(color -> color == Color.WHITE).collect(() -> Integer.valueOf(0), (number, color) -> Integer.valueOf(number + 1), (number1, number2) -> Integer.valueOf(number1 + number2));
	}
	
	public int getNumberOfWhiteParticles2() {
		return (int) particleColors.stream().filter(color -> color == Color.WHITE).count();
	}

	public Set<Integer> getObstacleLocations() {
		return Collections.unmodifiableSet(obstacleLocations);
	}
	
	public void timeEvolve1() {
		List<Color> particleColors = new ArrayList<>();
		int index = this.particleColors.size() - 1;
		particleColors.add(timeEvolveColor(index, this.particleColors.get(index)));
		for (index = 0; index < this.particleColors.size() - 1; index++) {
			particleColors.add(timeEvolveColor(index, this.particleColors.get(index)));
		}
		this.particleColors = particleColors;
	}
	
	
	public void timeEvolve2() {
	
	}
	
	public Color timeEvolveColor(int originalIndex, Color color) {
		if (obstacleLocations.contains(originalIndex)) {
			if (color == Color.BLACK) {
				return Color.WHITE;
			} else {
				return Color.BLACK;
			}
		} else {
			return color;
		}
	}
}
