import {auth} from '../../config/firebase';
import {http} from '../../lib/http';
import {ApiResult} from '../../shared/types/api';

type Command = 'START' | 'STOP';

export const sendCommand = async (
  vehicleId: string,
  command: Command
): Promise<ApiResult> => {
  const token = await auth.currentUser?.getIdToken();
  if (!token) {
    return {success: false, message: 'User not authenticated'}
  }

  try {
    await http<void>(
      `https://europe-west3-coscooter-eu-staging.cloudfunctions.net/send-commands?apiKey=${token}`,
      'POST',
      {command, vehicleId}
    );

    return {success: true};
  } catch (err: any) {
    return {
      success: false,
      message: err.message || 'Failed to send command',
      reason: err.reason,
    };
  }
};
