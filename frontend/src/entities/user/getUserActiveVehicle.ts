import {doc, getDoc} from 'firebase/firestore';
import {auth, db} from '../../config/firebase';

export const getUserActiveVehicleId = async (): Promise<string | null> => {
  const user = auth.currentUser;

  if (!user) {
    return null
  }

  const ref = doc(db, 'users', user.uid);
  const snap = await getDoc(ref);

  if (snap.exists()) {
    snap.data().activeVehicle
  }
  return null;
}
