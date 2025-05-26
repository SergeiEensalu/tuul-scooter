import React from 'react';

type Props = React.ButtonHTMLAttributes<HTMLButtonElement> & {
  children: React.ReactNode;
};

export const Button: React.FC<Props> = ({children, className = '', ...rest}) => {
  return (
    <button
      className={
        `px-4 py-2 bg-black text-white rounded-md font-medium transition disabled:opacity-50 disabled:cursor-not-allowed flex justify-center items-center w-full ${className}`
      }
      {...rest}
    >
      {children}
    </button>
  );
};
