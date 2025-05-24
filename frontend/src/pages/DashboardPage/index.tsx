import React from 'react';
import {Navigate} from 'react-router-dom';
import {useAuth} from "../../shared/hooks/useAuth";

export const DashboardPage: React.FC = () => {
  const {user, loading} = useAuth();

  if (loading) {
    return <p>Loading...</p>
  }

  if (!user) {
    return <Navigate to="/login"/>
  }

  return (
    <div className="p-4">
      <h1 className="text-xl font-bold">Hello, {user.email}</h1>
    </div>
  );
};
