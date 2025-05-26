import React, {useState} from 'react';
import {signInWithEmailAndPassword} from 'firebase/auth';
import {auth} from '../../config/firebase';
import {useNavigate, Link} from 'react-router-dom';
import {Input} from '../../shared/ui/Input';
import {Button} from '../../shared/ui/Button';
import {FormError} from '../../shared/ui/FormError';
import {getReadableErrorMessage} from "../../shared/utils/getReadableErrorMessage";
import {CenteredCard} from "../../shared/ui/CenteredCard";

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
      setError(getReadableErrorMessage(err));
    }
  };

  return (
    <CenteredCard>
      <form onSubmit={handleLogin} className="p-4 space-y-4 max-w-sm mx-auto">
        <h1 className="text-xl font-bold">Login</h1>
        <FormError message={error || ''}/>

        <Input
          type="email"
          label="Email"
          placeholder="you@example.com"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <Input
          type="password"
          label="Password"
          placeholder="••••••••"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <Button type="submit">Log in</Button>
        <p className="text-sm">
          Don’t have an account?{' '}
          <Link to="/register" className="text-blue-600 underline">
            Sign up
          </Link>
        </p>
      </form>
    </CenteredCard>
  );
};
