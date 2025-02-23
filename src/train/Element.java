package train;

/**
 * Cette classe abstraite est la représentation générique d'un élément de base d'un
 * circuit, elle factorise les fonctionnalitÃ©s communes des deux sous-classes :
 * l'entrée d'un train, sa sortie et l'appartenance au circuit.<br/>
 * Les deux sous-classes sont :
 * <ol>
 *   <li>La représentation d'une gare : classe {@link Station}</li>
 *   <li>La représentation d'une section de voie ferrée : classe {@link Section}</li>
 * </ol>
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public abstract class Element {
	private final String name;
	protected Railway railway;

	protected Element(String name) {
		if(name == null)
			throw new NullPointerException();
		this.name = name;
	}

	public void setRailway(Railway r) {
		if(r == null)
			throw new NullPointerException();
		
		this.railway = r;
	}
	
	public Railway getRailway() {
		return railway;
	};
	
	public Element nextElement(Direction d) {
		// comment out to see the number of trains in each direction during the simulation
		//System.out.println("trains in the direction RL: "+railway.RLTrainOnTrack);
		//System.out.println("trains in the direction LR: "+railway.LRTrainOnTrack);
		Element[] elements=railway.getElements();
		Element nextElement = this;
		for (int i=0; i<elements.length; i++) {
			if (elements[i]==this  && i!=0  && i!=elements.length-1) {
				if (d == Direction.LR ) {
						nextElement = elements[i+1];
		    	}
				else {
					nextElement = elements[i-1];
				}
	    	}
			else if (elements[i]==this  && i==0 ) {
				nextElement = elements[1];
	    	}
			else if (elements[i]==this  && i==elements.length-1 ) {
				nextElement = elements[elements.length-2];
	    	}
		}
		return nextElement;		
	}
	
	abstract void enter(Train t) throws InterruptedException;
	abstract void leave(Train t) throws InterruptedException;

	@Override
	public String toString() {
		return this.name;
	}

}
