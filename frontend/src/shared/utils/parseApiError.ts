export const parseApiError = (err: any): string => {
  if (err?.reason === 'MAX_PAIRED_USERS_EXCEEDED') {
    return 'Scooter already paired with maximum users.'
  }

  if (err?.message?.includes('vehicleCode')) {
    return 'Invalid scooter code.'
  }

  if (err?.message) {
    return err.message
  }

  return 'Something went wrong.';
};
