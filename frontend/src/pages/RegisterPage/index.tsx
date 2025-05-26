import React, {useState} from 'react';
import {createUserWithEmailAndPassword} from 'firebase/auth';
import {auth} from '../../config/firebase';
import {useNavigate, Link} from 'react-router-dom';
import {Input} from '../../shared/ui/Input';
import {Button} from '../../shared/ui/Button';
import {FormError} from '../../shared/ui/FormError';
import {getReadableErrorMessage} from "../../shared/utils/getReadableErrorMessage";
import {CenteredCard} from "../../shared/ui/CenteredCard";

export const RegisterPage: React.FC = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState<string | null>(null);

  const handleRegister = async (e: React.FormEvent) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      setError('Passwords do not match');
      return;
    }

    try {
      await createUserWithEmailAndPassword(auth, email, password);
      navigate('/');
      // I hate any-s. But let it be here.
    } catch (err: any) {
      setError(getReadableErrorMessage(err));
    }
  };

  return (
    <CenteredCard>
      <form onSubmit={handleRegister} className="p-4 space-y-4 max-w-sm mx-auto">
        <h1 className="text-xl font-bold">Register</h1>
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
        <Input
          type="password"
          label="Confirm Password"
          placeholder="••••••••"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
          required
        />
        <Button type="submit">Create Account</Button>
        <p className="text-sm">
          Already have an account?{' '}
          <Link to="/login" className="text-blue-600 underline">
            Log in
          </Link>
        </p>
      </form>
    </CenteredCard>
  );
};
