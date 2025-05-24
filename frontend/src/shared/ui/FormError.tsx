import React from 'react';

export const FormError: React.FC<{ message?: string }> = ({message}) => {
  if (!message) return null;
  return <p className="text-red-500 text-sm">{message}</p>;
};
