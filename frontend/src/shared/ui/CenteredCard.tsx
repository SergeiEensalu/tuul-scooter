import React from 'react';

type Props = {
  children: React.ReactNode;
};

export const CenteredCard: React.FC<Props> = ({children}) => {
  return (
      // Comment by Sergei: Ideally, this 'mt-32' shouldn't be here.
      // My philosophy is that all reusable components must stay truly REUSABLE,
      // meaning they should not contain layout-specific hacks or case-specific styling.
      // Unfortunately, due to time constraints and unresolved layout/page structure issues,
      // I'm intentionally breaking this principle here for now. Let it be.

    <div className="flex justify-center mt-32">
      <div className="p-6 space-y-4 max-w-md w-full border rounded-xl shadow-sm bg-white">
        {children}
      </div>
    </div>
  );
};
