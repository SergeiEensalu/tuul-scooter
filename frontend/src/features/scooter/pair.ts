import {auth} from "../../config/firebase";
import {http} from "../../lib/http";
import {ApiResult} from "../../shared/types/api";

export const pair = async (vehicleCode: string): Promise<ApiResult> => {
  const token = await auth.currentUser?.getIdToken();

  if (!token) {
    return {success: false, message: 'User not authenticated'}
  }

  try {
    await http<void>(
      `https://europe-west3-coscooter-eu-staging.cloudfunctions.net/pair?apiKey=${token}`,
      'POST',
      {vehicleCode}
    );

    return {success: true};
  } catch (err: any) {
    return {
      success: false,
      message: err.message || 'Pairing failed',
      reason: err.reason,
    };
  }
}
