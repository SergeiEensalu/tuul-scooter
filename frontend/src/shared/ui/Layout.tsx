import React from 'react';
import {Outlet} from 'react-router-dom';
import {signOut} from 'firebase/auth';
import {auth} from '../../config/firebase';
import {useAuth} from "../hooks/useAuth";
import {Button} from './Button';

export const Layout: React.FC = () => {
  const {user} = useAuth();

  const handleLogout = () => {
    signOut(auth);
  };

  return (
    <div>
      <header className="flex justify-between items-center p-4 border-b">
        <h1 className="text-xl font-bold">Tuul Scooter</h1>
        <div className="flex items-center gap-4">
          {user && <span className="text-sm text-gray-600">{user.email}</span>}
          <div className="w-auto">
            <Button onClick={handleLogout}>Logout</Button>
          </div>
        </div>
      </header>
      <main className="p-4">
        <Outlet/>
      </main>
    </div>
  );
};
