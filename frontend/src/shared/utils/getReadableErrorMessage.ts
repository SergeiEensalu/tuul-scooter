import {errorMessages} from '../constants/errorMessages';

export const getReadableErrorMessage = (error: unknown): string => {
  if (!error || typeof error !== 'object') return errorMessages.UNKNOWN;

  const err = error as any;

  // Firebase errors
  if (typeof err.code === 'string' && errorMessages[err.code]) {
    return errorMessages[err.code];
  }

  // Custom backend errors
  if (typeof err.reason === 'string' && errorMessages[err.reason]) {
    return errorMessages[err.reason];
  }

  if (typeof err.message === 'string') {
    return err.message;
  }

  return errorMessages.UNKNOWN;
};
