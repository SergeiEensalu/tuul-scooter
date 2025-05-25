import {doc, getDoc} from 'firebase/firestore';
import {db} from '../../config/firebase';
import {VehicleData} from './types';

export const getVehicleById = async (vehicleId: string): Promise<VehicleData | null> => {
  if (import.meta.env.VITE_USE_FIREBASE_MOCK === 'true') {
    return {
      id: vehicleId,
      location: {latitude: 59.437, longitude: 24.7536},
      soc: 87,
      odometer: 1223,
      poweredOn: false,
      estimatedRange: 42,
      vehicleCode: 'code1',
    };
  }

  const ref = doc(db, 'vehicles', vehicleId);
  const snap = await getDoc(ref);

  if (!snap.exists()) {
    return null
  }

  const data = snap.data();

  return {
    id: snap.id,
    location: data.location,
    soc: data.soc,
    odometer: data.odometer,
    poweredOn: data.poweredOn,
    estimatedRange: data.estimatedRange,
    vehicleCode: data.vehicleCode,
  };
}
