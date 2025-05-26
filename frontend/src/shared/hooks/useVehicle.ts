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
    const user = await getUser();
    if (user?.activeVehicle) {
      const data = await getVehicleById(user.activeVehicle);
      setVehicleData(data);
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
