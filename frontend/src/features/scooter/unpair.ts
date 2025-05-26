import {ApiResult} from "../../shared/types/api";
import {fetchWithToken} from "../../shared/utils/fetchWithToken";

export const unpair = async (vehicleId: string): Promise<ApiResult> => {
  try {
    await fetchWithToken<void>(
      'https://europe-west3-coscooter-eu-staging.cloudfunctions.net/pair',
      'DELETE',
      {vehicleId}
    );
    return {success: true, data: undefined};
    // I hate any-s. But let it be here.
  } catch (err: any) {
    return {
      success: false,
      message: err.message || 'Unpairing failed',
      reason: err.reason,
    };
  }
};
