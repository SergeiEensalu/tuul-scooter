import React from 'react';

type Props = {
  level: number; // from 0 to 100
};

export const BatteryBar: React.FC<Props> = ({level}) => {
  const getColor = () => {
    if (level > 60) return 'bg-green-500';
    if (level > 30) return 'bg-yellow-500';
    return 'bg-red-500';
  };

  return (
    <div className="w-full bg-gray-200 h-4 rounded-md overflow-hidden">
      <div
        className={`h-full ${getColor()}`}
        style={{width: `${level}%`}}
      />
    </div>
  );
};
