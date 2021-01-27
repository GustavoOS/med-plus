import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Utils {
	public static double calculateDistance(CoordinateDS pointA, CoordinateDS pointB) {
		CoordinateDS aRad, bRad, delta;
		aRad = new CoordinateDS(Math.toRadians(pointA.latitude), Math.toRadians(pointA.longitude));
		bRad = new CoordinateDS(Math.toRadians(pointB.latitude), Math.toRadians(pointB.longitude));

		// Haversine formula  
		delta = new CoordinateDS(bRad.latitude - aRad.latitude, bRad.longitude - aRad.longitude);
		double a = Math.pow(Math.sin(delta.latitude / 2), 2)
				+ Math.cos(aRad.latitude)
				* Math.cos(bRad.latitude)
				* Math.pow(Math.sin(delta.longitude / 2), 2);

		double c = 2 * Math.asin(Math.sqrt(a));

		// Radius of earth in kilometers. Use 3956  
		// for miles 
		double r = 6371;

		// calculate the result 
		return (c * r);
	}

	public static ArrayList<HealthProvider> copyProviderList(ArrayList<HealthProvider> providers)
	{
		ArrayList<HealthProvider> copy = new ArrayList<HealthProvider>();
		for (HealthProvider healthProvider : providers) {
			copy.add(healthProvider.clone());
		}
		return copy;
	}

	public static int calculateAge(LocalDate birth)
	{
		if(birth == null)
			return 0;
		return Period.between(birth, LocalDate.now()).getYears();
	}
}
