import React from 'react';
import {Link} from 'react-router-dom';

export const NotFoundPage: React.FC = () => {
  return (
    <div className="text-center p-8">
      <h1 className="text-3xl font-bold mb-4">404 – Page Not Found</h1>
      <p className="mb-4">The page you’re looking for doesn’t exist.</p>
      <Link to="/" className="text-blue-600 underline">
        Refresh
      </Link>
    </div>
  );
};
