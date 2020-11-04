package diningphilosophers;

public class ChopStick {
    // Le nombre total de baguettes
    private static int stickCount = 0;
    // Le numéro de chaque baguette
    private final int myNumber;
    // Est-ce que ma baguette est libre ?
    private boolean iAmFree = true;

    public ChopStick() {
        // Chaque baguette est numérotée 
        myNumber = ++stickCount;
    }

    synchronized void take() throws InterruptedException{
        //On attend que la baguette soit libre
        while (!iAmFree) {
			wait(); // Peut lever InterruptedException
		}
	assert (iAmFree); // On s'assure que la baguette est bien libre
	this.iAmFree = false; //On la fait passer en plus disponible
	System.out.printf("La baguette" + myNumber + "est prise");
	notifyAll(); // Notifier que la baguette n'est plus libre
	} 
   
    synchronized void release() throws InterruptedException{
    //On attend que la baguette soit prise
        while (iAmFree) {
			wait(); // Peut lever InterruptedException
		}
	assert (!iAmFree); // On s'assure que la baguette est bien prise
	this.iAmFree = true; //On la fait passer est libérée
        System.out.printf("Le philosophe lache la baguette" + myNumber);
	notifyAll(); // Notifier que la baguette est libéréé
	}
    
    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
