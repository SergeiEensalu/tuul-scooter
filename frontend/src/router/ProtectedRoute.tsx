import React from 'react';
import {Navigate} from 'react-router-dom';
import {useAuth} from '../shared/hooks/useAuth';
import {Loader} from "../shared/ui/Loader";

interface ProtectedRouteProps {
  children: React.ReactNode;
}

export const ProtectedRoute: React.FC<ProtectedRouteProps> = ({children}) => {
  const {user, loading} = useAuth();

  if (loading) {
    return <Loader />;
  }

  if (!user) {
    return <Navigate to="/login"/>
  }

  return <>{children}</>;
};
