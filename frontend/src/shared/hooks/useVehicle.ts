import {useEffect, useState} from 'react';
import {getUser} from '../../entities/user/getUser';
import {getVehicleById} from '../../entities/vehicle/getVehicleById';
import {VehicleData} from '../../entities/vehicle/types';

export const useVehicle = () => {
  const [vehicleData, setVehicleData] = useState<VehicleData | null>(null);

  const fetch = async () => {
    const user = await getUser();
    if (user?.activeVehicle) {
      const data = await getVehicleById(user.activeVehicle);
      setVehicleData(data);
    } else {
      setVehicleData(null);
    }
  };

  useEffect(() => {
    fetch();
  }, []);

  return {vehicleData, refresh: fetch};
};
