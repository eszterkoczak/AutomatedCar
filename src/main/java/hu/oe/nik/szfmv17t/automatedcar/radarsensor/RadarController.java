package hu.oe.nik.szfmv17t.automatedcar.radarsensor;

import hu.oe.nik.szfmv17t.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv17t.automatedcar.SystemComponent;
import hu.oe.nik.szfmv17t.automatedcar.bus.Signal;
import hu.oe.nik.szfmv17t.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv17t.automatedcar.powertrainsystem.PowertrainSystem;
import hu.oe.nik.szfmv17t.environment.utils.Triangle;

public class RadarController extends SystemComponent{
	private static RadarSensor radarSensor;
	private AutomatedCar automatedCar;
    
	public RadarController (AutomatedCar auto) {
		this.automatedCar = auto;
		initSensor();
	}

	private void initSensor(){
		radarSensor = new RadarSensor(automatedCar.getPositionObj(), automatedCar.getAxisAngle());
	}

    @Override
    public void loop() {
            VirtualFunctionBus.sendSignal(new Signal(PowertrainSystem.RADAR_SENSOR_ID, null));
            Triangle sensorArea = radarSensor.calculateCoordinates(automatedCar.getPositionObj(), automatedCar.getAxisAngle());
    }

    @Override
    public void receiveSignal(Signal s) { }
}
