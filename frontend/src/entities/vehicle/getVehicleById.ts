import {doc, getDoc} from 'firebase/firestore';
import {db} from '../../config/firebase';

export const getVehicleById = async (vehicleId: string) => {
  const ref = doc(db, 'vehicles', vehicleId);
  const snap = await getDoc(ref);

  if (!snap.exists()) {
    return null
  }

  return snap.data();
}
