import {auth} from "../../config/firebase";
import {ApiResult} from "../../shared/types/api";
import {http} from "../../lib/http";

export const unpairScooter = async (vehicleId: string): Promise<ApiResult> => {
  const token = await auth.currentUser?.getIdToken();

  if (!token) {
    throw Error(`Token not found.`);
  }

  try {
    await http<void>(
      `https://europe-west3-coscooter-eu-staging.cloudfunctions.net/pair?apiKey=${token}`,
      'DELETE',
      {vehicleId}
    );

    return {success: true};
  } catch (err: any) {
    return {
      success: false,
      message: err.message || 'Unpairing failed',
      reason: err.reason,
    };
  }
}
