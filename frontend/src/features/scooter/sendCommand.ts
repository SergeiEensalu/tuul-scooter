import {ApiResult} from '../../shared/types/api';
import {fetchWithToken} from "../../shared/utils/fetchWithToken";

type Command = 'START' | 'STOP';

export const sendCommand = async (
  vehicleId: string,
  command: Command
): Promise<ApiResult> => {
  try {
    await fetchWithToken<void>(
      'https://europe-west3-coscooter-eu-staging.cloudfunctions.net/send-commands',
      'POST',
      {command, vehicleId}
    );
    return {success: true, data: undefined};
  } catch (err: any) {
    return {
      success: false,
      message: err.message || 'Failed to send command',
      reason: err.reason,
    };
  }
};
