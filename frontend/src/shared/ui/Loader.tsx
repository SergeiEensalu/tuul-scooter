import React from 'react';

export const Loader: React.FC = () => {
  return (
    <div className="flex items-center justify-center p-8 text-gray-500 animate-pulse">
      <span className="text-sm">Loading scooter data...</span>
    </div>
  );
};
