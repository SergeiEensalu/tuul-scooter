import React, {useState} from 'react';
import {signInWithEmailAndPassword} from 'firebase/auth';
import {auth} from '../../config/firebase';
import {useNavigate} from 'react-router-dom';

export const LoginPage: React.FC = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState<string | null>(null);

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await signInWithEmailAndPassword(auth, email, password);
      navigate('/');
    } catch (err: any) {
      setError(err.message);
    }
  };

  return (
    <form onSubmit={handleLogin} className="p-4 space-y-4 max-w-sm mx-auto">
      <h1 className="text-xl font-bold">Login</h1>
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        className="w-full border p-2"
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        className="w-full border p-2"
      />
      <button type="submit" className="w-full bg-black text-white p-2">
        Log in
      </button>
      {error && <p className="text-red-500">{error}</p>}
    </form>
  );
};
