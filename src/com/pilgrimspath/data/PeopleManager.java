package com.pilgrimspath.data;

public class PeopleManager {
	private int population = 0;
	private int maxPopulation = 0;
	private int lastPopulation = 0;
	private int lifeSupport = 25;
	//private float happiness;
	private float health = 1f; 
	
	private int curLabor = 0;
	private int allocatedLabor = 0;	// allocated each tick
	private int lastAllocatedLabor = 0;
	private boolean laborShortage = false;
	private boolean curLaborShortage = false; // updated each tick
	private int curRequestedLabor = 0;
	private int requestedLabor = 0;

	private float birthRate = 0.01f;
	private float deathRate = 0.002f;
	
	private Ship container;
	
	public static final float FOOD_PER_TICK_PER_PERSON = 0.02f;
	public static final float STARVATION_HEALTH_LOSS_PER_PERCENT = 0.01f;
	public static final float SUFFOCATION_HEALTH_LOSS_PER_PERCENT = 0.2f;
	public static final float HEALTH_RECOVER_RATE = 0.02f;
	public static final float OVERCROWDED_MORTALITY_RATE = 0.02f;
	
	public static final float MORTALITY_THRESHOLD = 0.75f;
	public static final float MORTALITY_RATE = 0.25f;
	
	public static final float LABOR_PARTICIPATION = 0.7f;
	
	public PeopleManager(Ship _container) {
		container = _container;
	}
	
	public void tick() {
		adjustHealth();
		adjustPopulation();
		adjustLabor();
		// adjust happiness based on current max
	}
	
	public void updateLifeSupport(int supporting) {
		
	}
	
	public synchronized void addHousing(int units) { maxPopulation += units; }

	public synchronized void removeHousing(int units) { 
		maxPopulation -= units; 
		if (maxPopulation < 0) { maxPopulation = 0; }
	}
	
	private void adjustHealth() {
		// try to consume food
		boolean areHealthy = true;
		Resource useFood = new Resource(Resource.FOOD_NAME, FOOD_PER_TICK_PER_PERSON * population);
		if (!container.fleet.resources.remove(useFood)) {
			// could not eat all that we wanted
			areHealthy = false;
			double fleetFood = container.fleet.resources.getResourceCount(Resource.FOOD_NAME);
			int fed = (int) Math.floor(fleetFood / FOOD_PER_TICK_PER_PERSON);
			double percentSick = 1 - (population == 0 ? 1 : fed / population);

			// adjust health based on food consumed
			health -= (float) (percentSick * STARVATION_HEALTH_LOSS_PER_PERCENT);
		} 
		
		// factor in life support
		if (lifeSupport < population) {
			areHealthy = false;
			double percentSick = 1 - lifeSupport / population;
			health -= (float) (percentSick * SUFFOCATION_HEALTH_LOSS_PER_PERCENT);
		}
		
		// if healthy, recover health
		if (areHealthy) { health += HEALTH_RECOVER_RATE; }		
	}

	// adjust population based on health
	private void adjustPopulation() {
		// aside from base death by aging, mortality kicks in only at a certain population level
		if (health < MORTALITY_THRESHOLD) {
			// each tick, a certain number of the sick will die
			float percentSick = MORTALITY_THRESHOLD - health;
			float overcrowdedModifier = OVERCROWDED_MORTALITY_RATE * (maxPopulation - population);
			lastPopulation = population;
			// TODO: labor & population colors aren't showing up properly
			population = (int) Math.floor((1 + (birthRate - (percentSick * MORTALITY_RATE) 
						- overcrowdedModifier - deathRate)) * population); 
			// limit to feasible numbers
			population = Math.max(population,0);
		}
	}
	
	private void adjustLabor() {
		curLabor = (int)Math.ceil(LABOR_PARTICIPATION * population);
	}
	
	public int checkLabor(int units, int laborPerUnit) {
		int available = curLabor - allocatedLabor;
		return (int) Math.min(units, Math.floor(available / laborPerUnit));		
	}
	
	// returns the number of units that were supplied with labor
	public int requestLabor(int units, int laborPerUnit) {
		curRequestedLabor += units * laborPerUnit;
		int available = curLabor - allocatedLabor;
		int supplied = (int) Math.min(units, Math.floor(available / laborPerUnit));
		if (supplied < units) { curLaborShortage = true; }
				
		allocatedLabor += supplied * laborPerUnit;
		return supplied;
	}
	
	public void startNewRound() {
		// reset per tick variables
		lastAllocatedLabor = 0;
		allocatedLabor = 0;
		laborShortage = curLaborShortage;
		curLaborShortage = false;
		requestedLabor = curRequestedLabor;
		curRequestedLabor = 0;
	}
	
	public synchronized void adjustPopulation(int pop) { population += pop; }
	
	public synchronized int getPopulation() { return population; }
	
	public synchronized int getMaxPopulation() { return maxPopulation; }
	
	public synchronized boolean hasLaborShortage() { return laborShortage; }
	
	public synchronized int getLastPopulation() { return lastPopulation; }
	
	public synchronized int getRequestedLabor() { return requestedLabor; }
	
	public synchronized int getAvailableLabor() { return lastAllocatedLabor; }
}
