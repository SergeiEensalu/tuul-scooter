import {useEffect, useState} from 'react';
import {getUser} from '../../entities/user/getUser';
import {getVehicleById} from '../../entities/vehicle/getVehicleById';
import {VehicleData} from '../../entities/vehicle/types';

export const useVehicle = (): {
  vehicleData: VehicleData | null;
  loading: boolean;
  refresh: () => Promise<void>;
} => {
  const [vehicleData, setVehicleData] = useState<VehicleData | null>(null);
  const [loading, setLoading] = useState(true);

  const fetch = async () => {
    setLoading(true);
    const userResult = await getUser();
    if (!userResult.success) {
      console.warn('Failed to fetch user.', userResult.message);
      setVehicleData(null);
      setLoading(false);
      return;
    }

    const user = userResult.data;

    if (user?.activeVehicle) {
      const vehicleResult = await getVehicleById(user.activeVehicle);
      if (!vehicleResult.success) {
        console.warn('Failed to fetch vehicle.', vehicleResult.message);
        setVehicleData(null);
        setLoading(false);
        return;
      }
      setVehicleData(vehicleResult.data);
    } else {
      setVehicleData(null);
    }
    setLoading(false);
  };

  useEffect(() => {
    fetch();
  }, []);

  return {vehicleData, loading, refresh: fetch};
};
