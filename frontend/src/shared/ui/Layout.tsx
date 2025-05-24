import React from 'react';
import {Outlet} from 'react-router-dom';
import {signOut} from 'firebase/auth';
import {auth} from '../../config/firebase';

export const Layout: React.FC = () => {
  const handleLogout = () => {
    signOut(auth);
  };

  return (
    <div>
      <header className="flex justify-between items-center p-4 border-b">
        <h1 className="text-xl font-bold">Tuul Scooter</h1>
        <button onClick={handleLogout} className="text-sm bg-black text-white px-4 py-2 rounded">
          Logout
        </button>
      </header>
      <main className="p-4">
        <Outlet/>
      </main>
    </div>
  );
};
