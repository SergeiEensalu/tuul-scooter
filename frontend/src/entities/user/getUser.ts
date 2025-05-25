import {doc, getDoc} from 'firebase/firestore';
import {auth, db} from '../../config/firebase';

export type UserProfile = {
  createdAt: any;
  name: string | null;
  email: string;
  phoneNo: string | null;
  profileImagePath: string | null;
  activeVehicle?: string | null;
};

export const getUser = async (): Promise<UserProfile | null> => {
  if (import.meta.env.VITE_USE_FIREBASE_MOCK) {
    return {
      createdAt: new Date().toISOString(),
      email: 'mockuser@tuul.app',
      name: 'Mock User',
      phoneNo: null,
      profileImagePath: null,
      activeVehicle: '12345678',
    };
  }

  const user = auth.currentUser;

  if (!user) {
    return null
  }

  const ref = doc(db, 'users', user.uid);
  const snap = await getDoc(ref);

  if (snap.exists()) {
    const data = snap.data();

    return {
      createdAt: data.createdAt,
      email: data.email,
      name: data.name,
      phoneNo: data.phoneNo,
      profileImagePath: data.profileImagePath,
      activeVehicle: data.activeVehicle
    }
  }
  return null;
}
