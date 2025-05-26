import {ApiResult} from "../../shared/types/api";
import {fetchWithToken} from "../../shared/utils/fetchWithToken";

export const pair = async (vehicleCode: string): Promise<ApiResult> => {
  try {
    await fetchWithToken<void>(
      'https://europe-west3-coscooter-eu-staging.cloudfunctions.net/pair',
      'POST',
      {vehicleCode}
    );
    return {success: true, data: undefined};
  } catch (err: any) {
    return {
      success: false,
      message: err.message || 'Pairing failed',
      reason: err.reason,
    };
  }
};
