package at.fhs.nos.parksim;

public class GateWorker extends Thread {
    private Gate gate;
    private ParkingArea parkingArea;

    public GateWorker(Gate gate, ParkingArea area )
    {
        this.gate = gate;
        this.parkingArea = area;
    }
    
    @Override
    public void run()
    {
        while (gate.isOpen())
        {
            try
            {
                Thread.sleep(2000);
                double insert = Math.random();
                // in 70% we want to drive in
                // because we want to see when the
                // parking area is full ...
                if (insert < 0.7f)
                {
                    Car car = CarFactory.createNew();
                    gate.driveIn(car);
                }
                else
                {
                	Car car = parkingArea.findOccupiedPlace().getPlacedCar(); //TODO: FINISHED choose one car from the parking area
                    if (car != null)
                    {
                        gate.driveOut(car);
                    }
                }
            }
            catch (InterruptedException e)
            {
            	// TODO: FINISHED handle the interrupt accordingly, shutdown gate
                gate.setGateOpen(false);
                System.out.println("Gate will be shutdown.");
                System.out.println(e.getMessage());
            }
            catch (NoFreePlaceException e)
            {
            	// TODO: FINISHED print a message the place is full
                System.out.println(e.getMessage());
                interrupt();
            }
        }
    }
}
