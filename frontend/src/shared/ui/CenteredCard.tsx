import React from 'react';

type Props = {
  children: React.ReactNode;
};

export const CenteredCard: React.FC<Props> = ({children}) => {
  return (
    // Comment by Sergei:  actually this 'mt-32' should not be here,
    // this component should be max be reusable and as simple as possible,
    // but cause I do not have time to solve problem with general layout and page sizes and elements - let be here.

    <div className="flex justify-center mt-32">
      <div className="p-6 space-y-4 max-w-md w-full border rounded-xl shadow-sm bg-white">
        {children}
      </div>
    </div>
  );
};
