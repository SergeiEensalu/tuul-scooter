import React from 'react';
import {Navigate} from 'react-router-dom';
import {useAuth} from '../shared/hooks/useAuth';
import {Loader} from "../shared/ui/Loader";

interface PublicRouteProps {
  children: React.ReactNode;
}

export const PublicRoute: React.FC<PublicRouteProps> = ({children}) => {
  const {user, loading} = useAuth();

  if (loading) {
    return <Loader />;
  }

  if (user) {
    return <Navigate to="/"/>
  }

  return <>{children}</>;
};
