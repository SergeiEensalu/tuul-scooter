import {doc, getDoc} from 'firebase/firestore';
import {auth, db} from '../../config/firebase';
import {ApiResult} from "../../shared/types/api";

export type UserProfile = {
  createdAt: any;
  name: string | null;
  email: string;
  phoneNo: string | null;
  profileImagePath: string | null;
  activeVehicle?: string | null;
};

export const getUser = async (): Promise<ApiResult<UserProfile>> => {
  if (import.meta.env.VITE_USE_FIREBASE_MOCK === 'true') {
    return {
      success: true,
      data: {
        createdAt: new Date().toISOString(),
        email: 'mockuser@tuul.app',
        name: 'Mock User',
        phoneNo: null,
        profileImagePath: null,
        activeVehicle: '12345678',
      },
    };
  }

  const user = auth.currentUser;

  if (!user) {
    return {
      success: false,
      message: 'User not authenticated',
      code: 'AUTH_REQUIRED',
    };
  }

  const ref = doc(db, 'users', user.uid);
  const snap = await getDoc(ref);

  if (!snap.exists()) {
    return {
      success: false,
      message: 'User profile not found',
      code: 'USER_NOT_FOUND',
    };
  }

  const data = snap.data();
  return {
    success: true,
    data: {
      createdAt: data.createdAt,
      email: data.email,
      name: data.name,
      phoneNo: data.phoneNo,
      profileImagePath: data.profileImagePath,
      activeVehicle: data.activeVehicle,
    },
  };
}
