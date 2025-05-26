import {doc, getDoc} from 'firebase/firestore';
import {db} from '../../config/firebase';
import {VehicleData} from './types';
import {ApiResult} from "../../shared/types/api";

export const getVehicleById = async (vehicleId: string): Promise<ApiResult<VehicleData>> => {
  if (import.meta.env.VITE_USE_FIREBASE_MOCK === 'true') {
    return {
      success: true,
      data: {
        id: vehicleId,
        location: {latitude: 59.437, longitude: 24.7536},
        soc: 87,
        odometer: 1223,
        poweredOn: false,
        estimatedRange: 42,
        vehicleCode: 'code1',
      },
    };
  }

  const ref = doc(db, 'vehicles', vehicleId);
  const snap = await getDoc(ref);

  if (!snap.exists()) {
    return {
      success: false,
      message: 'Vehicle not found',
      code: 'VEHICLE_NOT_FOUND',
    };
  }

  const data = snap.data();

  return {
    success: true,
    data: {
      id: snap.id,
      location: {
        latitude: data.location.latitude,
        longitude: data.location.longitude,
      },
      soc: data.soc,
      odometer: data.odometer,
      poweredOn: data.poweredOn,
      estimatedRange: data.estimatedRange,
      vehicleCode: data.vehicleCode,
    },
  };
}
