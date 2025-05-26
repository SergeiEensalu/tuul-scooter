import React from 'react';

export const FormError: React.FC<{ message?: string }> = ({message}) => {
  if (!message) {
    return null
  }
  return <div
    className="flex items-center gap-2 p-2 mb-2 text-sm text-red-700 bg-red-100 border border-red-300 rounded-md">
    <span className="text-xl">❗</span>
    <span>{message}</span>
  </div>
};
